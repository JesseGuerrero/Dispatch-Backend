let isSignUp = false;
document.getElementById("signup-block").display = "none";

function login() {
    if (isSignUp) {
        isSignUp = false;
    } else {
        postForm('/api/auth/login', 'login-form')
    }
}

function signup() {
    if (!isSignUp) {
        isSignUp = true;
    } else {
        postForm('/api/auth/register', 'signup-form')
    }
}
