function postForm(endPoint, formId) {
    let form = document.getElementById(formId);
    let formData = new FormData(form);
    let request = new XMLHttpRequest();
    request.open("POST", endPoint, true);
    request.send(formData);
}

function deleteSubscriber(email) {
    let formData = new FormData();
    formData.append('email', email); // add email from id of active subscriber in document
    let request = new XMLHttpRequest();
    request.open("POST", "/delete-subscriber", true);
    request.send(formData);
}

function logout() {
    let request = new XMLHttpRequest();
    request.open("POST", "/logout", true);
    request.send();
}