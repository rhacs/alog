package cl.rhacs.alog.repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cl.rhacs.alog.Utilidades;
import cl.rhacs.alog.modelos.Conexion;
import cl.rhacs.alog.modelos.Item;
import cl.rhacs.alog.repositorios.interfaces.IItemsRepositorio;

public class ItemsRepositorio implements IItemsRepositorio {

    // Constantes
    // -----------------------------------------------------------------------------------------

    /** Nombre de la tabla en la base de datos */
    private final String TABLA = "alog_items";

    /** Consulta base para los SELECT */
    private final String BASE_SELECT = "SELECT item_id, descripcion, precio_unitario, cantidad, factura_id FROM "
            + TABLA;

    // Atributos
    // -----------------------------------------------------------------------------------------

    /** Objeto {@link Conexion} con los métodos de conexión a la base de datos */
    private Conexion conexion;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia del objeto {@link ItemsRepositorio}
     * 
     * @param conexion objeto {@link Conexion} con los métodos de conexión a la base
     *                 de datos
     */
    public ItemsRepositorio(Conexion conexion) {
        this.conexion = conexion;
    }

    // Métodos
    // -----------------------------------------------------------------------------------------

    /**
     * Extrae la información de un {@link ResultSet}
     * 
     * @param rs objeto {@link ResultSet} con la información
     * @return un objeto {@link Item} con el resultado, {@code null} si ocurrió un
     *         error
     */
    private Item extraerItem(ResultSet rs) {
        // Crear item
        Item item = new Item();

        try {
            // Poblar item
            item.setItemId(rs.getInt("item_id"));
            item.setDescripcion(rs.getNString("descripcion"));
            item.setPrecioUnitario(rs.getDouble("precio_unitario"));
            item.setCantidad(rs.getInt("cantidad"));
            item.setFacturaId(rs.getInt("factura_id"));
        } catch (SQLException e) {
            // Vaciar item
            item = null;

            // Extraer error
            Utilidades.extraerError(this.getClass().getSimpleName(), "extraerItem", e);
        }

        return item;
    }

    // CRUD
    // -----------------------------------------------------------------------------------------

