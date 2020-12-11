import React, { Component }  from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';

import { BrowserRouter, Route, Switch } from 'react-router-dom';

import CompareResult from '../src/components/CompareResult';
import ExtractResult from '../src/components/ExtractResult';
import FileUploader from '../src/components/FileUploader';
import UploadedList from '../src/components/UploadedList';
import Navigation from "../src/components/Navbar";
import CompareTwoResults from "../src/components/CompareTwoResults";
// const PACKAGES_ENDPOINT = 'http://localhost:8080/packages';


class App extends Component {
    render() {
        return (
            <BrowserRouter>
                <div>
                    <Navigation />
                    <Switch>
                        <Route path="/" component={FileUploader} exact/>
                        <Route path="/list" component={UploadedList}/>
                        <Route path="/result" component={ExtractResult}/>
                        <Route path="/compare" component={CompareResult}/>
                        <Route path="/comparetwo" component={CompareTwoResults}/>
                    </Switch>
                </div>
            </BrowserRouter>
        );
    }
}

export default App;
