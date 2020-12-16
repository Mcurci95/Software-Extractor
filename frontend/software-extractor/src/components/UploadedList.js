import React, { useState, useEffect } from 'react';
import Accordion from 'react-bootstrap/Accordion';
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import CompareTwoResults from './CompareTwoResults';
import CompareComponent from './CompareComponent';
import ClassComponent from './ClassComponent';
import {
  selectDiff,
  addAllDiffs,
  addAllEntities,
} from '../reducers/entityReducer';
import { useSelector } from 'react-redux';
import CompareResult from './CompareResult';

import {compareTwoEntities} from '../helpers/compareTwoEntities';

const SERVER_ENDPOINT = 'http://localhost:8080/allClasses';
const DOWNLOAD_ENDPOINT = 'http://localhost:8080/downloadFile'



export default function UploadList() {
  const [loading, setLoading] = useState(true);
  const [project, setProject] = useState(null);
  const [project1, setProject1] = useState(null);
  const [pID, setPID] = useState(null);
  const [sortedProjectMap, setSortedProjectMap] = useState(null);
  const [original, setOriginal] = useState(null);
  const diffs = useSelector(selectDiff);
  const allDiffs = useSelector(addAllDiffs);
  const allEntities = useSelector(addAllEntities);

  const  bin2String = array => {
    return String.fromCharCode.apply(String, array);
  }

  useEffect(() => {
    async function fetchData() {
      const response = await fetch(SERVER_ENDPOINT);
      let data = await response.json();
      data.sort((a, b) => (a.name > b.name ? 1 : -1));
      let sortedProjectMap = {};
      for (let i = 0; i < data.length; i++) {
        if (sortedProjectMap.hasOwnProperty(data[i].name)) {
          sortedProjectMap[data[i].name].push(data[i]);
        } else {
          sortedProjectMap[data[i].name] = [data[i]];
        }
      }

      // const selectedDataTest = useSelector(selectDiff);
      // console(selectedDataTest);
      setLoading(false);
      setProject(data);
      setSortedProjectMap(sortedProjectMap);

      // this.setState({project: data, loading : false, sortedProjectMap: sortedProjectMap});
      
    }
    fetchData();
  }, []);

  const buildAccordian = classDict => {
    let accordionArray = [];
    for (const cls of Object.keys(classDict)) {
      let p = classDict[cls];
      accordionArray.push(
        <Accordion key={p[0].id}>
          <Card>
            <Card.Header>
              <Accordion.Toggle as={Button} variant='link' eventKey='0'>
                {p[0].name}
              </Accordion.Toggle>
            </Card.Header>
            {buildInner(classDict[cls])}
          </Card>
        </Accordion>,
      );
    }

    return accordionArray;
  };

  const buildInner = c => {
    let innerAccordion = [];

    for (let _cls of Object.keys(c)) {
      let cls = c[_cls];

      innerAccordion.push(
        <Accordion.Collapse eventKey='0'>
          <Card.Body>
            <Button
              variant='outline-primary'
              onClick={handleClick}
              value={cls.id}>
              Compare Snapshots
            </Button>
            <strong>Version: </strong> {cls.version} <strong>Snapshot:</strong>{' '}
            {cls.createdDateTime}
          </Card.Body>
        </Accordion.Collapse>,
      );
    }

    return innerAccordion;
  };

  const handleClick = event => {
    let selectedClass = allEntities.payload.entity.data.filter(
      p => p.id == event.target.value,
    );
    if (selectedClass.lenth === 0) return null;
    selectedClass = selectedClass[0];
    setPID(event.target.value);
    setProject1(selectedClass);
    let original = allEntities.payload.entity.data.filter(
      p => p.name == selectedClass.name && p.version == 1,
    );
    original = original[0];
    setOriginal(original);
  };


async function downloadFile(obj, output) {

//     fetch('https://example.com?' + new URLSearchParams({
//     foo: 'value',
//     bar: 2,
// }))
    const res = await fetch(DOWNLOAD_ENDPOINT + '?' + new URLSearchParams({
        classId: obj.id.toString(),
        comment: output
    }), {
        method: 'GET',
        headers : { 
            'Content-Type': 'application/json',
            'Accept': 'application/json'
           }
    });

    const blob = await res.blob();
    const newBlob = new Blob([blob]);

    const blobUrl = window.URL.createObjectURL(newBlob);

    const link = document.createElement('a');
    link.href = blobUrl;
    link.setAttribute('download', `${obj.name}.java`);
    document.body.appendChild(link);
    link.click();
    link.parentNode.removeChild(link);

    window.URL.revokeObjectURL(blob);

    console.log(res);



    
}
const createCompareComponent = () => {
    let localOriginal = { ...original };
    let localProject1 = { ...project1 };

    let f = localProject1.data;
    delete localOriginal.data;
    delete localProject1.data;
    console.log(f);

    let output = buildComparisonString(localOriginal, localProject1);
    console.log(localProject1.id);
    downloadFile(localProject1, '/* ' + output + '*/    \n');


        
    return (
      <div>
        <CompareTwoResults version1={original} version2={project1} />
        {output.map(c => <p>{c}</p>)}
      </div>
    );
  };


  const buildComparisonString = (entity, comparedEntity) => {
        let comparison = compareTwoEntities(entity, comparedEntity);
        const changes = comparison[comparedEntity.version];
        const changedMethods = changes['method'];
        const changedDataMembers = changes['datamember'];
        const changedConstructors = changes['constructor'];
        let output = [];
        console.log(changes);

        // Scan Date
        // Method Comparisons
        // Added 
        const methodsAdded = changedMethods['added'];
        for (let addedMethod of methodsAdded) {
            output.push(_methodDetailExtractor(addedMethod, 'added'));
        }
        // Deleted
        const methodsDeleted = changedMethods['deleted'];
        for (let deletedMethod of methodsDeleted) {
            output.push(_methodDetailExtractor(deletedMethod, 'deleted'));
        }
        // Changed
        const methodsChanged = changedMethods['changeAccess'];
        for (let changedMethod of methodsChanged) {
            output.push(_methodModifier(changedMethod));
        }

        // Constructors
        const consAdded = changedConstructors['added'];
        for (let addedCons of consAdded) {
            output.push(_constructorDetailExtractor(addedCons, 'added'));
        }

        const consDeleted = changedConstructors['deleted'];
        for (let deletedCons of consDeleted) {
            output.push(_constructorDetailExtractor(deletedCons, 'deleted'));
        }
        // Data Members comparisons
        // Added
        const membersAdded = changedDataMembers['added'];
        for (let addedMember of membersAdded) {
            output.push(_datamemberDetailExtractor(addedMember, 'added'));
        }
        // Deleted
        const membersDeleted = changedDataMembers['deleted'];
        for (let deletedMember of membersDeleted) {
            output.push(_datamemberDetailExtractor(deletedMember, 'deleted'));
        }
        // Changed
        const membersChanged = changedDataMembers['changeAccess'];
        for (let changedMember of membersChanged) {
            output.push(_datamemberModifier(changedMember, 'access'));
        }

        const membersTypedChanged = changedDataMembers['changeTypes'];
        for (let changedDM of membersTypedChanged) {
            output.push(_datamemberModifier(changedDM, 'type'));
        }
        // comparison[entity.id];
        console.log(output);
        return output;
  }

  const _methodDetailExtractor = (modifiedMethod, delta) => {
    let name = modifiedMethod['name'];
    let access = modifiedMethod['mAccess'][0]['accessName'];
    let returnType = modifiedMethod['mReturnType']['name'];
    let params = modifiedMethod['mParameters'];
    let paramsArr  = [];
    for (let param of params) {
        console.log(param);
        paramsArr.push(`${param.mType.name} ${param.name}`);
    }

    return `Method ${access} ${returnType} ${name}(${paramsArr.join()}) was ${delta} since previous scan`;
  }

  const _methodModifier = (modifiedMethod) => {
    let name = modifiedMethod['from']['name'];
    let startAccess = modifiedMethod['from']['mAccess'][0]['accessName'];
    let endAccess = modifiedMethod['to']['mAccess'][0]['accessName'];
    let returnType = modifiedMethod['from']['mReturnType']['name'];
    let params = modifiedMethod['from']['mParameters'];
    let paramsArr  = [];
    for (let param of params) {
        console.log(param);
        paramsArr.push(`${param.mType.name} ${param.name}`);
    }
    return  startAccess === endAccess ? ' ': `Method ${startAccess} ${returnType} ${name}(${paramsArr.join()}) was changed from ${startAccess} to ${endAccess} since previous scan`;
  }

  const _datamemberDetailExtractor = (modifiedMember, delta) => {
        let name = modifiedMember['name'];
        let type = modifiedMember['mType']['name'];

        return `Data Member ${type} ${name} was ${delta} since previous scan`;
  }


  const _datamemberModifier = (modifiedMember, typeOfChange) => {
    let name = modifiedMember['from']['name'];
    if (typeOfChange === 'access') {
        let startAccess = modifiedMember['from']['mAccess'][0]['accessName'];
        let endAccess = modifiedMember['to']['mAccess'][0]['accessName'];
        let type = modifiedMember['from']['mType']['name'];
        return startAccess === endAccess ? ' ': `Data Member ${startAccess} ${type} ${name} changed from ${startAccess} to ${endAccess} since previous scan`;
    }
    else {
        let startType = modifiedMember['from']['mType']['name'];
        let endType = modifiedMember['to']['mType']['name'];
        return `Data Member ${name} changed from ${startType} to ${endType} since previous scan`;
    }

    
}


const _constructorDetailExtractor = (modifiedConstructor, delta) => {
    let name = modifiedConstructor['name'];
    let params = modifiedConstructor['parameters'];
    let paramsArr  = [];
    for (let param of params) {
        console.log(param);
        paramsArr.push(`${param.mType.name} ${param.name}`);
    }

    return  `Method  ${name}(${paramsArr.join()}) was ${delta} since previous scan`;
}


  return (
    <div>
      <h1>Classes: </h1>
      {loading || !sortedProjectMap ? (
        <div class='a'> Loading... </div>
      ) : (
        buildAccordian(sortedProjectMap)
      )}

      {project1 ? createCompareComponent() : <p>Pick a Version</p>}
    </div>
  );
}
