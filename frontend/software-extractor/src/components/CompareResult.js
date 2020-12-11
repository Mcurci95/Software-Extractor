import React from 'react';
import CompareComponent from './CompareComponent';
import {useSelector, useDispatch} from 'react-redux'
import {selectEntity, addAllDiffs} from '../reducers/entityReducer'


export default function CompareResult() {
    const dispatch = useDispatch();
    const allEntities = useSelector(selectEntity); // Get from redux store

    const entityBuckets = allEntities.reduce((acc, cls) => {
            let fullQualifiedName = "";
            if ('classMPackage' in cls) {
                fullQualifiedName = cls['classMPackage'].name + "." + cls['name'];
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
        entityBuckets[entity].sort((x, y) => x.version - y.version)
    }

    const getDiff = data => {
        let modelClass = data.filter(cls => cls.version === 1);
        // If model version 1 is not found, stop
        if (modelClass.length === 0) return {};
        modelClass = modelClass[0];
        
        const otherClasses = data.filter(cls => cls.version !== 1);

        // For each class, compare it to v1 and gather method and datamember diff
        return otherClasses.reduce((diff, cls) => {
            diff[cls.version] = {
                method: _getMethodDiff(modelClass.mMethods, cls.mMethods),
                datamember: _getDataMemberDiff(modelClass.mClassDataMembers, cls.mClassDataMembers),
            };
            return diff;
        }, {})
    }

    const _getMethodDiff = (modelMethods, otherMethods) => {
        // Build a dict of model method's props for ease of access 
        const modelDict = modelMethods.reduce((acc, m) => {
            acc[m.name] = m;
            return acc;
        }, {})

        const added = [];
        const changeAccess = []; 
        const changeTypes = [];
        const deleted = []; 

        const modelMethodsNames = modelMethods.map(m => m.name);
        for (const method of otherMethods) {
            if (!(method.name in modelMethodsNames)) {
                added.push(method);
            } else {
                const v1 = modelDict[method.name];
                for (const access of method.mAccess) {
                    if (!(access in v1.mAccess)) {
                        changeAccess.push(method);
                    }
                    if (method.mReturnType.name !== v1.mReturnType.name) {
                        changeTypes.push(method);
                    }
                }
            }
        };

        // Get deleted
        const otherMethodsName = otherMethods.map(m => m.name);
        for (const method of modelMethods) {
            if (!(method.name in otherMethodsName)) {
                deleted.push(method);
            }
        }
        
        return {
            added,
            changeAccess,
            changeTypes,
            deleted
        }
    }

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
            if (!(dm.name in modelDMNames)) {
                added.push(dm);
            } else {
                const v1 = modelDict[dm.name];
                for (const access of dm.mAccess) {
                    if (!(access in v1.mAccess)) {
                        changeAccess.push(dm);
                    }
                    if (dm.mType.name !== v1.mType.name) {
                        changeTypes.push(dm);
                    }
                }
            }
        };

        // Get deleted
        const otherDMName = otherDM.map(m => m.name);
        for (const dm of modelDM) {
            if (!(dm.name in otherDMName)) {
                deleted.push(dm);
            }
        }
        
        return {
            added,
            changeAccess,
            changeTypes,
            deleted
        }
    }

    // Gather all diffs of different classes
    const allDiffs = Object.keys(entityBuckets).reduce((acc, namespace) => {
        acc[namespace] = getDiff(entityBuckets[namespace]);
        return acc;
    }, {})

    dispatch(addAllDiffs(allDiffs)) // Send data to redux store

    return (
        <>
            {allEntities.length === 0
                ? (<div className = "container"> No Entity Loaded. 
                Please click on Result to grab content from database </div>)
                : <div>
                    <p>
                        {JSON.stringify(allDiffs)}
                    </p>
                </div>
                // : this.state.project.map(attr =>
                //     <CompareComponent key={attr.name} {...attr} />)
            }
        </>
    )
}