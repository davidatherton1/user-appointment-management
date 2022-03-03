function onLoadUserUpdate(){
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
            document.getElementById("firstName").value = data.firstName
            document.getElementById("lastName").value = data.lastName
            document.getElementById("age").value = data.age
            document.getElementById("gender-select").value = data.gender
            document.getElementById("emailAddress").value = data.emailAddress
            document.getElementById("phoneNumber").value = data.phoneNumber
        },
        error: function (err){

        }
    })
}