let authType = 0; // 0 - login, 1 - signup, 2 - forgot password

function login() {
    if (authType != 0) {
        authType = true;
        document.getElementById("login-block").style.display = "block";
        document.getElementById("signup-block").style.display = "none";
        document.getElementById("forgot-block").style.display = "none";
        document.getElementById(responseId).innerText = "";
    } else {
        postForm('/auth/login', 'login-form', 'text-response')
    }
}

function createNewsletter() {
    if (authType != 1) {
        authType = false;
        document.getElementById("login-block").style.display = "none";
        document.getElementById("signup-block").style.display = "block";
        document.getElementById("forgot-block").style.display = "none";
        document.getElementById(responseId).innerText = "";
    } else {
        postForm('/auth/register', 'signup-form', 'text-response')
    }
}

function forgotPassword() {
    if (authType != 2) {
        authType = false;
        document.getElementById("login-block").style.display = "none";
        document.getElementById("signup-block").style.display = "none";
        document.getElementById("forgot-block").style.display = "block";
        document.getElementById(responseId).innerText = "";
    } else {
        postForm('/auth/login-temp', 'forgot-form', 'text-response')
    }
}
