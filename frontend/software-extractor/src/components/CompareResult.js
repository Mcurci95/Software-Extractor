import React from 'react';
import CompareTwoResults from './CompareTwoResults';

const SERVER_ENDPOINT = 'http://localhost:8080/allClasses';

let output = '';

const out = [];

function compareTwoJson(elm, target) {
  for (const j in target) {
    try {
      // if the element not in target then added
      if (!elm.hasOwnProperty(j)) {
        const outstring = j + ' was added' + '<br />';
        if (!out.includes(outstring)) {
          output = output.concat(outstring);
          out.push(outstring);
        }
        // console.log(j+' was added');
      }
      if (target.hasOwnProperty(elm[j])) {
        const outstring = j + ' was deleted' + '<br />';
        if (!out.includes(outstring)) {
          output = output.concat(outstring);
          out.push(outstring);
        }
        // output = output.concat(j+' was deleted' + '<br />');
        // console.log(j+' was deleted');
      }
      // if the target not in element then delete
      if (typeof target[j] == 'object') {
        compareTwoJson(elm[j], target[j]);
      } else if (
        elm[j] !== target[j] &&
        j !== 'id' &&
        j !== 'createdDateTime' &&
        j !== 'version' &&
        j != 'data'
      ) {
        const outstring = elm[j] + ' changed to ' + target[j] + '<br />';
        if (!out.includes(outstring)) {
          output = output.concat(outstring);
          out.push(outstring);
        }
        // output = output.concat(elm[j]+' changed to ' + target[j] + '<br />');
        // console.log(elm[j] + ' changed to ' + target[j]);
      }
    } catch (e) {
      // console.log(e);
    }
  }
}

class Testing extends React.Component {
  state = {
    loading: true,
    project: null,
  };

  classDict = {}; // This is a dict [className] : [class attributes]

  async componentDidMount() {
    const response = await fetch(SERVER_ENDPOINT);
    const rawAllClassesData = await response.json();
    rawAllClassesData.sort(function (a, b) {
      return a.name.localeCompare(b.name);
    });
    this.classDict = rawAllClassesData.reduce((acc, cls) => {
      acc[cls.name] = cls;
      return acc;
    }, {});
    const comparedata = rawAllClassesData;

    let targetelm = '';
    const versionone = [];
    const versiontwo = [];
    for (let elm of comparedata) {
      if (targetelm !== elm.name) {
        //    add to list and set targetelm = the new one
        targetelm = elm.name;
        versionone.push(elm);
      } else {
        versiontwo.push(elm);
      }
    }

    for (const elm of versionone) {
      const targetcompare = versiontwo.filter(function (i) {
        return i.name === elm.name;
      });
      if (targetcompare.length > 0) {
        const target = targetcompare[0];
        output = output.concat('<br />For class ' + target.name + '<br />');
        output = output.concat(
          'Version 2 uploaded on ' + target.createdDateTime + '<br />',
        );
        const compareone = target;
        delete compareone.id;
        delete compareone.createdDateTime;
        delete compareone.version;
        delete compareone.implementation;

        const comparetwo = elm;
        delete comparetwo.id;
        delete comparetwo.createdDateTime;
        delete comparetwo.version;
        delete comparetwo.implementation;

        // console.log(JSON.stringify(compareone));
        // console.log(JSON.stringify(comparetwo));

        if (JSON.stringify(compareone) !== JSON.stringify(comparetwo)) {
          compareTwoJson(elm, target);
        }
        // else {
        //     output = output.concat('Nothing was changed'+ '<br />')
        // }
      }
    }
    console.log(output);

    //run the whole thing output from here
    // const parsedData = this.parseData(comparedata);
    // console.log(parsedData);
    this.setState({ project: output, loading: false });
  }
  //
  // parseData(rawData) {
  //     let data = this.findDescendants(rawData);
  //     data = this.findAggregate(data);
  //     data = this.findAssociate(data);
  //     data = this.findAncestors(data);
  //     console.log(data);
  //     return data;
  // }
  //
  // findAncestors(allClasses) {
  //     return allClasses.reduce((acc, cls) => {
  //         cls['ancestor'] = new Set();
  //
  //         let currentParent = cls.parent;
  //         while (currentParent !== null) {
  //             cls['ancestor'].add(currentParent);
  //             const parentClsAttr = this.classDict[currentParent];
  //             currentParent = parentClsAttr.parent;
  //         }
  //         cls['ancestor'] = [...cls['ancestor']];
  //
  //         return acc.concat(cls);
  //     }, [])
  // }
  //
  // findDescendants(allClasses) {
  //     return allClasses.reduce((acc, cls) => {
  //         for (const otherCls of allClasses) {
  //             if (otherCls.name === cls.name) continue;
  //             if (otherCls.parent === cls.name) {
  //                 if (!cls.childClasses.includes(otherCls.name)
  //                     && otherCls.name !== null) {
  //                     cls.childClasses.push(otherCls.name);
  //                 }
  //             }
  //         }
  //         return acc.concat(cls);
  //     }, [])
  // }
  //
  // findAggregate(allClasses) {
  //     return allClasses.reduce((acc, cls) => {
  //         cls['aggregate'] = new Set();
  //
  //         for (const otherCls of allClasses) {
  //             if (cls.name === otherCls.name) continue;
  //             for (const dataMember of cls.mClassDataMembers) {
  //                 if (dataMember.mType.name === otherCls.name ||
  //                     !this._isPrimitive(dataMember.mType.name)) {
  //                     if (dataMember.mType.name !== null)
  //                         cls.aggregate.add(dataMember.mType.name);
  //                 }
  //             }
  //         }
  //
  //         cls['aggregate'] = [...cls['aggregate']];
  //         return acc.concat(cls);
  //     }, [])
  // }
  //
  // findAssociate(allClasses) {
  //     return allClasses.reduce((acc, cls) => {
  //         cls['associate'] = new Set();
  //         // Check constructor
  //         for (const constructor of cls.mConstructors) {
  //             for (const param of constructor.parameters) {
  //                 if (!this._isPrimitive(param.mType.name) && param.mType.name !== null) {
  //                     cls.associate.add(param.mType.name);
  //                 }
  //             }
  //         }
  //
  //         // Check method param and method body
  //         for (const method of cls.mMethods) {
  //             for (const param of method.mParameters) {
  //                 if (!this._isPrimitive(param.mType.name) && param.mType.name !== null) {
  //                     cls.associate.add(param.mType.name);
  //                 }
  //             }
  //             for (const variable of method.mBody.variables) {
  //                 if (!this._isPrimitive(variable.mReturnType.name)
  //                     && variable.mReturnType.name !== null) {
  //                     cls.associate.add(variable.mReturnType.name);
  //                 }
  //             }
  //         }
  //
  //         cls['associate'] = [...cls['associate']];
  //
  //         return acc.concat(cls);
  //     }, [])
  // }
  //
  // _isPrimitive(s) {
  //     if (s && s.includes("[")) {
  //         s = s.substring(0, s.length - 2);
  //     }
  //     return PRIMITIVE_TYPES.includes(s);
  // }

  render() {
    return (
      <>
        {this.state.loading || !this.state.project ? (
          <div className='container'> Loading... </div>
        ) : (
          this.state.project.split('<br />').map(i => (
            <div className='container'>
              {i}
              <br />
            </div>
          ))
        )}
      </>
    );
  }
}

export default Testing;
