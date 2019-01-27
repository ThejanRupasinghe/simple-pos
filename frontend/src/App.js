import React from 'react';
import {BrowserRouter, Switch, Redirect, Link, Route} from 'react-router-dom';
import AppNavBar from './components/AppNavBar';
import OrderTable from './components/OrderTable'

import {Provider} from 'react-redux';
import store from './store';

import "bootstrap/dist/css/bootstrap.min.css"
import './App.css';
import SignInForm from "./components/SignInForm";
import {isLoggedIn} from "./utils/authUtils";

class App extends React.Component {
    render() {
        return (
            <Provider store={store}>
                <BrowserRouter>
                    <div className="App">
                        <AppNavBar/>
                        <Switch>
                            <Route exact path="/" render={() => (
                                isLoggedIn() ? (
                                    <Redirect to="/orders"/>
                                ) : (
                                    <Redirect to="/signin"/>
                                )
                            )}/>
                            <Route exact path="/orders" render={() => (
                                isLoggedIn() ? (
                                    <OrderTable/>
                                ) : (
                                    <Redirect to="/signin"/>
                                )
                            )}/>
                            <Route exact path="/signin" render={() => (
                                isLoggedIn() ? (
                                    <Redirect to="/orders"/>
                                ) : (
                                    <SignInForm/>
                                )
                            )}/>
                            {/*<Route component={PageNotFound}/>*/}
                        </Switch>
                    </div>
                </BrowserRouter>
            </Provider>
        );
    }
}

export default App;
