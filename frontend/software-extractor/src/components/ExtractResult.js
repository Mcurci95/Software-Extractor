import React from 'react';
import ClassComponent from './ClassComponent';

const SERVER_ENDPOINT = 'http://localhost:8080/allClasses';
const PRIMITIVE_TYPES = ['int', 'String', 'double', 'Int', 'Double', 'float']

class Testing extends React.Component{
    state = {
        loading : true,
        project: null,
    };

    classDict = {}; // This is a dict [className] : [class attributes]

    async componentDidMount() {
        const response = await fetch(SERVER_ENDPOINT);
        const rawAllClassesData = await response.json();
        console.log(rawAllClassesData);
        this.classDict = rawAllClassesData.reduce((acc, cls) => {
            acc[cls.name] = cls;
            return acc;
        }, {});
        
        const parsedData = this.parseData(rawAllClassesData);
        console.log(parsedData);
        this.setState({project:parsedData, loading : false});
    };

    parseData(rawData) {
        console.log(rawData)
        let data = this.findDescendants(rawData);
        data = this.findAggregate(data);
        data = this.findAssociate(data);
        data = this.findAncestors(data);
        return data;
    }

    findAncestors(allClasses) {
        return allClasses.reduce((acc, cls) => {
            cls['ancestor'] = new Set();
            
            let currentParent = cls.parent;
            while (currentParent !== null) {
                cls['ancestor'].add(currentParent);
                const parentClsAttr = this.classDict[currentParent];
                currentParent = parentClsAttr.parent;
            }
            cls['ancestor'] = [...cls['ancestor']];

            return acc.concat(cls);
        }, [])
    }

    findDescendants(allClasses) {
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

    findAggregate(allClasses) {
        return allClasses.reduce((acc, cls) => {
            cls['aggregate'] = new Set();

            for (const otherCls of allClasses) {
                if (cls.name === otherCls.name) continue; 
                for (const dataMember of cls.mClassDataMembers) {
                    if (dataMember.mType.name === otherCls.name || 
                        !this._isPrimitive(dataMember.mType.name)) {
                        if (dataMember.mType.name !== null) 
                            cls.aggregate.add(dataMember.mType.name);
                    }
                }
            }

            cls['aggregate'] = [...cls['aggregate']];
            return acc.concat(cls);
        }, [])
    }

    findAssociate(allClasses) {
        return allClasses.reduce((acc, cls) => {
            cls['associate'] = new Set();
            // Check constructor 
            for (const constructor of cls.mConstructors) {
                for (const param of constructor.parameters) {
                    if (!this._isPrimitive(param.mType.name) && param.mType.name !== null) {
                        cls.associate.add(param.mType.name);
                    }
                }
            }

            // Check method param and method body
            for (const method of cls.mMethods) {
                for (const param of method.mParameters) {
                    if (!this._isPrimitive(param.mType.name) && param.mType.name !== null) {
                        cls.associate.add(param.mType.name);
                    }
                }
                for (const variable of method.mBody.variables) {
                    if (!this._isPrimitive(variable.mReturnType.name) 
                    && variable.mReturnType.name !== null) {
                        cls.associate.add(variable.mReturnType.name);
                    }
                }
            }

            cls['associate'] = [...cls['associate']];

            return acc.concat(cls);
        }, [])
    }
    
    _isPrimitive(s) {
        if (s && s.includes("[")) {
            s = s.substring(0, s.length - 2);
        }
        return PRIMITIVE_TYPES.includes(s);
    }

    render() {
        return <>
                {this.state.loading || !this.state.project 
                ? (<div className = "container"> Loading... </div>)
                : this.state.project.map(attr =>
                    <ClassComponent key={attr.name} {...attr} />)
                }
            </>
    }
}

export default Testing;