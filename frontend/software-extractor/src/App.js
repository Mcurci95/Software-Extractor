import React, { Component }  from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';

import { BrowserRouter, Route, Switch } from 'react-router-dom';


import ExtractResult from '../src/components/ExtractResult';
import FileUploader from '../src/components/FileUploader';
import Navigation from "../src/components/Navbar";


const PACKAGES_ENDPOINT = 'http://localhost:8080/packages';

class App extends Component {


    render() {
        return (
            <BrowserRouter>
                <div>
                    <Navigation />
                    <Switch>
                        <Route path="/" component={FileUploader} exact/>
                        <Route path="/result" component={ExtractResult}/>
                    </Switch>
                </div>
            </BrowserRouter>
        );
    }
}

export default App;
