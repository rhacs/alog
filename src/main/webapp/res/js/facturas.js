// Esperar a que termine de cargar la página
$(function() {
    // Establecer fecha máxima para la fecha de la factura
    $('#fecha').attr('max', function() {
        return new Date().toJSON().split('T')[0];
    });

    // Al presionar el botón eliminar factura
    $('[data-chaleco]').on('click', function() {
        // Obtener el identificador
        let id = $(this).data('id');

        // Redireccionar
        $(location).attr('href', '/alog/chauchera/' + id);
    });

    // Al presionar el botón Agregar Item
    $('[data-agregar]').on('click', function() {
        // Crear tr
        let tr = $('<tr>');

        // Crear cabecera de la fila
        let th = $('<th>')
            .attr('scope', 'row')
            .attr('class', 'text-nowrap');

        // Crear input
        let input = $('<input>')
            .attr('name', 'cantidad')
            .attr('id', 'cantidad')
            .attr('type', 'number')
            .attr('class', 'form-control')
            .attr('min', '1')
            .attr('max', '1000000')
            .attr('value', '1')
            .attr('required', true);

        // Agregar input a la cabecera
        $(th).append(input);

        // Agregar th a la fila
        $(tr).append(th);

        // Crear columna
        let td = $('<td>')
            .attr('class', 'text-nowrap');

        // Crear input para la columna
        input = $('<input>')
            .attr('name', 'descripcion')
            .attr('id', 'descripcion')
            .attr('class', 'form-control')
            .attr('type', 'text')
            .attr('maxlength', '250')
            .attr('required', true);

        // Agregar input a la columna
        $(td).append(input);

        // Agregar columna a la fila
        $(tr).append(td);

        // Agregar otra columna
        td = $('<td>')
            .attr('class', 'text-nowrap');

        // Crear otro input
        input = $('<input>')
            .attr('name', 'precio')
            .attr('id', 'precio')
            .attr('type', 'number')
            .attr('class', 'form-control')
            .attr('min', '1')
            .attr('value', '1')
            .attr('required', true);

        // Agregar input a la columna
        $(td).append(input);

        // Agregar columna a la fila
        $(tr).append(td);

        // Agregar otra columna
        td = $('<td>')
            .attr('class', 'text-nowrap');

        // Crear botón para eliminar
        let boton = $('<button>')
            .attr('type', 'button')
            .attr('class', 'btn btn-danger')
            .attr('data-eliminar', true)
            .text('X');

        // Agregar botón a la columna
        $(td).append(boton);

        // Agregar columna a la fila
        $(tr).append(td);

        // Agregar fila a la tabla
        $('#nuevo tbody').append(tr);
    });

    // Al presionar el botón eliminar item
    $(document).on('click', '[data-eliminar]', function() {
        // Eliminar elemento seleccionado
        $(this).parent().parent().remove();
    });

    // Al presionar un botón o una fila de la tabla
    $('[data-action]').on('click', function() {
        // Obtener acción
        let accion = $(this).data('action');

        // Obtener identificador
        let id = $(this).data('id');

        // Filtrar acción
        if (accion === 'nuevo') {
            // Redireccionar
            $(location).attr('href', '/alog/facturas/nueva');
        } else if (accion === 'detalles') {
            // Redireccionar
            $(location).attr('href', '/alog/facturas/' + id);
        }
    });
});
