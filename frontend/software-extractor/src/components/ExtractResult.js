import React from 'react';

const SERVER_ENDPOINT = 'http://localhost:8080/packages';


class testing extends React.Component{
    state = {
        loading : true,
        project: null,
    };

    async componentDidMount() {
        const url = "https://api.randomuser.me/";
        const response = await fetch(url);
        const data = await response.json();
        this.setState({project: data.results[0], loading : false})
        console.log(data);
    }

    render() {
        return(
            <div>
                {this.state.loading || !this.state.project ? (<div class = "a"> Loading... </div>) :
                (<div class="a">
                    {this.state.project.name.first} {/* program class name*/}
                    <div className="b">
                        Ancestors:
                        <div className="c">
                            {this.state.project.name.first}
                        </div>
                    </div>
                    <div className="b">
                        Desendants:
                        <div className="c">
                        {!this.state.project.name.first? (<div>No decendants</div>):(this.state.project.name.first)}
                        </div>
                    </div>
                    <div className="b">
                        Aggreages:
                        <div className="c">
                            {!this.state.project.name.first? (<div>NONE</div>):(this.state.project.name.first)}
                        </div>
                    </div>
                    <div className="b">
                        Data Members:
                        <div className="c">
                            {!this.state.project.name.first? (<div>NONE</div>):(this.state.project.name.first)}
                        </div>
                    </div>
                    <div className="b">
                        Method Members:
                        <div className="c">
                            {!this.state.project.name.first? (<div>NONE</div>):
                                (this.state.project.name.first + " returns " + this.state.project.name.first + " is " + this.state.project.name.first)}
                            <div>
                                {!this.state.project.name.first? (<div></div>):
                                    ("Parameters: " + this.state.project.name.first )}
                            </div>
                            <div>

                            {!this.state.project.name.first? (<div></div>):
                                ("Local Vairables: " + this.state.project.name.first )}
                            </div>
                            <div>

                            {!this.state.project.name.first? (<div></div>):
                                ("Calls Methods " + this.state.project.name.first)}
                            </div>
                        </div>
                    </div>
                </div>) }
            </div>
        )
    }
}

export default testing;