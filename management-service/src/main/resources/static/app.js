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

// function loadUsersTable() {
//     $.ajax({
//         url: "http://localhost:8081/management/users",
//         jsonp: "callback",
//         dataType: "jsonp",
//         headers: {'Access-Control-Allow-Origin': "*"},

//         success: function(response) {
//             var tableData = $(`<tr>
//                         <th>First Name</th>
//                         <th>Last name</th>
//                         <th>Gender</th>
//                         <th>Age</th>
//                         <th>Email</th>
//                         <th>Phone</th>
//                     </tr>`);


//             response.forEach(user => {
//                 tableData.append(`<tr data-id='${user["id"]}'>
//                     <td>${user["firstName"]}</td>
//                     <td>${user["lastName"]}</td>
//                     <td>${user["gender"]}</td>
//                     <td>${user["age"]}</td>
//                     <td>${user["emailAddress"]}</td>
//                     <td>${user["phoneNumber"]}</td>
//                 </tr>`);
//             });

//             $("#user-table").html(tableData);
//         },

//         error: function(response) {
//             console.log(response);
//         }

//     });
// }

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

    }

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