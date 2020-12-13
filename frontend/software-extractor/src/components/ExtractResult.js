import React, {useState, useEffect} from 'react';
import {useDispatch} from 'react-redux'
import {addAllEntities} from '../reducers/entityReducer'

const SERVER_ENDPOINT = 'http://localhost:8080/allClasses';
const PRIMITIVE_TYPES = ['int', 'String', 'double', 'Int', 'Double', 'float']

export default function ExtractResult() {
    const [loading, setLoading] = useState(true);
    const [project, setProject] = useState(null);
    const [count, setCount] = useState(0);

    const dispatch = useDispatch()

    let classDict = null;

    useEffect(() => {
        async function fetchData() {
            const response = await fetch(SERVER_ENDPOINT);
            const rawAllClassesData = await response.json();
            // console.log(rawAllClassesData);
            classDict = rawAllClassesData.reduce((acc, cls) => {
                acc[cls.name] = cls;
                return acc;
            }, {});
            
            const parsedData = parseData(rawAllClassesData);
            // console.log(parsedData);
            // this.setState({project:parsedData, loading : false});
            setLoading(false);
            setProject(parsedData);
            dispatch(addAllEntities(parsedData))
        }
        fetchData();
    }, [count])

    const parseData = rawData => {
        console.log(rawData)
        let data = findDescendants(rawData);
        data = findAggregate(data);
        data = findAssociate(data);
        data = findAncestors(data);
        return data;
    }

    const findAncestors = allClasses => {
        return allClasses.reduce((acc, cls) => {
            cls['ancestor'] = new Set();
            
            let currentParent = cls.parent;
            while (currentParent !== null) {
                cls['ancestor'].add(currentParent);
                const parentClsAttr = classDict[currentParent];
                currentParent = parentClsAttr.parent;
            }
            cls['ancestor'] = [...cls['ancestor']];

            return acc.concat(cls);
        }, [])
    }

    const findDescendants = allClasses => {
        return allClasses.reduce((acc, cls) => {
            for (const otherCls of allClasses) {
                if (otherCls.name === cls.name) continue;
                if (otherCls.parent === cls.name) {
                    if (!cls.childClasses.includes(otherCls.name) 
                    && otherCls.name !== null) {
                        cls.childClasses.push(otherCls.name);
                    }
                }
            }
            return acc.concat(cls);
        }, [])
    }

    const findAggregate = allClasses => {
        return allClasses.reduce((acc, cls) => {
            cls['aggregate'] = new Set();

            for (const otherCls of allClasses) {
                if (cls.name === otherCls.name) continue; 
                for (const dataMember of cls.mClassDataMembers) {
                    if (dataMember.mType.name === otherCls.name || 
                        !_isPrimitive(dataMember.mType.name)) {
                        if (dataMember.mType.name !== null) 
                            cls.aggregate.add(dataMember.mType.name);
                    }
                }
            }

            cls['aggregate'] = [...cls['aggregate']];
            return acc.concat(cls);
        }, [])
    }

    const findAssociate = allClasses => {
        return allClasses.reduce((acc, cls) => {
            cls['associate'] = new Set();
            // Check constructor 
            for (const constructor of cls.mConstructors) {
                for (const param of constructor.parameters) {
                    if (!_isPrimitive(param.mType.name) && param.mType.name !== null) {
                        cls.associate.add(param.mType.name);
                    }
                }
            }

            // Check method param and method body
            for (const method of cls.mMethods) {
                for (const param of method.mParameters) {
                    if (!_isPrimitive(param.mType.name) && param.mType.name !== null) {
                        cls.associate.add(param.mType.name);
                    }
                }
                for (const variable of method.mBody.variables) {
                    if (!_isPrimitive(variable.mReturnType.name) 
                    && variable.mReturnType.name !== null) {
                        cls.associate.add(variable.mReturnType.name);
                    }
                }
            }

            cls['associate'] = [...cls['associate']];

            return acc.concat(cls);
        }, [])
    }
    
    const _isPrimitive = s => {
        if (s && s.includes("[")) {
            s = s.substring(0, s.length - 2);
        }
        return PRIMITIVE_TYPES.includes(s);
    }

    return (
        <>
            <div className="container">
                <div className="row">
                    <div className="col-3">
                        {loading || !project 
                        ? (<div className = "container"> Loading... </div>)
                        // : project.map(attr =>
                        //     <ClassComponent key={attr.name} {...attr} />)
                        // }
                        : <p>Loaded All Class Data</p>}
                    </div>
                    <div className="col">
                        <button className="btn btn-dark btn-sm" onClick={() => setCount(count + 1)}>Reload data from database</button>
                    </div>
                </div>
            </div>
        </>
    )
}
