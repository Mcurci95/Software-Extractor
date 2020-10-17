import React from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';


import Header from '../src/components/Header';
import FileUploader from '../src/components/FileUploader';

function App() {
  return (

    <div className="App">
        <Header />
        <div>
        <FileUploader />

        </div>
    </div>

  );
}

export default App;
