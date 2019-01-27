// import Cookies from 'universal-cookie/cjs';

//changed from cookie check to local storage check
//cookie is HttpOnly now
/**
 export const isLoggedIn = () => {
    const cookies = new Cookies();

    let cookie = cookies.get('simplePOSAuth');

    if (cookie !== undefined) {
        console.log(cookie);
        return true;
    } else {
        console.log("undefined");
        return false;
    }
};
 **/

export const isLoggedIn = () => {
    let auth = localStorage.getItem("simplePOSAuth");

    if (auth === undefined || auth === null || auth === "false") {
        return false;
    } else if (auth === "true") {
        return true;
    }
};