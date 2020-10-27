import React from 'react';
import ClassComponent from './ClassComponent';

const SERVER_ENDPOINT = 'http://localhost:8080/allClasses';
const PRIMITIVE_TYPES = ['int', 'String', 'double', 'Int', 'Double']

class Testing extends React.Component{
    state = {
        loading : true,
        project: null,
    };

    async componentDidMount() {
        const response = await fetch(SERVER_ENDPOINT);
        const rawAllClassesData = await response.json();
        this.setState({project:this.parseData(rawAllClassesData), loading : false});
    };

    parseData(rawData) {
        let data = this.findDescendants(rawData);
        data = this.findAggregate(data);
        data = this.findAssociate(data);
        return data;
    }

    findDescendants(allClasses) {
        return allClasses.reduce((acc, cls) => {
            for (const childCls of allClasses) {
                if (childCls.name === cls.name) continue;
                if (childCls.parent === cls.name) {
                    if (!cls.childClasses.includes(childCls.name)) {
                        cls.childClasses.push(childCls.name);
                    }
                }
            }
            return acc.concat(cls);
        }, [])
    }

    findAggregate(allClasses) {
        const newData = []
        for (const cls of allClasses) {
            cls['aggregate'] = []

            for (const otherCls of allClasses) {
                if (cls.name === otherCls.name) continue; 
                for (const dataMember of cls.mClassDataMembers) {
                    if (dataMember.mType.name === otherCls.name || !this._isPrimitive(dataMember.mType.name)) {
                        cls.aggregate.push(dataMember.mType.name);
                    }
                }
            }
            newData.push(cls);
        }
        return newData;
    }

    findAssociate(allClasses) {
        const newData = []
        for (const cls of allClasses) {
            cls['associate'] = []
            // Check constructor 
            for (const constructor of cls.mConstructors) {
                for (const param of constructor.parameters) {
                    if (!this._isPrimitive(param.mType.name)) {
                        cls.associate.push(param.mType.name);
                    }
                }
            }

            // Check method param and method body
            for (const method of cls.mMethods) {
                for (const param of method.mParameters) {
                    if (!this._isPrimitive(param.mType.name)) {
                        cls.associate.push(param.mType.name);
                    }
                }
                for (const variable of method.mBody.variables) {
                    if (!this._isPrimitive(variable.mReturnType.name)) {
                        cls.associate.push(variable.mReturnType.name);
                    }
                }
            }

            newData.push(cls);
        }
        return newData;
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
                ? (<div className = "a"> Loading... </div>) 
                : this.state.project.map(attr =>
                    <ClassComponent key={attr.name} {...attr} />)
                }
            </>
    }
}

export default Testing;