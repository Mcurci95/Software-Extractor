import React from 'react'
import ExtractResult from './ExtractResult'
import ExtractDiff from './ExtractDiff'

export default function HomePage() {
  return (
    <>
      <div className="container">
        <h4>Welcome to Software Extractor!</h4>
        <p>Made by:</p>
        <ul>
          <li>Dennis Do</li>
          <li>Marco Curci</li>
          <li>Liam Nguyen</li>
        </ul>
      <hr></hr>
      <div>
        <h6>Loading Data from Source</h6>
        <ExtractResult />
      </div>
      <hr></hr>
      <div>
        <h6>Extracting Diff</h6>
        <ExtractDiff />
      </div>
      </div>

      
    </>
  )
}