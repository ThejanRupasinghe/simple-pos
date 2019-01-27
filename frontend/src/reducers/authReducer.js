import {AUTHENTICATE} from "../actions/types";

const initialState = {
    details: {}
};

export default function (state = initialState, action) {
    switch (action.type) {
        case AUTHENTICATE:
            return {
                ...state,
                auth: action.payload
            };
        default:
            return state;
    }
}