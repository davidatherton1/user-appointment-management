$(function () {
    $('table.userTable tr').click(function (){
        // console.log($(this).data('uuid'))
        window.location.href ='userInfo.html?id=' + $(this).data('uuid')
    })
})


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

    // $.get('http://localhost:8082/management/user/' + userId, function(data){
    //     document.getElementById('userFirst').innerHTML += data.firstName
    //     document.getElementById('userLast').innerHTML += data.lastName
    //     document.getElementById('userAge').innerHTML += data.age
    //     document.getElementById('userGender').innerHTML += data.gender
    //     document.getElementById('userNumber').innerHTML += data.phoneNumber
    //     document.getElementById('userEmail').innerHTML += data.email
    // })

    $.get('http://localhost:8082/management/appointment/user/' + userId, function (data){
        console.log(data)
        let apptTable = document.getElementById('appointmentTable')
        data.forEach(element => {
            let newRow = apptTable.insertRow()
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

        })
    })

}