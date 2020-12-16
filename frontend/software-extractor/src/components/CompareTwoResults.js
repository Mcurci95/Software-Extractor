import React from 'react';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import ClassComponent from './ClassComponent';
// import SplitPane from "react-split-pane";

class CompareTwoResults extends React.Component {


    render() {

        return (


            <Container>
                <h1>Compare Versions: </h1>
                <Row>
                    
                    <Col style= {{border: '2px solid #000000'}}>
                        <h1>Pre-Maintenance ID: {this.props.version1.id}</h1>
                        <ClassComponent key={this.props.version1.name} {...this.props.version1} />
                    </Col>

                    <Col style={{border: '2px solid #000000'}}>
                    <h1>Post-Maintenance ID: {this.props.version2.id}</h1>
                    <ClassComponent key={this.props.version2.name} {...this.props.version2} />
                    </Col>
                </Row>
            </Container>
        );
    }
}

export default CompareTwoResults;