import React, { useState, useEffect } from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import FormGroup from 'react-bootstrap/esm/FormGroup';
import axios from 'axios';

const UPLOAD_ENDPOINT = 'http://localhost:8080/upload';

const styles = {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    justifyContent: 'center'
};


const range = {

};


const FileUploader = () => {
    // Starting with one file for testing then using multiple
    const [files, setFiles] = useState('');
    const [fileName, setFileName] = useState('');

    const onChange = e => {
        setFiles(e.target.files);
        // setFileName(e.target.files.name);
    }


    const onSubmit = async e => {
        e.preventDefault();
        console.log("Submitting data");
        console.log("File: ", files);
        const formData = new FormData();
        for (let file of files) {
            formData.append('files', file);
        }
        // formData.append('files', file);

        try {
            const res = await axios.post(UPLOAD_ENDPOINT, formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            });
        } catch (err) {
            console.error(err);
        }
        window.location.replace("/result");
    }

    return (
        <div class="range">
        <Form onSubmit={ onSubmit } onChange={ onChange }>
        <Row style={styles}>
                <Col>
                    <Form.Group>
                        <Form.File 
                            id="fileUpload"
                            label={fileName ? fileName: "Upload Source Files"}
                            multiple
                        />
                    </Form.Group>
                </Col>
                <Col>
                    <Form.Group>
                        <Button type="submit">Submit Files</Button>
                    </Form.Group>
                </Col>
        </Row>
        </Form>
        </div>
    );
};

export default FileUploader;
