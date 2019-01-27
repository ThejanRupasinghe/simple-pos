import { AUTHENTICATE, SIGNOUT } from "./types";

export const authenticateUser = (user, success) => dispatch => {
    fetch("/api/auth/signin", {
        headers: {
            "Content-Type": "application/json",
            "cache-control": "no-cache",
        },
        credentials: "include",
        method: 'post',
        body: JSON.stringify({ username: user.username, password: user.password })

    }).then((fetchResponse) => {
        if (fetchResponse.status === 200) {
            localStorage.setItem("simplePOSAuth", "true");
            success();
        } else {
            localStorage.setItem("simplePOSAuth", "false");
        }
        dispatch({
            type: AUTHENTICATE,
            payload: fetchResponse.data
        })
    }).catch();
};

export const signOutUser = () => dispatch => {
    console.log("in signout");
    fetch("/api/auth/signout", {
        headers: {
            "Content-Type": "application/json",
            "cache-control": "no-cache",
        },
        credentials: "include",
        method: 'post',
        body: JSON.stringify({})
    }).then((fetchResponse) => {
        if (fetchResponse.status === 200) {
            localStorage.setItem("simplePOSAuth", "false");
        }
        dispatch({
            type: SIGNOUT,
            payload: fetchResponse.data
        })
    })
}