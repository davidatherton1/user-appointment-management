function userInfo(row){
    window.location.href ='userInfo.html?id=' + row.getAttribute('uuid')
}

function updateAppt(row) {
    window.location.href= 'updateAppointment.html?id=' + row.getAttribute("id");
}

function loadUserTable(){
    $.ajax({
        url: "http://localhost:8082/management/users/",
        type: "GET",
        success: function (data){
            let userTable = document.getElementById("userTable")
            data.forEach(element => {
                let newRow = userTable.insertRow();
                newRow.setAttribute('uuid', element.id)
                let firstName = newRow.insertCell()
                firstName.innerHTML = element.firstName
                let lastName = newRow.insertCell()
                lastName.innerHTML = element.lastName
                let gender = newRow.insertCell()
                gender.innerHTML = element.gender
                let age = newRow.insertCell()
                age.innerHTML = element.age
                let emailAddress = newRow.insertCell()
                emailAddress.innerHTML = element.emailAddress
                let phoneNumber = newRow.insertCell()
                phoneNumber.innerHTML = element.phoneNumber
                let button = newRow.insertCell()
                button.innerHTML = "<button onclick='userInfo(this.parentNode.parentNode)'>Info</button>"
            })

        },
        error: function (err){

        }
    })
}


function onLoadUserInfo(){

    const queryString = window.location.search

    const urlParam = new URLSearchParams(queryString)

    const userId = urlParam.get('id')

    $.ajax({
        url: "http://localhost:8082/management/user/",
        type: "GET",
        data: {
            "id": userId
        },
        success: function (data){
                document.getElementById('userFirst').innerHTML += data.firstName
                document.getElementById('userLast').innerHTML += data.lastName
                document.getElementById('userAge').innerHTML += data.age
                document.getElementById('userGender').innerHTML += data.gender
                document.getElementById('userNumber').innerHTML += data.phoneNumber
                document.getElementById('userEmail').innerHTML += data.emailAddress
        },
        error: function (err){

        }
    })

    $.get('http://localhost:8082/management/appointment/user/' + userId, function (data){
        console.log(data)
        let apptTable = document.getElementById('appointmentTable')
        data.forEach(element => {
            let newRow = apptTable.insertRow()
            newRow.setAttribute('id', element.id)
            let name = newRow.insertCell()
            name.innerHTML = element.apptName
            let type = newRow.insertCell()
            type.innerHTML = element.apptType
            let startTime = newRow.insertCell()
            startTime.innerHTML = element.startTime
            let endTime = newRow.insertCell()
            endTime.innerHTML = element.endTime
            let description = newRow.insertCell()
            description.innerHTML = element.description
            let button = newRow.insertCell()
            button.innerHTML = "<button onclick='deleteAppointment(this.parentNode.parentNode)'>Delete</button>"
            let updateButton = newRow.insertCell();
            updateButton.innerHTML = "<button onclick='updateAppt(this.parentNode.parentNode)'>Update</button>";

        })
    })

}

function updateAppointment() {
    const queryString = window.location.search;

    const urlParam = new URLSearchParams(queryString);

    const appointmentId = urlParam.get('id');

    console.log(appointmentId);

    var idInput = $(`<input type='text' value='${appointmentId}' id='apptId'>`);
    $("#id-container").html(idInput);

    $.ajax({
        url: "http://localhost:8082/management/appointment/" + appointmentId,
        type: "GET",
        success: function (data){
            document.getElementById("apptName").value = data.apptName
            document.getElementById("apptType").value = data.apptType
            document.getElementById("description").value = data.description
            document.getElementById("startTime").value = data.startTime.substring(0,16)
            document.getElementById("endTime").value = data.endTime.substring(0,16)
        },
        error: function (err){

        }
    })
}

function onSubmitUpdateAppointment() {
    const queryString = window.location.search;

    const urlParam = new URLSearchParams(queryString);

    const appointmentId = urlParam.get('id');

    let formData = {
        apptName: $("#apptName").val(),
        apptType: $("#apptType").val(),
        description: $("#description").val(),
        startTime: $("#startTime").val(),
        endTime: $("#endTime").val()
    };

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "PUT",
        url: "http://localhost:8082/management/appointment/" + appointmentId,
        data: JSON.stringify(formData),
        dataType: "json"
    });

    window.location.href = "http://localhost:8082/index.html"
}

function deleteUser(){
    if (confirm("Are you sure you want to delete?")){
        const queryString = window.location.search

        const urlParam = new URLSearchParams(queryString)

        const userId = urlParam.get('id')

        $.ajax({
            url: 'http://localhost:8082/management/user',
            type: "DELETE",
            data: {
                "id": userId
            }
        })

        window.location.href = '/'
    }

}

function updateUserOnClick() {
    const queryString = window.location.search

    const urlParam = new URLSearchParams(queryString)

    const userId = urlParam.get('id')

    window.location.href = 'http://localhost:8082/updateUser.html?id=' + userId;
}

function onSubmitUpdateUser() {
    const queryString = window.location.search;

    const urlParam = new URLSearchParams(queryString);

    const userId = urlParam.get('id');

    let userData = {
        firstName: $("#firstName").val(),
        lastName: $("#lastName").val(),
        age: $("#age").val(),
        emailAddress: $("#emailAddress").val(),
        phoneNumber: $("#phoneNumber").val(),
        gender: $("#gender-select option:selected").val()
    };

    console.log(userData);

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "PUT",
        url: "http://localhost:8082/management/user?id=" + userId,
        data: JSON.stringify(userData),
        dataType: "json",

        success: function(response) {
            console.log("Success");
            console.log(response);
        },
        
        error: function(err) {
            console.log(err);
        }

    });

    
}

function deleteAppointment(row){
    if (confirm("Are you sure you want to delete?")){
        let id = row.getAttribute('id')

        $.ajax({
            url: 'http://localhost:8082/management/appointment/' + id,
            type: "DELETE"
        })

        location.reload()
    }

}