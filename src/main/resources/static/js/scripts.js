

function postForm(endPoint, formId, responseId) {
    let form = document.getElementById(formId);
    if (form == null) {
        postEmptyFormData(endPoint, responseId)
        return;
    }
    postFilledFormData(endPoint, new FormData(form), responseId);

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