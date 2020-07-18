// Esperar a que termine de cargar la página
$(function() {
    // Al presionar un botón o una fila de la tabla
    $('[data-action]').on('click', function() {
        // Obtener acción
        let accion = $(this).data('action');

        // Obtener identificador
        let id = $(this).data('id');

        // Filtrar acción
        if (accion === 'nuevo') {
            // Redireccionar
            $(location).attr('href', '/alog/nuevo');
        } else if (accion === 'detalles') {
            // Redireccionar
            $(location).attr('href', '/alog/facturas/' + id);
        }
    });
});
