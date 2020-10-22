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
                {this.state.loading || !this.state.project ? (<div> Loading </div>) :
                    (<div> {this.state.project.name.first} </div>) }
            </div>
        )
    }
}

export default testing;