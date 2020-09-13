import React, { useState, useEffect } from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import FormGroup from 'react-bootstrap/esm/FormGroup';

const styles = {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    flexDirection: 'column'
};

const FileUploader = () => {
    return (
        <div style={styles}>
        <Form>
        <Row>
                <Col>
                    <Form.Group>
                        <Form.File 
                            id="fileUpload"
                            label="Upload Source Files"
                            custom
                        />
                    </Form.Group>
                </Col>
                <Col>
                    <Form.Group>
                        <Button>Submit Files</Button>
                    </Form.Group>
                </Col>
        </Row>
        </Form>
        </div>
    );
};

export default FileUploader;
