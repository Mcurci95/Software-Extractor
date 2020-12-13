import React from 'react';
import Accordion from 'react-bootstrap/Accordion'
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import CompareTwoResults from './CompareTwoResults';
import CompareComponent from './CompareComponent';
import ClassComponent from './ClassComponent';
import PRIMITIVE_TYPES from './ExtractResult';
import _isPrimitive from './ExtractResult';

const SERVER_ENDPOINT = 'http://localhost:8080/allClasses';


class testing extends React.Component{
    constructor(props) {
        super(props);
        this.handleClick = this.handleClick.bind(this);
        this.state = {
            loading : true,
            project: null,
            project1: null,
            project2: null
        };
    }
    
    
    buildAccordian(classDict) {

        let accordionArray = [];
        console.log(classDict);
        for (const cls of Object.keys(classDict)) {
            let p = classDict[cls];
            console.log(p);
            accordionArray.push(<Accordion key={p[0].id}>
                <Card>
                    <Card.Header>
                        <Accordion.Toggle as={Button} variant="link" eventKey="0">
                            {p[0].name}
                        </Accordion.Toggle>
                    </Card.Header>
                    {this.buildInner(classDict[cls])}

                </Card>
                </Accordion>)
        }
        
        console.log(accordionArray);
        return accordionArray;

        // classDict.map(p => {
        //     return  (
        //         <Accordion key={p.id}>
        //         <Card>
        //             <Card.Header>
        //                 <Accordion.Toggle as={Button} variant="link" eventKey="0">
        //                     {p.name}
        //                 </Accordion.Toggle>
        //             </Card.Header>
        //             {p.map(c => this.buildInner(c))}

        //         </Card>
        //         </Accordion>
        //     )
        // })
    }

    buildInner(c) {
        let innerAccordion = [];
        console.log(c);
        for (let _cls of Object.keys(c))
        {
            let cls = c[_cls];
            console.log(cls);
            innerAccordion.push(<Accordion.Collapse eventKey="0">
                        <Card.Body>
                            <Button variant="outline-primary" onClick = {this.handleClick} value={cls.id}>Compare Snapshots</Button>
                             <strong>Version: </strong> {cls.version} <strong>Snapshot:</strong> {cls.createdDateTime}
                        </Card.Body>
                    </Accordion.Collapse>);
        }

        return innerAccordion;

    }


    handleClick(event) {
        console.log(event.target.value);
        this.setState({project1: event.target.value});
    }


    createCompareComponent() {
        let selectedClass = this.state.project.filter( p => p.id == this.state.project1);
        if (selectedClass.lenth === 0) return null;
        selectedClass = selectedClass[0];

        return <CompareComponent key={selectedClass.id} {...selectedClass} />
    }

    async componentDidMount() {
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
        console.log(sortedProjectMap);
        this.setState({project: data, loading : false, sortedProjectMap: sortedProjectMap});
        console.log(data);
        
        // console.log([...sortedProjectMap]);
    };

    createCompareComponent() {
        let selectedClass = this.state.project.filter(p => p.name === this.state.project1);
        if (selectedClass.length === 0) return null;
        selectedClass = selectedClass[0];

        return <CompareComponent key={selectedClass.id} {...selectedClass} />
    }

    render() {
        return (
            <div>
                <h1>Classes: </h1>
                {this.state.loading || !this.state.sortedProjectMap ? (<div class = "a"> Loading... </div>) :
                    this.buildAccordian(this.state.sortedProjectMap)
                    
                    }
            
            <CompareTwoResults version1="" version2="" />
            {this.state.project1 ? 
            this.createCompareComponent(): <p>Loading...</p>    
        
        }
            </div>
        );
    }
}
//     render() {
//         return(
//             <div>
//                 <div className="a">
//                     List of files uploaded:
//                     <ul>
//                     {this.state.loading || !this.state.project ? (<div class = "a"> Loading... </div>) :
//                         (
//                             (this.state.project.map((project) =>
//                                 <div>
//                                     <li>{project.id}</li>
//                                     <li>{project.name}</li>
//                                     </div>
//                             ))
//                         )
//                     }
//                     </ul>
//                 </div>
//             </div>
//         )
//     }
// }

export default testing;


// this.state.project.map( (p) => 
// <Accordion key={p.id}>
// <Card>
//     <Card.Header>
//         <Accordion.Toggle as={Button} variant="link" eventKey="0">
//             {p.name}
//         </Accordion.Toggle>
//     </Card.Header>
//     <Accordion.Collapse eventKey="0">
//         <Card.Body>
//             <Button variant="outline-primary" onClick = {this.handleClick} value={p.id}>Compare Snapshots</Button>
//              <strong>Version: </strong> {p.version} <strong>Snapshot:</strong> {p.createdDateTime}
//         </Card.Body>
//     </Accordion.Collapse>
// </Card>
// </Accordion>