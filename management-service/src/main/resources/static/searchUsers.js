function searchUsers(){
    let input = document.getElementById("search-input")
    let filter = input.value.toUpperCase()
    let table = document.getElementById("userTable")
    let tr = table.getElementsByTagName("tr")

    let txtValue;
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[1];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}