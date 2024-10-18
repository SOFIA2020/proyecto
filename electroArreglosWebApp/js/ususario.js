$(function () {

    $("#resetData").click(function () {
        $(".error-input").removeClass("error-input");
        $("#usuarioId").val("");
    });

    loadRoles();
    loadUsuarios();
    loadFormEvent();

});

function loadFormEvent() {

    console.log("loadFormEvent");

    $("#frm1").on("submit", function (e) {
        e.preventDefault();

        $(".error-input").removeClass("error-input");

        if ($("#nombre").val() === "") {
            $("#nombre").addClass("error-input");
        }
        if ($("#email").val() === "") {
            $("#email").addClass("error-input");
        }

        if ($("#avatar").val() === "") {
            $("#avatar").addClass("error-input");
        }

        if ($("#rol").val() === "") {
            $("#rol").addClass("error-input");
        }

        if ($(".error-input").length > 0) {
            alert("Verifique los datos ingresados");
            return;
        }

        var objUsuario = {
            "fullName": $("#nombre").val(),
            "email": $("#email").val(),
            "avatar": $("#fileName").val(),
            "rolId": $("#rol").val()
        };

        if ($("#usuarioId").val() === "") {
            console.log("Creando nuevo usuario " + JSON.stringify(objUsuario));
            createUsuario(objUsuario);
        } else {
            var usuarioId = $("#usuarioId").val();
            console.log("Editando usuario " + usuarioId + " :: " + JSON.stringify(objUsuario));
            editUsuario(usuarioId, objUsuario);
        }

    });

}

function viewUsuario(id) {
    var url = "http://localhost:8080/usuario/" + id;
    callApi(url, "GET", null, renderUser);
}

function deleteUsuario(id) {
    var url = "http://localhost:8080/usuario/" + id;
    callApi(url, "DELETE", null, function () {
        alert("Registro eliminado con exito!");
        loadUsuarios();
    })
}

function editUsuario(id, data) {
    var url = "http://localhost:8080/usuario/" + id;

    callApi(url, "PUT", data, function () {
        alert("Registro actualizado");
        $("#resetData").click();
        loadUsuarios();
    });

}

function createUsuario(data) {
    var url = "http://localhost:8080/usuario";

    callApi(url, "POST", data, function () {
        alert("Registro creado");
        $("#resetData").click();
        loadUsuarios();
    });
}

function loadUsuarios() {
    var url = "http://localhost:8080/usuario";
    callApi(url, "GET", null, renderUsers);

}