import {GET_ORDERS, ADD_ORDER, DELETE_ORDER, UPDATE_ORDER, GET_ITEMS} from "../actions/types";

export const getOrders = () => {
    return {
        type: GET_ORDERS
    };
};