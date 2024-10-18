$(function () {

    $("#resetData").click(function () {
        $(".error-input").removeClass("error-input");
        $("#clienteId").val("");
    });

    loadRoles();
    loadCliente();
    loadFormEvent();

});

function loadFormEvent() {

    console.log("loadFormEvent");

    $("#frm2").on("submit", function (e) {
        e.preventDefault();

        $(".error-input").removeClass("error-input");

        if ($("#nombre").val() === "") {
            $("#nombre").addClass("error-input");
        }

        if ($("#celular").val() === "") {
            $("#celular").addClass("error-input");
        }

        if ($("#usuarioEmail").val() === "") {
            $("#usuarioEmail").addClass("error-input");
        }

        if ($("#cedula").val() === "") {
            $("#cedula").addClass("error-input");
        }

        if ($("#direccion").val() === "") {
            $("#direccion").addClass("error-input");
        }

        if ($("#ingreso").val() === "") {
            $("#ingreso").addClass("error-input");
        }

        if ($("#usuadioId").val() === "") {
            $("#usuarioId").addClass("error-input");
        }

        if ($(".error-input").length > 0) {
            alert("Verifique los datos ingresados");
            return;
        }

        var objCliente = {
            "nombre": $("#nombre").val(),
            "celular": $("#celular").val(),
            "email": $("#usuarioEmail").val(),
            "cedula": $("#cedula").val(),
            "direccion": $("#direccion").val(),
            "ingreso": $("#ingreso").val(),
            "usuarioId": $("#usuarioId").val()
        };

        if ($("#clienteId").val() === "") {
            console.log("Creando nuevo cliente " + JSON.stringify(objCliente));
            createCliente(objCliente);
        } else {
            var clienteId = $("#clienteId").val();
            console.log("Editando cliente " + clienteId + " :: " + JSON.stringify(objCliente));
            editCliente(clienteId, objCliente);
        }

    });

}

function viewCliente(id) {
    var url = "http://localhost:8080/cliente/" + id;
    callApi(url, "GET", null, renderUser);
}

function deleteCliente(id) {
    var url = "http://localhost:8080/cliente/" + id;
    callApi(url, "DELETE", null, function () {
        alert("Registro eliminado con exito!");
        loadCliente();
    })
}

function editCliente(id, data) {
    var url = "http://localhost:8080/cliente/" + id;

    callApi(url, "PUT", data, function () {
        alert("Registro actualizado");
        $("#resetData").click();
        loadCliente();
    });

}

function createCliente(data) {
    var url = "http://localhost:8080/cliente/";

    callApi(url, "POST", data, function () {
        alert("Registro creado");
        $("#resetData").click();
        loadCliente();
    });
}

function loadCliente() {
    var url = "http://localhost:8080/cliente/";
    callApi(url, "GET", null, renderCliente);

}