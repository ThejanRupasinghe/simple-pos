import React from 'react';
import {BrowserRouter, Switch, Redirect, Link, Route} from 'react-router-dom';
import AppNavBar from './components/AppNavBar';
import OrderTable from './components/OrderTable'

import {Provider} from 'react-redux';
import store from './store';

import "bootstrap/dist/css/bootstrap.min.css"
import './App.css';
import LoginForm from "./components/LoginForm";

class App extends React.Component {
    render() {
        return (
            <Provider store={store}>
                <BrowserRouter>
                    <div className="App">
                        <AppNavBar/>

                        {/*Routing goes here*/}
                        {/*<Switch>*/}
                        {/*<Route exact path="/" render={() => (*/}
                        {/*loggedIn ? (*/}
                        {/*<Redirect to="/orders"/>*/}
                        {/*) : (*/}
                        {/*<Redirect to="/login"/>*/}
                        {/*)*/}
                        {/*)}/>*/}
                        {/*<Route exact path="/orders" render={() => (*/}
                        {/*loggedIn ? (*/}
                        {/*<OrderView/>*/}
                        {/*) : (*/}
                        {/*<Redirect to="/login"/>*/}
                        {/*)*/}
                        {/*)}/>*/}
                        {/*<Route exact path="/login" render={() => (*/}
                        {/*loggedIn ? (*/}
                        {/*<Redirect to="/orders"/>*/}
                        {/*) : (*/}
                        {/*<LoginForm/>*/}
                        {/*)*/}
                        {/*)}/>*/}
                        {/*<Route component={PageNotFound} />*/}
                        {/*</Switch>*/}

                        <OrderTable/>
                    </div>
                </BrowserRouter>
            </Provider>
        );
    }
}

export default App;
