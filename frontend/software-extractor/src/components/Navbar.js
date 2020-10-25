
import React from 'react';

import { NavLink } from 'react-router-dom';

const Navigation = () => {
    return (
        <div id="nav">
            <ul>
                <li><a href="#">TEAM DML</a></li>
                <li><NavLink to="/">File Uploader</NavLink></li>
                <li><NavLink to="/result">Result</NavLink></li>
                <li><NavLink to="/List">File List</NavLink></li>
            </ul>


        </div>
    );
}

export default Navigation;