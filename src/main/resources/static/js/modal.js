$(document).ready(function () {
    $("body").on('click', '.editBtn', function (event) {
        event.preventDefault ? event.preventDefault() : (event.returnValue = false);
        let href = $(this).attr('href');
        $.get(href, function (user) {
            $('#id1').val(user.id);
            $('#firstname1').val(user.firstName);
            $('#lastname1').val(user.lastName);
            $('#age1').val(user.age);
            $('#email1').val(user.email);
            rolesSelect(user.roles)
        });
        $('#editUser').modal();
    });

    $('body').on('click', '.deleteBtn', function (event) {
        event.preventDefault ? event.preventDefault() : (event.returnValue = false);
        $.get($(this).attr('href'), function (user) {
            $('#id2').val(user.id);
            $('#firstname2').val(user.firstName);
            $('#lastname2').val(user.lastName);
            $('#age2').val(user.age);
            $('#email2').val(user.email);
            rolesSelect(user.roles)
        });
        $('#deleteUser').modal();
    });

})

function rolesSelect(roles){
    let i = 2;
    if (roles.length === 1) (roles[0].role === "ROLE_USER") ? i = 1 : i = 0
    document.getElementById("roles1").getElementsByTagName('option')[i].selected = 'selected'
}