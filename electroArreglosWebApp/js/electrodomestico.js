$(function () {

    $("#resetData").click(function () {
        $(".error-input").removeClass("error-input");
        $("#electrodomesticoId").val("");
    });

    loadelectrodomestico();
    loadFormEvent();

});

function loadFormEvent() {

    console.log("loadFormEvent");

    $("#frm5").on("submit", function (e) {
        e.preventDefault();

        $(".error-input").removeClass("error-input");

        if ($("#referencia").val() === "") {
            $("#referencia").addClass("error-input");  
        }
        
        if ($("#marca").val() === "") {
            $("#marca").addClass("error-input");
        }

        if ($("#ingreso").val() === "") {
            $("#ingreso").addClass("error-input");
        }

        if ($("#serial").val() === "") {
            $("#serial").addClass("error-input");
        }

        if ($("#clienteId").val() === "") {
            $("#clienteId").addClass("error-input");
        }

        if ($("#usuadioId").val() === "") {
            $("#usuarioId").addClass("error-input");
        }

        if ($(".error-input").length > 0) {
            alert("Verifique los datos ingresados");
            return;
        }

        var objElectrodomesico = {
            "referncia": $("#referencia").val(),
            "marca": $("#marca").val(),
            "ingreso": $("#ingreso").val(),
            "serial": $("#serial").val(),
            "clienteId": $("#clienteId").val(),            
            "usuarioId": $("#usuarioId").val()
        };

        if ($("#electrodomesticoId").val() === "") {
            console.log("Creando nuevo electrodomestico" + JSON.stringify(objElectrodomesico));
            createCliente(objElectrodomesico);
        } else {
            var electrodomesticoId = $("#electrodomesticoId").val();
            console.log("Editando electrodomestico" + electrodomesticoId + " :: " + JSON.stringify(objElectrodomesico));
            editCliente(electrodomesticoId, objElectrodomesico);
        }

    });

}

function viewElectrodomestico(id) {
    var url = "http://localhost:8080/electrodomestico/" + id;
    callApi(url, "GET", null, renderUser);
}

function deleteElectrodomestico(id) {
    var url = "http://localhost:8080/electrodomestico/" + id;
    callApi(url, "DELETE", null, function () {
        alert("Registro eliminado con exito!");
        loadelectrodomestico();
    })
}

function editElectrodomestico(id, data) {
    var url = "http://localhost:8080/electrodomestico/" + id;

    callApi(url, "PUT", data, function () {
        alert("Registro actualizado");
        $("#resetData").click();
        loadelectrodomestico();
    });

}

function createElectrodomestico(data) {
    var url = "http://localhost:8080/electrodomestico/";

    callApi(url, "POST", data, function () {
        alert("Registro creado");
        $("#resetData").click();
        loadelectrodomestico();
    });
}

function loadElectrodomestico() {
    var url = "http://localhost:8080/electrodomestico/";
    callApi(url, "GET", null, renderTecnico);

}