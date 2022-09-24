/**
 *
 * */
$(document).ready(function () {
    document.getElementById("headAll").innerHTML = "<tr><th>ID</th><th>First Name</th><th>Last Name</th>" +
        "<th>Age</th><th>Email</th><th>Role</th><th>Edit</th><th>Delete</th></tr>"
    url = "http://localhost:8080/admin/all/"
    fetch(url).then(rez => {
        rez.json().then(data => {
            let temp = ""

            data.forEach((str) => {
                let role;
                if (str.roles.length === 2) {
                    role = "USER ADMIN"
                } else {
                    (str.roles[0].role === "ROLE_USER") ? role = "USER" : role = "ADMIN"
                }
                let btnEdit = "<a href=\"user/" + str.id + "\" class='btn btn-info btn-sm editBtn'>Edit</a>"
                let btnDelete = "<a href=\"user/" + str.id + "\" class='btn btn-danger btn-sm deleteBtn'>Detete</a>"

                temp += "<tr><td>" + str.id + "</td>"
                temp += "<td>" + str.firstName + "</td>"
                temp += "<td>" + str.lastName + "</td>"
                temp += "<td>" + str.age + "</td>"
                temp += "<td>" + str.email + "</td>"
                temp += "<td>" + role + "</td>"
                temp += "<td>" + btnEdit + "</td>"
                temp += "<td>" + btnDelete + "</td>"
                temp += "</tr>"
            })
            document.getElementById("dataAll").innerHTML = temp
        })
    })
})