$(function () {

    $("#resetData").click(function () {
        $(".error-input").removeClass("error-input");
        $("#tecnicoId").val("");
    });

    loadTecnico();
    loadFormEvent();

});

function loadFormEvent() {

    console.log("loadFormEvent");

    $("#frm4").on("submit", function (e) {
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

        if ($("#titulo").val() === "") {
            $("#titulo").addClass("error-input");
        }

        if ($("#usuarioEmail").val() === "") {
            $("#usuarioEmail").addClass("error-input");
        }

        if ($("#usuadioId").val() === "") {
            $("#usuarioId").addClass("error-input");
        }

        if ($("#electrodomesticoId").val() === "") {
            $("#electrodomesticoId").addClass("error-input");
        }

        if ($(".error-input").length > 0) {
            alert("Verifique los datos ingresados");
            return;
        }

        var objTecnico = {
            "nombre": $("#nombre").val(),
            "cedula": $("#cedula").val(),
            "celular": $("#celular").val(),
            "titulo": $("#titulo").val(),
            "email": $("#usuarioEmail").val(),            
            "usuarioId": $("#usuarioId").val(),
            "electrodomesticoId": $("#electrodomesticoId").val(),
        };

        if ($("#tecnicoId").val() === "") {
            console.log("Creando nuevo Tecnico" + JSON.stringify(objTecnico));
            createCliente(objTecnico);
        } else {
            var tecnicoId = $("#tecnicoId").val();
            console.log("Editando tecnico" + tecnicoId + " :: " + JSON.stringify(objTecnico));
            editCliente(tecnicoId, objTecnico);
        }

    });

}

function viewTecnico(id) {
    var url = "http://localhost:8080/tecnico/" + id;
    callApi(url, "GET", null, renderUser);
}

function deleteTecnico(id) {
    var url = "http://localhost:8080/tecnico/" + id;
    callApi(url, "DELETE", null, function () {
        alert("Registro eliminado con exito!");
        loadTecnico();
    })
}

function editTecnico(id, data) {
    var url = "http://localhost:8080/tecnico/" + id;

    callApi(url, "PUT", data, function () {
        alert("Registro actualizado");
        $("#resetData").click();
        loadTecnico();
    });

}

function createTecnico(data) {
    var url = "http://localhost:8080/tecnico/";

    callApi(url, "POST", data, function () {
        alert("Registro creado");
        $("#resetData").click();
        loadTecnico();
    });
}

function loadTecnico() {
    var url = "http://localhost:8080/tecnico/";
    callApi(url, "GET", null, renderTecnico);

}