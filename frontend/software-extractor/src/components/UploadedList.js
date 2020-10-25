import React from 'react';

const SERVER_ENDPOINT = 'http://localhost:8080/distinctClasses';

class testing extends React.Component{
    state = {
        loading : true,
        project: null,
    };

    async componentDidMount() {
        const response = await fetch(SERVER_ENDPOINT);
        const data = await response.json();

        this.setState({project: data, loading : false});

        // console.log(data);
    };



    render() {
        return(
            <div>
                {this.state.loading || !this.state.project ? (<div class = "a"> Loading... </div>) :
                    (
                        (this.state.project.map((project) =>

                            <div class="a">
                                {project}

                            </div>
                        )))}
            </div>
        )
    }
}

export default testing;