/**
 *
 * */
$(document).ready(function () {
    document.getElementById("headAll").innerHTML = "<tr><th>ID</th><th>First Name</th><th>Last Name</th>" + "<th>Age</th><th>Email</th><th>Role</th><th>Edit</th><th>Delete</th></tr>"
    const url = "/admin/all/"
    allUsersInTable(url)

    document.querySelector('.formNew').addEventListener('submit', async (event) => {
        event.preventDefault()
        let formInfo = $('.formNew').serializeArray()
        let user = {
            firstName: formInfo[0].value,
            lastName: formInfo[1].value,
            age: formInfo[2].value,
            email: formInfo[3].value,
            password: formInfo[4].value,
            roles: roles(formInfo[5].value)
        }
        await fetch('/admin/new/', {
            method: 'POST', headers: {'Content-Type': 'application/json;charset=utf-8'}, body: JSON.stringify(user)
        });
        allUsersInTable(url)
        document.getElementById("nav-home-tab").click()
    })

    document.querySelector('.formEdit').addEventListener('submit', (event) => {
        event.preventDefault()

        // код по подготовке данных
        // и их отправка на сервер

        allUsersInTable(url)
        document.getElementById("eBC").click()
    })

    document.querySelector('.formDelete').addEventListener('submit', (event) => {
        event.preventDefault()

        // код по подготовке данных
        // и их отправка на сервер
        allUsersInTable(url)
        document.getElementById("dBC").click()
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

function roles(role) {
    if (role === "USER ADMIN") return [{"id": 1, role: "ROLE_USER"}, {"id": 2, role: "ROLE_ADMIN"}]
    if (role === "ADMIN") return [{"id": 2, role: "ROLE_ADMIN"}]
    if (role === "USER") return [{"id": 1, role: "ROLE_USER"}]
}