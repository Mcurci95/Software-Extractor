import React, { Component }  from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';

import { BrowserRouter, Route, Switch } from 'react-router-dom';

import CompareResult from '../src/components/CompareResult';
import FileUploader from '../src/components/FileUploader';
import UploadedList from '../src/components/UploadedList';
import Navigation from "../src/components/Navbar";
import CompareTwoResults from "../src/components/CompareTwoResults";
import ClassComponentContainer from '../src/components/ClassComponentContainer';
import HomePage from '../src/components/HomePage';


class App extends Component {
    render() {
        return (
            <BrowserRouter>
                <div>
                    <Navigation />
                    <Switch>
                        <Route path="/upload" component={FileUploader} exact/>
                        <Route path="/list" component={UploadedList}/>
                        <Route path="/result" component={ClassComponentContainer}/>
                        <Route path="/compare" component={CompareResult}/>
                        <Route path="/comparetwo" component={CompareTwoResults}/>
                        <Route path="/" component={HomePage} />
                    </Switch>
                </div>
            </BrowserRouter>
        );
    }
}

export default App;
