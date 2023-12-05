let isLogin = true;

function login() {
    if (isLogin) {
        isLogin = false;
        document.getElementById("login-block").style.display = "none";
        document.getElementById("signup-block").style.display = "block";
    } else {
        postForm('/auth/login', 'login-form')
    }
}

function createNewsletter() {
    if (!isLogin) {
        isLogin = true;
        document.getElementById("login-block").style.display = "block";
        document.getElementById("signup-block").style.display = "none";
    } else {
        postForm('/auth/register', 'signup-form')
    }
}
