import {GET_ORDERS, ADD_ORDER, DELETE_ORDER, UPDATE_ORDER} from "../actions/types";

const initialState = {
    orders: [
        {id: 1, date: "Date1", total: "$50"},
        {id: 2, date: "Date2", total: "$80"},
        {id: 3, date: "Date3", total: "$120"},
        {id: 4, date: "Date4", total: "$70"},
        {id: 5, date: "Date5", total: "$35"},
    ]
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