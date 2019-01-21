import {GET_ORDERS, ADD_ORDER, DELETE_ORDER, UPDATE_ORDER} from "../actions/types";

const initialState = {
    items: []
};

export default function (state = initialState, action) {
    switch (action.type) {
        case GET_ORDERS:
            return {
                ...state
            };
        default:
            return state;
    }
}