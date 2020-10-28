import React from 'react'

export default function ClassComponent(props) {
  const ancestorBlock = () => props.ancestor.length === 0 
                                ? (null)
                                :( <div className="b">
                                            Ancestors:
                                            {props.ancestor.map(
                                                each => <div className="c">{each}</div>)
                                            }
                                  </div>)

  const descendantBlock = () => 
    <div className="b"> Descendants:
      <div className="c">
      {props.childClasses.length === 0 
        ? (<div>NONE</div>)
        :(props.childClasses.map(name=>
              <div key={name}>{name}</div>)
      )}
      </div>
      
    </div>

    const accessBlock = () => !props.mAccess 
                            ? (<div className="b"></div>):
                              <>
                              { props.mAccess.map(access =>
                                <div key={access.name} className="b">{`is ${access.name}`}</div>)
                              }
                              </>
              
          

  const aggregateBlock = () => <div className="b">
                              Aggregates:
                              {props.aggregate.length === 0 ? (<div className="c"> NONE</div>):(
                                  props.aggregate.map(type =>
                                      <div key={type} className="c">
                                          {type}
                                      </div>
                                  ))}
                                </div>

  const associateBlock = () => <div className="b">
              Associates:
              {props.associate.length === 0 ? (<div className="c">NONE</div>):(
                  props.associate.map(name =>
                      <div key={name} className="c">
                          {name}
                      </div>
                  ))}
              </div>

  const dataMemberBlock = () => <div className="b">
                          Data Members:
                          {!props.mClassDataMembers? (<div></div>):(
                              props.mClassDataMembers.map((datamember, i) =>
                          <div key={i} className="c">
                              {datamember.length = 0 
                              ? (<div>NONE</div>)
                              : (datamember.mAccess.map(accessname =>
                                      <span key={accessname.name}>{accessname.name} </span>
                                  )
                              )}
                              <span>{!('value' in datamember) 
                                        ? "null" 
                                        : datamember.value}</span>
                              <span> {datamember.mType.name} </span>
                              <span> {datamember.name} </span>
                          </div>
                              ))}
                      </div>

  const constructorBlock = () => <div className="b">
      Constructors: 
      {
          props.mConstructors.length != 0 ? props.mConstructors.map(constructor => 
              <div key={constructor.name} className="c">
                  {constructor.name} returns constructor is {constructor.type.name === null ? "public" : constructor.type.name}
                  {constructor.parameters.map(param => 
                      <div key={param.name} className="d">
                          Parameter: {param.mType.name} {param.name}
                      </div>
                  )} 
              </div>
          )
           : "NONE"
      }
  </div>

  const methodBlock = () => <div className="b">
          Method Members:
          {!props.mMethods? (<div></div>):
              (props.mMethods.map((method) =>
                  <div key={method.name} className="c">
                      {!props? (<div>NONE</div>):
                          (<div>
                              <span> {method.name} </span> <span> returns </span>  <span> {method.mReturnType.name}  </span> <span>  is  </span>
                                  {props.mAccess.map((accessnew) =>
                                      <span key={accessnew.name}>  {accessnew.name}  </span>
                                  )}
                              </div>
                          )}
                      <div className="d">
                          {!method.mParameters? (<div></div>):
                              (method.mParameters.map((para) =>
                                  <div key={para.mType.name}>Parameters: {para.mType.name} {para.name}<br /></div>
                              ))}
                      </div>
                      <div className="d">

                          {!method.mBody.variables? (<div></div>):
                              (method.mBody.variables.map((vari) =>
                                  <div key={vari.name}>Local Vairables: {vari.value} {vari.name}<br /></div>
                              ))}
                      </div>
                      <div className="d">

                          {!method.mBody.methodCalls? (<div></div>):
                              (method.mBody.methodCalls.map((call) =>
                                  <div key={call.name}>Calls Methods: {call.owner}.{call.name}<br /></div>))}
                      </div>
                  </div>
              ))}
          </div>

  return <div className="a"> Class name: <span> {props.name} </span>
          {ancestorBlock()}
          {descendantBlock()}
          {accessBlock()}
          {aggregateBlock()}
          {associateBlock()}
          {dataMemberBlock()}
          {constructorBlock()}
          {methodBlock()}
      </div>
}