/**
 *
 * */
$(document).ready(function () {
    document.getElementById("head").innerHTML =
        "<tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Age</th><th>Email</th><th>Role</th>"
    fetch("/user/principal").then(rez => {
        rez.json().then(data => {
            let role = "ADMIN USER";
            if (data.roles.length === 1) (data.roles[0].role === "ROLE_USER") ? role = "USER" : role = "ADMIN"

            let temp = "<tr><td>" + data.id + "</td>"
            temp += "<td>" + data.firstName + "</td>"
            temp += "<td>" + data.lastName + "</td>"
            temp += "<td>" + data.age + "</td>"
            temp += "<td>" + data.email + "</td>"
            temp += "<td>" + role + "</td></tr>"
            document.getElementById("data").innerHTML = temp
            document.getElementById("email").textContent = data.email
            document.getElementById("role").textContent = "with roles: " + role
        })
    })
})