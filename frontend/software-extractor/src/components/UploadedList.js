import React, { useState, useEffect } from 'react';
import Accordion from 'react-bootstrap/Accordion'
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import CompareTwoResults from './CompareTwoResults';
import CompareComponent from './CompareComponent';
import ClassComponent from './ClassComponent';
import selectDiff from '../reducers/entityReducer';
import {useSelector} from 'react-redux';

const SERVER_ENDPOINT = 'http://localhost:8080/allClasses';

// export default function ExtractResult()

export default function UploadList() {
    const [loading, setLoading] = useState(true);
    const [project, setProject] = useState(null);
    const [project1, setProject1] = useState(null);
    const [sortedProjectMap, setSortedProjectMap] = useState(null);


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
        
        console.log(accordionArray);
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
        this.setState({project1: event.target.value});
        setProject1(event.target.value);
    }

    const createCompareComponent = () => {
        let selectedClass = this.state.project.filter( p => p.id == this.state.project1);
        if (selectedClass.lenth === 0) return null;
        selectedClass = selectedClass[0];

        return <CompareComponent key={selectedClass.id} {...selectedClass} />
    }

    return (
        <div>
            <h1>Classes: </h1>
            {loading || !sortedProjectMap ? (<div class = "a"> Loading... </div>) :
                buildAccordian(sortedProjectMap)
                
                }
        
        <CompareTwoResults version1="" version2="" />
        {project1 ? 
        createCompareComponent(): <p>Loading...</p>    
    
    }
        </div>
    );



}

// class testing extends React.Component {
//     constructor(props) {
//         super(props);
//         this.handleClick = this.handleClick.bind(this);
//         this.state = {
//             loading : true,
//             project: null,
//             project1: null,
//             project2: null
//         };
//     }
    
    
//     buildAccordian(classDict) {

//         let accordionArray = [];
//         for (const cls of Object.keys(classDict)) {
//             let p = classDict[cls];
//             accordionArray.push(<Accordion key={p[0].id}>
//                 <Card>
//                     <Card.Header>
//                         <Accordion.Toggle as={Button} variant="link" eventKey="0">
//                             {p[0].name}
//                         </Accordion.Toggle>
//                     </Card.Header>
//                     {this.buildInner(classDict[cls])}

//                 </Card>
//                 </Accordion>)
//         }
        
//         console.log(accordionArray);
//         return accordionArray;

//     }

//     buildInner(c) {
//         let innerAccordion = [];
        
//         for (let _cls of Object.keys(c))
//         {
//             let cls = c[_cls];
            
//             innerAccordion.push(<Accordion.Collapse eventKey="0">
//                         <Card.Body>
//                             <Button variant="outline-primary" onClick = {this.handleClick} value={cls.id}>Compare Snapshots</Button>
//                              <strong>Version: </strong> {cls.version} <strong>Snapshot:</strong> {cls.createdDateTime}
//                         </Card.Body>
//                     </Accordion.Collapse>);
//         }

//         return innerAccordion;

//     }


//     handleClick(event) {
//         console.log(event.target.value);
//         this.setState({project1: event.target.value});
//     }


//     createCompareComponent() {
//         let selectedClass = this.state.project.filter( p => p.id == this.state.project1);
//         if (selectedClass.lenth === 0) return null;
//         selectedClass = selectedClass[0];

//         return <CompareComponent key={selectedClass.id} {...selectedClass} />
//     }

//     async componentDidMount() {
//         const response = await fetch(SERVER_ENDPOINT);
//         let data = await response.json();
//         data.sort( (a,b) => (a.name > b.name) ? 1 : -1) ;
//         let sortedProjectMap = {};
//         for (let i = 0; i < data.length; i++) {
//             if (sortedProjectMap.hasOwnProperty(data[i].name)) {
//                 sortedProjectMap[data[i].name].push(data[i]);
//             } else {
//                 sortedProjectMap[data[i].name] = [data[i]];
//             }
//         }

//         const selectedDataTest = useSelector(selectDiff);
//         console(selectedDataTest);
//         console.log(sortedProjectMap);
//         this.setState({project: data, loading : false, sortedProjectMap: sortedProjectMap});
//         console.log(data);
//     };

//     createCompareComponent() {
//         let selectedClass = this.state.project.filter(p => p.name === this.state.project1);
//         if (selectedClass.length === 0) return null;
//         selectedClass = selectedClass[0];

//         return <CompareComponent key={selectedClass.id} {...selectedClass} />
//     }

//     render() {
//         return (
//             <div>
//                 <h1>Classes: </h1>
//                 {this.state.loading || !this.state.sortedProjectMap ? (<div class = "a"> Loading... </div>) :
//                     this.buildAccordian(this.state.sortedProjectMap)
                    
//                     }
            
//             <CompareTwoResults version1="" version2="" />
//             {this.state.project1 ? 
//             this.createCompareComponent(): <p>Loading...</p>    
        
//         }
//             </div>
//         );
//     }
// }

// export default testing;