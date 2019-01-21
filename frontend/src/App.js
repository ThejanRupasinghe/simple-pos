import React from 'react';
import AppNavBar from './components/AppNavBar';
import OrderTable from './components/OrderTable'

import {Provider} from 'react-redux';
import store from './store';

import "bootstrap/dist/css/bootstrap.min.css"
import './App.css';

class App extends React.Component {
    render() {
        return (
            <Provider store={store}>
                <div className="App">
                    <AppNavBar/>
                    <OrderTable/>
                </div>
            </Provider>
        );
    }
}

export default App;
