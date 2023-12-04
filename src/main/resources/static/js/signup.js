let isSignUp = false;

function login() {
    if (isSignUp) {
        isSignUp = false;
        document.getElementById("login-block").style.display = "block";
        document.getElementById("signup-block").style.display = "none";
    } else {
        postForm('/api/auth/login', 'login-form')
    }
}

function signup() {
    if (!isSignUp) {
        isSignUp = true;
        document.getElementById("login-block").style.display = "none";
        document.getElementById("signup-block").style.display = "block";
    } else {
        postForm('/api/auth/register', 'signup-form')
    }
}
