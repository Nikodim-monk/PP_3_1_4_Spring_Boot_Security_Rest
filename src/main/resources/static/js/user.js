/**
 *
 * */

document.getElementById("head").innerHTML =
    "<tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Age</th><th>Email</th><th>Role</th>"
let url = "http://localhost:8080/user/" + document.getElementById("id").textContent
fetch(url).then(rez => {
    rez.json().then(data => {
        let temp = "<tr>";
        temp += "<td>" + data.id + "</td>"
        temp += "<td>" + data.firstName + "</td>"
        temp += "<td>" + data.lastName + "</td>"
        temp += "<td>" + data.age + "</td>"
        temp += "<td>" + data.email + "</td>"
        temp += "<td>" + "USER" + "</td></tr>"
        document.getElementById("data").innerHTML = temp
        document.getElementById("email").textContent=data.email
    })
})
