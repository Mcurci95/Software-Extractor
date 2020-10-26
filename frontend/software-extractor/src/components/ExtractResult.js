import React from 'react';

const SERVER_ENDPOINT = 'http://localhost:8080/allClasses';

async function fetchparent(){
    const response = await fetch('http://localhost:8080/parents?name=Base' );
    console.log(response);
}

class testing extends React.Component{
    state = {
        loading : true,
        project: null,
    };

    async componentDidMount() {
        const response = await fetch(SERVER_ENDPOINT);
        const data = await response.json();

        const newData = [];
        for (const cls of data) {
            const parent = cls.parent;
            let longname = cls.name;
            if (parent != null) {
                const res = await fetch('http://localhost:8080/parents?name='+parent);
                const descendant = await res.json();
                cls['descendant'] = descendant;
                longname = longname + ' extends ' + parent;

            }else{
                cls['descendant'] = null
            }

            const implement = cls.implementInterfaces;

            if(implement.length > 0){
                longname = longname + ' implements ' + implement[0].name
            }
            cls['longname'] = longname;
            newData.push(cls);
        }
        console.log(newData)
        this.setState({project:data, loading : false});

    };




    render() {
        return(

            <div>
                {this.state.loading || !this.state.project ? (<div class = "a"> Loading... </div>) :
                (

                    (this.state.project.map((project) =>

                    <div class="a">
                    Class name:
                    <span> {project.longname} </span>
                    {project.parent == null? (null):(
                        <div className="b">
                            Ancestors:
                            <div className="c">
                                {project.parent}
                            </div>
                        </div>
                    )}

                    <div className="b">
                        Desendants:
                        <div className="c">

                        {project.childClasses.length == 0 ? (<div>No child</div>):(

                            project.childClasses.map((child)=>
                                <div>child.name</div>
                            )
                        )}
                        {project.descendant == null ? (<div>No parents</div>):(

                            project.descendant.map((pare)=>
                                <div>{pare}<br /></div>
                            )

                        )}

                        {!project.mAccess? (<div></div>):
                            (project.mAccess.map((access) =>
                                {
                                    if(access.name == 'final'){
                                        return <div>is final<br /></div>
                                    }
                                    if(access.name == 'static'){
                                        return <div>is static<br /></div>
                                    }
                                    if(access.name == 'abstract'){
                                        return <div>is abstract<br /></div>
                                    }
                                })
                            )
                        }

                        </div>
                    </div>
                    <div className="b">
                        Aggregates:
                        {!project.mMethods? (<div>NONE</div>):(
                            project.mMethods.map((meth) =>
                                <div className="c">
                                    {/*{!meth.mParameters? (<div>NONE</div>):(*/}
                                    {/*    meth.mParameters.map((type)=>*/}
                                    {/*        <div>{type.mType.name}<br /></div>*/}
                                    {/*    )*/}
                                    {/*)}*/}
                                    {meth.mReturnType.name == 'int' || meth.mReturnType.name == 'void' ? (null):(
                                        <div>{meth.mReturnType.name}<br /></div>
                                    )}
                                </div>
                            ))}
                    </div>

                        <div className="b">
                        Associates:
                        {!project.mMethods? (<div></div>):(
                            project.mMethods.map((meth) =>
                                <div className="c">
                                    {!meth.mParameters? (<div>NONE</div>):(
                                        meth.mParameters.map((type)=>
                                            <div>{type.mType.name}<br /></div>
                                        )
                                    )}
                                    {!meth.mBody.variables? (<div>NONE</div>):(
                                        meth.mBody.variables.map((vairable)=>
                                            <div>{vairable.value}<br /></div>
                                        )
                                    )}
                                </div>
                            ))}

                        </div>
                                <div className="b">
                                    Data Members:

                                    {!project.mClassDataMembers? (<div></div>):(
                                        project.mClassDataMembers.map((datamember) =>
                                    <div className="c">
                                        {datamember.length = 0 ? (<div>NONE</div>):(
                                            datamember.mAccess.map((accessname)=>
                                                <span> {accessname.name} </span>
                                            )
                                        )}
                                        <span> {datamember.mType.name} </span>

                                        <span> {datamember.name} </span>

                                    </div>
                                        ))}
                                </div>

                    <div className="b">
                    Method Members:
                    {!project.mMethods? (<div></div>):
                        (project.mMethods.map((method) =>
                            <div className="c">
                                {!project? (<div>NONE</div>):
                                    (   <div>
                                        <span> {method.name} </span> <span> returns </span>  <span> {method.mReturnType.name}  </span> <span>  is  </span>
                                            {project.mAccess.map((accessnew) =>
                                                <span>  {accessnew.name}  </span>
                                            )}
                                        </div>
                                    )}
                                <div>
                                    {!method.mParameters? (<div></div>):
                                        (method.mParameters.map((para) =>
                                            <div>Parameters: {para.mType.name} {para.name}<br /></div>
                                        ))}
                                </div>
                                <div>

                                    {!method.mBody.variables? (<div></div>):
                                        (method.mBody.variables.map((vari) =>
                                            <div>Local Vairables: {vari.value} {vari.name}<br /></div>
                                        ))}
                                </div>
                                <div>

                                    {!method.mBody.methodCalls? (<div></div>):
                                        (method.mBody.methodCalls.map((call) =>
                                            <div>Calls Methods: {call.owner}.{call.name}<br /></div>))}
                                </div>
                            </div>
                        ))}
                    </div>
                </div>

                )))}
            </div>
        )
    }
}

export default testing;