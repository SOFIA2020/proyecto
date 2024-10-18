document.addEventListener('DOMContentLoaded', function() {
    const formCliente = document.getElementById('formCliente');

    // Capturar el evento submit del formulario
    formCliente.addEventListener('submit', function(event) {
        // Evitar que el formulario recargue la página
        event.preventDefault();

        // Obtener los datos del formulario
        const nombre = document.getElementById('nombreCliente').value;
        const cedula = document.getElementById('cedulaCliente').value;
        const email = document.getElementById('emailCliente').value;
        const celular = document.getElementById('celularCliente').value;
        const direccion = document.getElementById('direccionCliente').value;
        const fechaIngreso = document.getElementById('fechaIngresoCliente').value;

        // Validar si todos los campos están completos
        if (nombre && cedula && email && celular && direccion && fechaIngreso) {
            // Crear un objeto con los datos del formulario
            const clienteData = {
                nombre: nombre,
                cedula: cedula,
                email: email,
                celular: celular,
                direccion: direccion,
                fechaIngreso: fechaIngreso
            };

            // Enviar los datos al servidor mediante fetch con una solicitud POST
            fetch('/ruta/guardarCliente', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(clienteData)
            })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    // Mostrar mensaje de éxito
                    alert('Cliente guardado con éxito.');
                    // Agregar el cliente a la tabla
                    agregarClienteTabla(clienteData);
                    // Limpiar el formulario
                    formCliente.reset();
                } else {
                    alert('Error al guardar el cliente.');
                }
            })
            .catch(error => {
                console.error('Error al enviar los datos:', error);
            });
        } else {
            alert('Por favor, complete todos los campos.');
        }
    });

    // Función para agregar un cliente a la tabla
    function agregarClienteTabla(cliente) {
        const listaClientes = document.getElementById('listaClientes');
        const nuevaFila = document.createElement('tr');
        nuevaFila.innerHTML = `
            <td>${cliente.id || '-'}</td>
            <td>${cliente.nombre}</td>
            <td>${cliente.cedula}</td>
            <td>${cliente.email}</td>
            <td>${cliente.celular}</td>
            <td>${cliente.direccion}</td>
            <td>${cliente.fechaIngreso}</td>
        `;
        listaClientes.appendChild(nuevaFila);
    }
});
