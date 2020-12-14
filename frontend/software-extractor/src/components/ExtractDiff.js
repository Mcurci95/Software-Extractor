import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { selectEntity, addAllDiffs } from '../reducers/entityReducer';

import { compareTwoEntities } from '../helpers/compareTwoEntities';

export default function ExtractDiff() {
  const dispatch = useDispatch();
  const allEntities = useSelector(selectEntity); // Get from redux store

  const entityBuckets = allEntities.reduce((acc, cls) => {
    let fullQualifiedName = '';
    if ('classMPackage' in cls) {
      fullQualifiedName = cls['classMPackage'].name + '.' + cls['name'];
    } else {
      fullQualifiedName = cls['name'];
    }
    if (!(fullQualifiedName in acc)) {
      acc[fullQualifiedName] = [];
    }

    acc[fullQualifiedName].push(cls);
    return acc;
  }, {});

  // Sort all buckets
  for (const entity of Object.keys(entityBuckets)) {
    entityBuckets[entity].sort((x, y) => x.version - y.version);
  }

  const compareAllVersionsOfANamespaceToV1 = allVersions => {
    let v1 = allVersions.filter(cls => cls.version === 1);
    // If model version 1 is not found, stop
    if (v1.length === 0) return {};
    v1 = v1[0];

    const otherVersions = allVersions.filter(cls => cls.version !== 1);

    // For each class, compare it to v1 and gather method and datamember diff
    return otherVersions.reduce(
      (diff, otherVersion) => ({
        ...diff,
        ...compareTwoEntities(v1, otherVersion),
      }),
      {},
    );
  };

  // Gather all diffs of different classes
  const allDiffs = Object.keys(entityBuckets).reduce((acc, namespace) => {
    acc[namespace] = compareAllVersionsOfANamespaceToV1(
      entityBuckets[namespace],
    );
    return acc;
  }, {});

  dispatch(addAllDiffs(allDiffs)); // Send data to redux store

  return (
    <>
      <div className='container'>
        {
          allEntities.length === 0 ? (
            <div className='container'>
              {' '}
              No Entity Loaded. Unable to connect to DB
            </div>
          ) : (
            <div>
              <p>Extracted all diff</p>
            </div>
          )
          // : this.state.project.map(attr =>
          //     <CompareComponent key={attr.name} {...attr} />)
        }
      </div>
    </>
  );
}
