package cl.rhacs.alog.repositorios;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cl.rhacs.alog.Utilidades;
import cl.rhacs.alog.modelos.Conexion;
import cl.rhacs.alog.modelos.Factura;
import cl.rhacs.alog.repositorios.interfaces.IFacturasRepositorio;

public class FacturasRepositorio implements IFacturasRepositorio {

    // Constantes
    // -----------------------------------------------------------------------------------------

    /** Nombre de la tabla en la base de datos */
    private final String TABLA = "alog_facturas";

    /** Consulta base para los SELECT */
    private final String BASE_SELECT = "SELECT factura_id, fecha FROM " + TABLA;

    // Atributos
    // -----------------------------------------------------------------------------------------

    /** Objeto {@link Conexion} con los métodos de conexión a la base de datos */
    private Conexion conexion;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia del objeto {@link FacturasRepositorio}
     * 
     * @param conexion objeto {@link Conexion} con los métodos de conexión a la base
     *                 de datos
     */
    public FacturasRepositorio(Conexion conexion) {
        this.conexion = conexion;
    }

    // Métodos
    // -----------------------------------------------------------------------------------------

    /**
     * Extrae la información de un {@link ResultSet}
     * 
     * @param resultSet objeto {@link ResultSet} con la información
     * @return un objeto {@link Factura} con el resultado, {@code null} si ocurrió
     *         un error
     */
    private Factura extraerFactura(ResultSet resultSet) {
        // Crear objeto
        Factura factura = new Factura();

        // Poblar objeto
        try {
            factura.setFacturaId(resultSet.getInt("factura_id"));
            factura.setFecha(resultSet.getDate("fecha"));
        } catch (SQLException e) {
            factura = null;

            Utilidades.extraerError(this.getClass().getSimpleName(), "extraerFactura", e);
        }

        return factura;
    }

    // CRUD
    // -----------------------------------------------------------------------------------------

    /**
     * Agrega un nuevo registro al repositorio
     * 
     * @param registro objeto {@link Factura} con la información a agregar
     * @return {@code true} si el registro fue agregado, {@code false} en cualquier
     *         otro caso
     */
    @Override
    public boolean agregarRegistro(Factura registro) {
        // Crear respuesta
        boolean agregado = false;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = "INSERT INTO " + TABLA + " (factura_id, fecha) VALUES (?, ?)";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Poblar consulta
                ps.setInt(1, registro.getFacturaId());
                ps.setDate(2, registro.getFecha());

                // Ejecutar consulta
                agregado = ps.executeUpdate() > 0;
            } catch (SQLException e) {
                Utilidades.extraerError(this.getClass().getSimpleName(), "agregarRegistro", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return agregado;
    }

    /**
     * Busca un registro en el repositorio
     * 
     * @param id identificador numérico del registro
     * @return un objeto {@link Factura} con el resultado, {@code null} en cualquier
     *         otro caso
     */
    @Override
    public Factura buscarPorId(int id) {
        // Crear factura
        Factura factura = null;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = BASE_SELECT + " WHERE factura_id = ?";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Poblar consulta
                ps.setInt(1, id);

                // Ejecutar consulta
                ResultSet rs = ps.executeQuery();

                // Verificar resultados
                if (rs.next()) {
                    factura = this.extraerFactura(rs);
                }
            } catch (SQLException e) {
                Utilidades.extraerError(this.getClass().getSimpleName(), "buscarPorId", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return factura;
    }

    /**
     * Busca todos los registros en el repositorio
     * 
     * @return un objeto {@link List} con los resultados, {@code null} en cualquier
     *         otro caso
     */
    @Override
    public List<Factura> buscarTodos() {
        // Crear factura
        List<Factura> facturas = null;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = BASE_SELECT + " ORDER BY fecha DESC";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Ejecutar consulta
                ResultSet rs = ps.executeQuery();

                // Verificar resultados
                if (rs.next()) {
                    // Inicializar listado
                    facturas = new ArrayList<>();

                    // Extraer resultados
                    do {
                        Factura factura = this.extraerFactura(rs);

                        // Agregar factura a la lista
                        facturas.add(factura);
                    } while (rs.next());
                }
            } catch (SQLException e) {
                Utilidades.extraerError(this.getClass().getSimpleName(), "buscarTodos", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return facturas;
    }

    /**
     * Busca los registros en el repositorio que coincidan con la fecha
     * 
     * @param fecha fecha de emisión de la {@link Factura}
     * @return un objeto {@link List} con los resultados, {@code null} en cualquier
     *         otro caso
     */
    @Override
    public List<Factura> buscarPorFecha(Date fecha) {
        // Crear factura
        List<Factura> facturas = null;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = BASE_SELECT + " WHERE fecha = ?";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Poblar consulta
                ps.setDate(1, fecha);

                // Ejecutar consulta
                ResultSet rs = ps.executeQuery();

                // Verificar resultados
                if (rs.next()) {
                    // Inicializar listado
                    facturas = new ArrayList<>();

                    // Extraer resultados
                    do {
                        Factura factura = this.extraerFactura(rs);

                        // Agregar factura a la lista
                        facturas.add(factura);
                    } while (rs.next());
                }
            } catch (SQLException e) {
                Utilidades.extraerError(this.getClass().getSimpleName(), "buscarPorFecha", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return facturas;
    }

    /**
     * Actualiza la información de un registro en el repositorio
     * 
     * @param registro objeto {@link Factura} con la información a actualizar
     * @return {@code true} si el registro fue actualizado, {@code false} en
     *         cualquier otro caso
     */
    @Override
    public boolean actualizarRegistro(Factura registro) {
        // Crear respuesta
        boolean actualizado = false;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar conexion
        if (con != null) {
            try {
                // Definir consulta
                String sql = "UPDATE " + TABLA + " SET fecha = ? WHERE factura_id = ?";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Poblar consulta
                ps.setDate(1, registro.getFecha());
                ps.setInt(2, registro.getFacturaId());

                // Ejecutar consulta
                actualizado = ps.executeUpdate() > 0;
            } catch (SQLException e) {
                Utilidades.extraerError(this.getClass().getSimpleName(), "actualizarRegistro", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return actualizado;
    }

    /**
     * Elimina un registro del repositorio
     * 
     * @param registro objeto {@link Factura} a eliminar
     * @return {@code true} si el registro fue eliminado, {@code false} en cualquier
     *         otro caso
     */
    @Override
    public boolean eliminarRegistro(Factura registro) {
        // Crear respuesta
        boolean eliminado = false;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar conexion
        if (con != null) {
            try {
                // Definir consulta
                String sql = "DELELE FROM " + TABLA + " WHERE factura_id = ?";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Poblar consulta
                ps.setInt(1, registro.getFacturaId());

                // Ejecutar consulta
                eliminado = ps.executeUpdate() > 0;
            } catch (SQLException e) {
                Utilidades.extraerError(this.getClass().getSimpleName(), "eliminarRegistro", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return eliminado;
    }

}
