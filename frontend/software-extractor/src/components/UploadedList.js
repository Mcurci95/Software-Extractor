import React, { useState, useEffect } from 'react';
import Accordion from 'react-bootstrap/Accordion'
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import CompareTwoResults from './CompareTwoResults';
import CompareComponent from './CompareComponent';
import ClassComponent from './ClassComponent';
import {selectDiff, addAllDiffs, addAllEntities} from '../reducers/entityReducer';
import {useSelector} from 'react-redux';
import CompareResult from './CompareResult';

const SERVER_ENDPOINT = 'http://localhost:8080/allClasses';

// export default function ExtractResult()
let output = "";

const out = [];


function compareTwoJson (elm, target){
    for (const j in target) {
        if (j !== 'data') {
            try{
                // if the element not in target then added
                if (!elm.hasOwnProperty(j)){
                    const outstring = j + ' was added' + '<br />';
                    if (!out.includes(outstring)) {
                        output = output.concat(outstring);
                        out.push(outstring);
                    }
                    // console.log(j+' was added');
                }
                if (target.hasOwnProperty(elm[j])){
    
                    const outstring = j + ' was deleted' + '<br />';
                    if (!out.includes(outstring)) {
                        output = output.concat(outstring);
                        out.push(outstring);
                    }
                    // output = output.concat(j+' was deleted' + '<br />');
                    // console.log(j+' was deleted');
                }
                // if the target not in element then delete
                if (typeof (target[j]) == "object") {
                    compareTwoJson(elm[j], target[j]);
                }
                else if (elm[j] !== target[j] && j !== 'id' && j !== 'createdDateTime' && j !== 'version' && j !== 'data') {
                    const outstring = elm[j]+' changed to ' + target[j] + '<br />';
                    if (!out.includes(outstring)) {
                        output = output.concat(outstring);
                        out.push(outstring);
                    }
                    // output = output.concat(elm[j]+' changed to ' + target[j] + '<br />');
                    // console.log(elm[j] + ' changed to ' + target[j]);
                }
            }
            catch (e) {
                // console.log(e);
            }
        }
        
    }

    return out;
}


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
    


    useEffect(() => {


        async function fetchData() {
            const response = await fetch(SERVER_ENDPOINT);
            let data = await response.json();
            data.sort( (a,b) => (a.name > b.name) ? 1 : -1) ;
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
            console.log(sortedProjectMap);
            setLoading(false);
            setProject(data);
            setSortedProjectMap(sortedProjectMap);
            

            // this.setState({project: data, loading : false, sortedProjectMap: sortedProjectMap});
            console.log(data);
            }
            fetchData();
    }, []);


    

    const buildAccordian = classDict => {

        let accordionArray = [];
        for (const cls of Object.keys(classDict)) {
            let p = classDict[cls];
            accordionArray.push(<Accordion key={p[0].id}>
                <Card>
                    <Card.Header>
                        <Accordion.Toggle as={Button} variant="link" eventKey="0">
                            {p[0].name}
                        </Accordion.Toggle>
                    </Card.Header>
                    {buildInner(classDict[cls])}

                </Card>
                </Accordion>)
        }
        
        return accordionArray;

    }

    const buildInner = c => {
        let innerAccordion = [];
        
        for (let _cls of Object.keys(c))
        {
            let cls = c[_cls];
            
            innerAccordion.push(<Accordion.Collapse eventKey="0">
                        <Card.Body>
                            <Button variant="outline-primary" onClick = {handleClick} value={cls.id}>Compare Snapshots</Button>
                             <strong>Version: </strong> {cls.version} <strong>Snapshot:</strong> {cls.createdDateTime}
                        </Card.Body>
                    </Accordion.Collapse>);
        }

        return innerAccordion;

    }

    const handleClick = event => {
        console.log(event.target.value);
        console.log(diffs);
        console.log(allDiffs);
        console.log(allEntities);
        let selectedClass = allEntities.payload.entity.data.filter( p => p.id == event.target.value);
        console.log(selectedClass);
        if (selectedClass.lenth === 0) return null;
        selectedClass = selectedClass[0];
        setPID(event.target.value);
        setProject1(selectedClass);
        console.log(selectedClass);
        let original = allEntities.payload.entity.data.filter(p => p.name == selectedClass.name && p.version == 1);
        original = original[0];
        setOriginal(original);
        
    }

    const createCompareComponent = () => {
        let localOriginal = {...original};
        let localProject1 = {...project1};
        delete localOriginal.data;
        delete localProject1.data;

        console.log(localOriginal);
        console.log(localProject1);
        return (
        <div>
            <CompareTwoResults version1={original} version2={project1} />
            {compareTwoJson(JSON.stringify(localOriginal), JSON.stringify(localProject1))}
        </div>)
    }

    return (
        <div>
            <h1>Classes: </h1>
            {loading || !sortedProjectMap ? (<div class = "a"> Loading... </div>) :
                buildAccordian(sortedProjectMap)
                
                }
        
        
        {project1 ? 
       createCompareComponent(): <p>Pick a Version</p>    
    
    }
        </div>
    );



}
