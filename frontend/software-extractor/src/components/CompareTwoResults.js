import React from 'react';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
// import SplitPane from "react-split-pane";

const SERVER_ENDPOINT = 'http://localhost:8080/allClasses';


class CompareTwoResults extends React.Component {


    render() {
        return (


            <Container>
                <h1>Compare Versions: </h1>
                <Row>
                    <Col style= {{border: '2px solid #000000'}}>{this.props.version1}</Col>
                    <Col style={{border: '2px solid #000000'}}>{this.props.version2}</Col>
                </Row>
            </Container>
            // <div>
            //     {/* <SplitPane split='vertical' minSize={50} defaultSize={100}>
            //         <div>Split 1</div>
            //         <div>Split 2</div>
            //     </SplitPane> */}
            // </div>


        );
    }
}

export default CompareTwoResults;