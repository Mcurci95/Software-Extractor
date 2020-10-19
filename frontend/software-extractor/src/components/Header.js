import React, { Component }  from 'react';

import { BrowserRouter, Route, Switch } from 'react-router-dom';


const Header = () =>{

    // return (
    //     <div id="nav">
    //       <ul>
    //           <li><a href="#">Team DML</a></li>
    //           <li><Link to="/ExtractResult">Result</Link></li>
    //       </ul>
    //     </div>
    // );
    return (
        <div>
            <h1>Home</h1>
            <p>Home page body content</p>
        </div>
    );
}

export default Header;
