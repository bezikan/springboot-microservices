import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import config from './config.js';

import '../node_modules/bootstrap/dist/css/bootstrap.min.css';

const SERVER_NAME = config.SERVER_NAME;

class App extends Component {

  constructor(){
    super();
    this.state = {
      quoteValue: "AAPL",
      userNameValue: "bob",
      data: [],
      status: ""
    }

    this.handleChangeQuote = this.handleChangeQuote.bind(this);
    this.handleChangeUserName = this.handleChangeUserName.bind(this);
    this.addStockForUserName = this.addStockForUserName.bind(this);
    this.getQuotes = this.getQuotes.bind(this);

    this.clearQuotes = this.clearQuotes.bind(this);
    
  }

  handleChangeQuote(event) {
    this.setState({quoteValue: event.target.value});
  }
  
  handleChangeUserName(event) {
    this.setState({userNameValue: event.target.value});
  }

  clearQuotes(){
    let url = SERVER_NAME+"/api/db-service/rest/db/delete/"+this.state.userNameValue;
    
    fetch(url, {
      headers: {
        'content-type': 'application/json'
      },
      method: 'POST'
    }).then(function(response){
      response.json().then((data)=>{
        console.log(data);
        this.setState({status: "removed all quotes: " + data.toString()})
      })

    }.bind(this)).catch(function(err){
      console.log(err);
    })
  }

  getQuotes(){
    fetch(SERVER_NAME+"/api/stock-service/rest/stock/"+this.state.userNameValue,
  {
    // cache: 'no-cache',
    // mode: 'no-cors',
    // redirect: 'follow',
    headers: {
      'content-type': 'application/json'
    },
  })
    .then(function(response){
      let j = response.json();
      j.then(function(data){
        this.setState({data: data})
        // console.log(this.state.data);
      }.bind(this))
    }.bind(this))
    .catch(function(err){
        console.log(err);
    });
  }

  addStockForUserName(){
    let url = SERVER_NAME+"/api/db-service/rest/db/add";
    let data = {
        'userName': this.state.userNameValue,
        'quotes': [this.state.quoteValue]
          }

   fetch(url, {
      body: JSON.stringify(data), // must match 'Content-Type' header
      // cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
      // credentials: 'same-origin', // include, same-origin, *omit
      headers: {
        'content-type': 'application/json'
      },
      method: 'POST', // *GET, POST, PUT, DELETE, etc.
      // mode: 'cors', // no-cors, cors, *same-origin
      // redirect: 'follow', // manual, *follow, error
      // referrer: 'no-referrer', // *client, no-referrer
    }).then(function(response){
      response.json().then((data)=>{
        console.log(data);
      
        this.setState({status: "total quotes now: " + data.toString()})
      })

    }.bind(this)).catch(function(err){
      console.log(err);
    })

  } 
  

  render() {
    return (
  <div className="App container">
        
    <div className="row">

      <div className="col-6">
        <ul className="list-group">
        <li className="list-group-item">
        <label >
                Name:
                <input value={this.state.userNameValue} onChange={this.handleChangeUserName} />
              </label>
              <button onClick={this.getQuotes} >
                Get Quotes
              </button>
          </li>
        <li className="list-group-item">
        
        </li>
        <li className="list-group-item"></li>
        <li className="list-group-item"></li>
        <li className="list-group-item">
        <label >
                Quote:
                <input value={this.state.quoteValue} onChange={this.handleChangeQuote} />
              </label>
              <button onClick={this.addStockForUserName} >
                      Add Stock
                    </button>
        </li>
        <li className="list-group-item">
        
        { this.state.userNameValue !== "" ?
         <div>
          <button onClick={this.clearQuotes} >
                      clear quotes for {this.state.userNameValue}
          </button>
          </div> : <div> </div>}
        
        </li>
        </ul>
       
      </div>

      <div className="col-6">
        <ul className="list-group list-group-flush">

          {this.state.data.map( (x, index) => (
            <li className="list-group-item" key={index} >
              {x.quote} - {x.price}
              </li>
          ))}

          </ul>
      </div>

      { this.state.status === "" ?
       <div> "no stat" </div> :
      <div> {this.state.status} </div>
      }

    </div> 
  </div>
    );
  }
}

export default App;
