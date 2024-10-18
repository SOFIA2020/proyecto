$(function () {

    $("#resetData").click(function () {
        $(".error-input").removeClass("error-input");
        $("#administradorId").val("");
    });

    loadRoles();
    loadAdministrador();
    loadFormEvent();

});

function loadFormEvent() {

    console.log("loadFormEvent");

    $("#frm3").on("submit", function (e) {
        e.preventDefault();

        $(".error-input").removeClass("error-input");

        if ($("#nombre").val() === "") {
            $("#nombre").addClass("error-input");
        }
        
        if ($("#cedula").val() === "") {
            $("#cedula").addClass("error-input");
        }

        if ($("#celular").val() === "") {
            $("#celular").addClass("error-input");
        }

        if ($("#usuarioEmail").val() === "") {
            $("#usuarioEmail").addClass("error-input");
        }

        if ($("#usuadioId").val() === "") {
            $("#usuarioId").addClass("error-input");
        }

        if ($(".error-input").length > 0) {
            alert("Verifique los datos ingresados");
            return;
        }

        var objAdministrador = {
            "nombre": $("#nombre").val(),
            "cedula": $("#cedula").val(),
            "celular": $("#celular").val(),
            "email": $("#usuarioEmail").val(),            
            "usuarioId": $("#usuarioId").val()
        };

        if ($("#administradorId").val() === "") {
            console.log("Creando nuevo administrador" + JSON.stringify(objAdministrador));
            createCliente(objAdministrador);
        } else {
            var administradorId = $("administradorId").val();
            console.log("Editando administrador " + administradorId + " :: " + JSON.stringify(objAdministrador));
            editCliente(administradorId, objAdministrador);
        }

    });

}

function viewAdministrador(id) {
    var url = "http://localhost:8080/administrador/" + id;
    callApi(url, "GET", null, renderUser);
}

function deleteAdministrador(id) {
    var url = "http://localhost:8080/administrador/" + id;
    callApi(url, "DELETE", null, function () {
        alert("Registro eliminado con exito!");
        loadAdministrador();
    })
}

function editAdministrador(id, data) {
    var url = "http://localhost:8080/administrador/" + id;

    callApi(url, "PUT", data, function () {
        alert("Registro actualizado");
        $("#resetData").click();
        loadAdministrador();
    });

}

function createAdministrador(data) {
    var url = "http://localhost:8080/administrador/";

    callApi(url, "POST", data, function () {
        alert("Registro creado");
        $("#resetData").click();
        loadAdministrador();
    });
}

function loadAdministrador() {
    var url = "http://localhost:8080/administrador/";
    callApi(url, "GET", null, renderAdministrador);

}