$.get("http://localhost:8082/management/users", function (data){
    let selectElement = document.getElementById("user")
    data.forEach(element => {
        let name = element.firstName + element.lastName
        selectElement.add(new Option(name, element.id))
    })
})

// $("form").submit(function (event){
//     let formData = {
//         apptName: $("#apptName").val(),
//         apptType: $("#apptType").val(),
//         description: $("#description").val(),
//         user: $("#user").val()
//     }
//
//     $.ajax({
//         type: "POST",
//         url: "http://localhost:8082:/management/appointment/",
//         data: formData,
//         dataType: "json"
//     })
//
//     event.preventDefault()
// })

function addAppt(){
    let formData = {
        apptName: $("#apptName").val(),
        apptType: $("#apptType").val(),
        description: $("#description").val(),
        user: $("#user").val()
    }
    console.log(formData)

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        url: "http://localhost:8082/management/appointment/",
        data: JSON.stringify(formData),
        dataType: "json"
    })}