    /**
     * Agrega un registro al repositorio
     * 
     * @param registro objeto {@link Item} a agregar
     * @return {@code true} si el registro fue agregado, {@code false} en cualquier
     *         otro caso
     */
    @Override
    public boolean agregarRegistro(Item registro) {
        // Crear respuesta
        boolean agregado = false;

        // Obtener conexión
        Connection con = conexion.conectar();

        // Verificar si hubo conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = "INSERT INTO " + TABLA + " (descripcion, precio_unitario, cantidad, factura_id) "
                        + "VALUES (?, ?, ?, ?)";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Poblar consulta
                ps.setNString(1, registro.getDescripcion());
                ps.setDouble(2, registro.getPrecioUnitario());
                ps.setInt(3, registro.getCantidad());
                ps.setInt(4, registro.getFacturaId());

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
     * @return un objeto {@link Item} con el resultado, {@code null} en cualquier
     *         otro caso
     */
    @Override
    public Item buscarPorId(int id) {
        // Crear respuesta
        Item item = null;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = BASE_SELECT + " WHERE item_id = ?";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Poblar consulta
                ps.setInt(1, id);

                // Ejecutar consulta
                ResultSet rs = ps.executeQuery();

                // Verificar si hay resultados
                if (rs.next()) {
                    // Extraer resultado
                    item = this.extraerItem(rs);
                }
            } catch (SQLException e) {
                Utilidades.extraerError(this.getClass().getSimpleName(), "buscarPorId", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return item;
    }

    /**
     * Busca todos los registros del repositorio
     * 
     * @return un objeto {@link List} con los resultados, {@code null} en cualquier
     *         otro caso
     */
    @Override
    public List<Item> buscarTodos() {
        // Crear listado
        List<Item> items = null;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar conexión
        if (con != null) {
            try {
                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(BASE_SELECT);

                // Ejecutar consulta
                ResultSet rs = ps.executeQuery();

                // Verificar si hay resultados
                if (rs.next()) {
                    // Inicializar listado
                    items = new ArrayList<>();

                    // Extraer resultados
                    do {
                        Item item = this.extraerItem(rs);

                        // Agregar al listado
                        items.add(item);
                    } while (rs.next());
                }
            } catch (SQLException e) {
                Utilidades.extraerError(this.getClass().getSimpleName(), "buscarTodos", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return items;
    }

    /**
     * Busca un listado de registros que coincidan con la descripción proporcionada
     * 
     * @param descripcion descripción a buscar
     * @return un objeto {@link List} con los resultados, {@code null} en cualquier
     *         otro caso
     */
    @Override
    public List<Item> busarPorDescripcion(String descripcion) {
        // Crear listado
        List<Item> items = null;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar conexion
        if (con != null) {
            try {
                // Definir consulta
                String sql = BASE_SELECT + " WHERE descripcion LIKE ?";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Poblar consulta
                ps.setNString(1, '%' + descripcion + '%');

                // Ejecutar consulta
                ResultSet rs = ps.executeQuery();

                // Verificar si hay resultados
                if (rs.next()) {
                    // Inicializar listado
                    items = new ArrayList<>();

                    // Extraer resultados
                    do {
                        Item item = this.extraerItem(rs);

                        // Agregar al listado
                        items.add(item);
                    } while (rs.next());
                }
            } catch (SQLException e) {
                Utilidades.extraerError(this.getClass().getSimpleName(), "buscarPorDescripcion", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return items;
    }

    /**
     * Busca los registros en el repositorio que coincidan con el precio indicado
     * 
     * @param precio valor del producto
     * @return un objeto {@link List} con los resultados, {@code null} en cualquier
     *         otro caso
     */
    @Override
    public List<Item> buscarPorPrecio(double precio) {
        // Crear listado
        List<Item> items = null;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar conexion
        if (con != null) {
            try {
                // Definir consulta
                String sql = BASE_SELECT + " WHERE precio_unitario = ?";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Poblar consulta
                ps.setDouble(1, precio);

                // Ejecutar consulta
                ResultSet rs = ps.executeQuery();

                // Verificar si hay resultados
                if (rs.next()) {
                    // Inicializar listado
                    items = new ArrayList<>();

                    // Extraer resultados
                    do {
                        Item item = this.extraerItem(rs);

                        // Agregar al listado
                        items.add(item);
                    } while (rs.next());
                }
            } catch (SQLException e) {
                Utilidades.extraerError(this.getClass().getSimpleName(), "buscarPorPrecio", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return items;
    }

    /**
     * Actualiza la información de un registro en el repositorio
     * 
     * @param registro objeto {@link Item} a actualizar
     * @return {@code true} si el registro fue actualizado, {@code false} en
     *         cualquier otro caso
     */
    @Override
    public boolean actualizarRegistro(Item registro) {
        // Crear respuesta
        boolean actualizado = false;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = "UPDATE " + TABLA + " SET descripcion = ?, precio_unitario = ?, cantidad = ?"
                        + ", factura_id = ? WHERE item_id = ?";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Poblar consulta
                ps.setNString(1, registro.getDescripcion());
                ps.setDouble(2, registro.getPrecioUnitario());
                ps.setInt(3, registro.getCantidad());
                ps.setInt(4, registro.getFacturaId());
                ps.setInt(5, registro.getItemId());

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
     * @param registro objeto {@link Item} a eliminar
     * @return {@code true} si el objeto fue eliminado, {@code false} en cualquier
     *         otro caso
     */
    @Override
    public boolean eliminarRegistro(Item registro) {
        // Crear respuesta
        boolean eliminado = false;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = "DELETE FROM " + TABLA + " WHERE item_id = ?";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Poblar consulta
                ps.setInt(1, registro.getItemId());

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
