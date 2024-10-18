$(function () {

    $("#resetData").click(function () {
        $(".error-input").removeClass("error-input");
        $("#estadoId").val("");
    });

    loadEstadosReparacion();
    loadEstado();
    loadFormEvent();

});

function loadFormEvent() {

    console.log("loadFormEvent");

    $("#frm6").on("submit", function (e) {
        e.preventDefault();

        $(".error-input").removeClass("error-input");

        if ($("#fechaIngreso").val() === "") {
            $("#fechaIngreso").addClass("error-input");
        }
        if ($("#fechaSalida").val() === "") {
            $("#fechaSalida").addClass("error-input");
        }

        if ($("#estadoRearacion").val() === "") {
            $("#estadoRearacion").addClass("error-input");
        }

        if ($("#electrodomesticoId").val() === "") {
            $("#electrodomesticoId").addClass("error-input");
        }

        if ($(".error-input").length > 0) {
            alert("Verifique los datos ingresados");
            return;
        }

        var objEstado = {
            "fechaIngreso": $("#fechaIngreso").val(),
            "fechaSalida": $("#fechaSalida").val(),
            "estadoReparacion": $("#estadoRearacion").val(),
            "elctrodomesticoId": $("#electrodomesticoId").val()
        };

        if ($("#estadoId").val() === "") {
            console.log("Creando nuevo estado " + JSON.stringify(objEstado));
            createUsuario(objEstado);
        } else {
            var estadoId = $("#usuarioId").val();
            console.log("Editando estado" + estadoId + " :: " + JSON.stringify(objEstado));
            editEstado(estadoId, objEstado);
        }

    });

}

function viewEstado(id) {
    var url = "http://localhost:8080/estado/" + id;
    callApi(url, "GET", null, renderEstado);
}

function deleteEstado(id) {
    var url = "http://localhost:8080/estado/" + id;
    callApi(url, "DELETE", null, function () {
        alert("Registro eliminado con exito!");
        loadEstado();
    })
}

function editEstado(id, data) {
    var url = "http://localhost:8080/estado/" + id;

    callApi(url, "PUT", data, function () {
        alert("Registro actualizado");
        $("#resetData").click();
        loadEstado();
    });

}

function createEstado(data) {
    var url = "http://localhost:8080/estado";

    callApi(url, "POST", data, function () {
        alert("Registro creado");
        $("#resetData").click();
        loadEstado();
    });
}

function loadEstado() {
    var url = "http://localhost:8080/estado";
    callApi(url, "GET", null, renderEstado);

}