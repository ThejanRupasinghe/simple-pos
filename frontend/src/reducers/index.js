import {combineReducers} from "redux";
// import authReducer from './authReducer';
import itemReducer from './itemReducer';
import orderReducer from './orderReducer';

export default combineReducers({
    itemReducer: itemReducer,
    // auth: authReducer,
    orderReducer: orderReducer
});