document.addEventListener('DOMContentLoaded', function () {
    //Para formulario de clientes
    const formCliente = document.getElementById('formCliente');
    const listaClientes = document.getElementById('listaClientes');
    let idCliente = 1;

    formCliente.addEventListener('submit', function (event) {
        event.preventDefault(); // Evitar que el formulario recargue la página

        // Capturamos los valores ingresados en el formulario
        const nombreCliente = document.getElementById('nombreCliente').value;
        const cedulaCliente = document.getElementById('cedulaCliente').value;
        const emailCliente = document.getElementById('emailCliente').value;
        const celularCliente = document.getElementById('celularCliente').value;
        const direccionCliente = document.getElementById('direccionCliente').value;
        const fechaIngreso = document.getElementById('fechaIngreso').value;

        // Creamos una nueva fila con los datos capturados
        const nuevaFila = document.createElement('tr');
        nuevaFila.innerHTML = `
            <td>${idCliente}</td>
            <td>${nombreCliente}</td>
            <td>${cedulaCliente}</td>
            <td>${emailCliente}</td>
            <td>${celularCliente}</td>
            <td>${direccionCliente}</td>
            <td>${fechaIngreso}</td>
        `;

        // Agregamos la nueva fila a la tabla
        listaClientes.appendChild(nuevaFila);

        // Incrementamos el ID para el siguiente cliente
        idCliente++;

        // Limpiamos el formulario después de enviarlo
        formCliente.reset();
    });
    
});
