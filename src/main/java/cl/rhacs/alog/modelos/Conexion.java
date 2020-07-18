package cl.rhacs.alog.modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import cl.rhacs.alog.Utilidades;

public class Conexion {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /** Instancia de la clase {@link Conexion} */
    private static Conexion instancia = null;

    /** Objeto {@link Connection} con los métodos de conexión a la base de datos */
    private Connection conexion = null;

    /** URL de conexión a la base de datos */
    private String url;

    /** Usuario de acceso a la base de datos */
    private String usuario;

    /** Contraseña de acceso del usuario */
    private String password;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia del objeto {@link Conexion}
     * 
     * @param url      url de conexión a la base de datos
     * @param usuario  usuario de acceso
     * @param password contraseña del usuario
     */
    private Conexion(String url, String usuario, String password) {
        this.url = url;
        this.usuario = usuario;
        this.password = password;
    }

    // Métodos
    // -----------------------------------------------------------------------------------------

    /**
     * Devuelve una instancia del objeto {@link Conexion}
     * 
     * @param url      url de acceso a la base de datos
     * @param usuario  usiario de acceso
     * @param password contraseña del usuario
     * @return una instancia del objeto
     */
    public static Conexion getInstance(String url, String usuario, String password) {
        // Verificar si no existe una instancia
        if (instancia == null) {
            // Crear nueva instancia del objeto
            instancia = new Conexion(url, usuario, password);
        }

        // Devolver instancia
        return instancia;
    }

    /**
     * Crea una nueva conexión con la base de datos
     * 
     * @return objeto {@link Connection} con los métodos de acceso a la base de
     *         datos
     */
    public Connection conectar() {
        try {
            // Verificar si el objeto no existe o no está conectado
            if (conexion == null || conexion.isClosed()) {
                // Cargar driver
                Class.forName("oracle.jdbc.driver.OracleDriver");

                // Crear conexión
                conexion = DriverManager.getConnection(url, usuario, password);
            }
        } catch (SQLException | ClassNotFoundException e) {
            Utilidades.extraerError(Conexion.class.getSimpleName(), "conectar", e);
        }

        return conexion;
    }

    /**
     * Desconecta la instancia de la base de datos
     */
    public void desconectar() {
        try {
            // Verificar si el objeto existe y está conectado
            if (conexion != null && !conexion.isClosed()) {
                // Desconectar
                conexion.close();
            }
        } catch (SQLException e) {
            Utilidades.extraerError(this.getClass().getSimpleName(), "desconectar", e);
        }
    }

}
