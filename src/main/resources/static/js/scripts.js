function postForm(endPoint, formId, responseId) {
    let form = document.getElementById(formId);
    let formData = new FormData(form);

    // Convert FormData to JSON
    let formDataObject = {};
    formData.forEach((value, key) => {
        if(key === 'password')
            formDataObject[key] = btoa(value);
        else
            formDataObject[key] = value;
    });
    let jsonData = JSON.stringify(formDataObject);

    let request = new XMLHttpRequest();

    // Set up event listener for successful response
    if(responseId !== undefined) {
        request.addEventListener('load', function () {
            // Successful response, handle the data here
            document.getElementById(responseId).innerText = request.responseText;
        });
    }

    request.open("POST", endPoint, true);

    // Set headers to indicate JSON data
    request.setRequestHeader("Content-Type", "application/json");

    // Log the JSON data before sending
    // console.log(jsonData);

    request.send(jsonData);
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