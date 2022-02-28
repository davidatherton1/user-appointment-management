// $("form").submit(function (event){
//     let formData = {
//         firstName: $("#firstName").val(),
//         lastName: $("#lastName").val(),
//         gender: $("#gender").val(),
//         age: $("#age").val(),
//         emailAddress: $("#emailAddress").val(),
//         phoneNumber: $("#phoneNumber").val()
//     }
//
//     console.log(formData)
//
//     $.ajax({
//         type: "POST",
//         url: "http://localhost:8082:/management/user/",
//         data: formData,
//         dataType: "json"
//     })
//
//     event.preventDefault()
// })
//
function addUser(){
    let formData = {
        firstName: $("#firstName").val(),
        lastName: $("#lastName").val(),
        gender: $("#gender").val(),
        age: $("#age").val(),
        emailAddress: $("#emailAddress").val(),
        phoneNumber: $("#phoneNumber").val()
    }

    console.log(formData)

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        url: "http://localhost:8082/management/user/",
        data: JSON.stringify(formData),
        dataType: "json"
    })

    window.location.href = "http://localhost:8082"

}