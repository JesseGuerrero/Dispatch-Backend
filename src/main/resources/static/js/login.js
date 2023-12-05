let isLogin = true;

function login() {
    if (!isLogin) {
        isLogin = true;
        document.getElementById("login-block").style.display = "block";
        document.getElementById("signup-block").style.display = "none";
        document.getElementById(responseId).innerText = "";
    } else {
        postForm('/auth/login', 'login-form', 'text-response')
    }
}

function createNewsletter() {
    if (isLogin) {
        isLogin = false;
        document.getElementById("login-block").style.display = "none";
        document.getElementById("signup-block").style.display = "block";
        document.getElementById(responseId).innerText = "";
    } else {
        postForm('/auth/register', 'signup-form', 'text-response')
    }
}
