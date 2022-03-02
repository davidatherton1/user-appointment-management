function onAddApptLoad(){

    const queryString = window.location.search;

    const urlParam = new URLSearchParams(queryString);

    const userId = urlParam.get('id');

    $.ajax({
        url: "http://localhost:8082/management/user/",
        type: "GET",
        data: {
            "id": userId
        },
        success: function (data){
            let userElement = document.getElementById("user-name")
            userElement.innerHTML += data.firstName + " " + data.lastName
        },
        error: function (err){

        }
    })

    // $.get("http://localhost:8082/management/users", function (data){
    //     let selectElement = document.getElementById("user")
    //     data.forEach(element => {
    //         let name = element.firstName + " " + element.lastName
    //         selectElement.add(new Option(name, element.id))
    //     })
    // })
}



function sendToAddAppt(){

    const queryString = window.location.search;

    const urlParam = new URLSearchParams(queryString);

    const userId = urlParam.get('id');

    window.location.href= 'addAppointment.html?id=' + userId;

}

function addAppt(){

    const queryString = window.location.search;

    const urlParam = new URLSearchParams(queryString);

    const userId = urlParam.get('id');

    let formData = {
        apptName: $("#apptName").val(),
        apptType: $("#apptType").val(),
        startTime: $("#startTime").val(),
        endTime: $("#endTime").val(),
        description: $("#description").val(),
        user: userId
    }

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        url: "http://localhost:8082/management/appointment/",
        data: JSON.stringify(formData),
        dataType: "json"
    })
}

