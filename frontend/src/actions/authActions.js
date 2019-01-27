import {AUTHENTICATE} from "./types";

export const authenticateUser = (user) => dispatch => {
    fetch("/api/auth/signin", {
        headers: {
            "Content-Type": "application/json",
            "cache-control": "no-cache",
        },
        credentials: "include",
        method: 'post',
        body: JSON.stringify({username: user.username, password: user.password})

    }).then((fetchResponse) => {
        console.log(fetchResponse);
        if (fetchResponse.status === 200) {
            localStorage.setItem("simplePOSAuth", "true");
        } else {
            localStorage.setItem("simplePOSAuth", "false");
        }
        dispatch({
            type: AUTHENTICATE,
            payload: fetchResponse.data
        })
    }).catch();
};