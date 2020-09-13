import React, { useState, useEffect } from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
const FileUploader = () => {
    return (
    <Container fluid="md">

        
        <Form className="mb-3">
            <Form.Group>
                <Form.File id="uploadFile" />
                <Button> 
                    Submit File
                </Button>
            </Form.Group>
        </Form>
        
    </Container>
    );
};

export default FileUploader;
