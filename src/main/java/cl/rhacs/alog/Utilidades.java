package cl.rhacs.alog;

import java.sql.SQLException;

public class Utilidades {

    /**
     * Extrae la información de la {@link Exception} y la muestra por consola
     * 
     * @param clase  nombre de la clase donde ocurrió la excepción
     * @param metodo método que se ejecutó cuando ocurrió la excepción
     * @param e      objeto {@link Exception} con la información
     */
    public static void extraerError(String clase, String metodo, Exception e) {
        // Mostrar cabecera
        System.err.println(String.format("ERROR: %s#%s()", clase, metodo));

        // Verificar si la excepción fue SQLException
        if (e instanceof SQLException) {
            // Extraer estado sql
            String estado = ((SQLException) e).getSQLState();

            // Mostrar estado
            System.err.println(String.format(" [!] Estado SQL: %s", estado));
        }

        // Extraer mensaje
        String mensaje = e.getLocalizedMessage() == null ? e.getMessage() : e.getLocalizedMessage();

        // Mostrar mensaje
        System.err.println(String.format(" [!] %s", mensaje));
    }

}
