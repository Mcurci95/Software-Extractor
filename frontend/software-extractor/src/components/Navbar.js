import React from 'react';
import NavBar from 'react-bootstrap/Navbar';
import Nav from 'react-bootstrap/Nav';
const Navbar = () => {
    return (
        <NavBar bg="dark">
            <NavBar.Brand>Software-Extractor</NavBar.Brand>
            <Nav className="mr-auto">
                <Nav.Link>Projects</Nav.Link>
                <Nav.Link>Upload</Nav.Link>
            </Nav>
        </NavBar>
    )
};

export default Navbar;