/**
 *
 * */
$(document).ready(function () {
    document.getElementById("headAll").innerHTML = "<tr><th>ID</th><th>First Name</th><th>Last Name</th>" +
        "<th>Age</th><th>Email</th><th>Role</th><th>Edit</th><th>Delete</th></tr>"
    const url="http://localhost:8080/admin/all/"
    allUsersInTable(url)

    document.querySelector('.formNew').addEventListener('submit', (event) => {
        event.preventDefault()
        alert("Создание нового юзера в базе")
        document.getElementById("nav-home-tab").click()
        allUsersInTable(url)
        // код по подготовке данных
        // и их отправка на сервер
    })

    document.querySelector('.formEdit').addEventListener('submit', (event) => {
        event.preventDefault()
        alert("Изменение юзера в базе")
        document.getElementById("eBC").click()
        allUsersInTable(url)
        // код по подготовке данных
        // и их отправка на сервер
    })

    document.querySelector('.formDelete').addEventListener('submit', (event) => {
        event.preventDefault()
        alert("Удаление юзера из базы")
        document.getElementById("dBC").click()
        allUsersInTable(url)
        // код по подготовке данных
        // и их отправка на сервер
    })
})

function allUsersInTable(url) {
    fetch(url).then(rez => {
        rez.json().then(data => {
            let temp = ""
            data.forEach((str) => {
                let role = "USER ADMIN";
                if (str.roles.length === 1) (str.roles[0].role === "ROLE_USER") ? role = "USER" : role = "ADMIN"

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
}