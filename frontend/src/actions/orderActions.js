import axios from 'axios';
import {GET_ORDERS, ADD_ORDER, DELETE_ORDER, UPDATE_ORDER, GET_ITEMS, ORDERS_LOADING} from "../actions/types";

export const getOrders = () => {
    return {
        type: GET_ORDERS
    };
};

export const deleteOrder = () => {
  return {
      type: DELETE_ORDER
  };
};

export const setOrdersLoading = () => {
    return {
        type: ORDERS_LOADING
    }
};