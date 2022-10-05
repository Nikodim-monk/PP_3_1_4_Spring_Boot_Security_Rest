/**
 *
 * */
$(document).ready(function () {
    let user = {
        firstName: "User",
        lastName: "Userov",
        age: 100,
        email: "user@mail.ru",
        password: "user",
        roles: rolesCollection("USER")
    }
    console.log("kjdkjvkjdvkjdv")

    // await fetch('/admin/new/', {
    //     method: 'POST',
    //     headers: {'Content-Type': 'application/json;charset=utf-8'},
    //     body: JSON.stringify(user)
    // });
});
    function rolesCollection(role) {
        if (role === "ADMIN USER") return [{"id": 1, role: "ROLE_USER"}, {"id": 2, role: "ROLE_ADMIN"}]
        if (role === "ADMIN") return [{"id": 2, role: "ROLE_ADMIN"}]
        if (role === "USER") return [{"id": 1, role: "ROLE_USER"}]
    }