const compareTwoEntities = (entity, comparedEntity) => ({
  [comparedEntity.version]: {
    comparedToEntityID: entity.id,
    method: _getMethodDiff(entity.mMethods, comparedEntity.mMethods),
    datamember: _getDataMemberDiff(
      entity.mClassDataMembers,
      comparedEntity.mClassDataMembers,
    ),
  },
});

const _getMethodDiff = (modelMethods, otherMethods) => {
  // Build a dict of model method's props for ease of access
  const modelDict = modelMethods.reduce((acc, m) => {
    acc[m.name] = m;
    return acc;
  }, {});

  const added = [];
  const changeAccess = [];
  const changeTypes = [];
  const deleted = [];

  const modelMethodsNames = modelMethods.map(m => m.name);
  for (const method of otherMethods) {
    if (!modelMethodsNames.includes(method.name)) {
      added.push(method);
    } else {
      const v1Method = modelDict[method.name];
      for (const access of method.mAccess) {
        if (!v1Method.mAccess.includes(access)) {
          changeAccess.push(_createDiffObject(v1Method, method));
        }
        if (method.mReturnType.name !== v1Method.mReturnType.name) {
          changeTypes.push(_createDiffObject(v1Method, method));
        }
      }
    }
  }

  // Get deleted
  const otherMethodsName = otherMethods.map(m => m.name);
  for (const method of modelMethods) {
    if (!otherMethodsName.includes(method.name)) {
      deleted.push(method);
    }
  }

  return {
    added,
    changeAccess,
    changeTypes,
    deleted,
  };
};

const _getDataMemberDiff = (modelDM, otherDM) => {
  // Build a dict of model method's props for ease of access
  const modelDict = modelDM.reduce((acc, m) => {
    acc[m.name] = m;
    return acc;
  }, {});

  const added = [];
  const changeAccess = [];
  const changeTypes = [];
  const deleted = [];

  const modelDMNames = modelDM.map(m => m.name);
  for (const dm of otherDM) {
    if (!modelDMNames.includes(dm.name)) {
      added.push(dm);
    } else {
      const v1Dm = modelDict[dm.name];
      for (const access of dm.mAccess) {
        if (!v1Dm.mAccess.includes(access)) {
          changeAccess.push(_createDiffObject(v1Dm, dm));
        }
        if (dm.mType.name !== v1Dm.mType.name) {
          changeTypes.push(_createDiffObject(v1Dm, dm));
        }
      }
    }
  }

  // Get deleted
  const otherDMName = otherDM.map(m => m.name);
  for (const dm of modelDM) {
    if (!otherDMName.includes(dm.name)) {
      deleted.push(dm);
    }
  }

  return {
    added,
    changeAccess,
    changeTypes,
    deleted,
  };
};

const _createDiffObject = (from, to) => ({ from, to });

exports.compareTwoEntities = compareTwoEntities;
