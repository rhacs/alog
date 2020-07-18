package cl.rhacs.alog.controladores;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.rhacs.alog.modelos.Conexion;
import cl.rhacs.alog.modelos.Factura;
import cl.rhacs.alog.modelos.Item;
import cl.rhacs.alog.repositorios.FacturasRepositorio;
import cl.rhacs.alog.repositorios.ItemsRepositorio;

@WebServlet(name = "FacturasController", urlPatterns = { "", "/facturas/*" })
public class FacturasController extends HttpServlet {

    // Constantes
    // -----------------------------------------------------------------------------------------

    /** Número de serie de la clase */
    private static final long serialVersionUID = 3596223343119128942L;

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Objeto {@link FacturasRepositorio} con los métodos CRUD para {@link Factura}s
     */
    private FacturasRepositorio facturasRepositorio;

    /** Objeto {@link ItemsRepositorio} con los métodos CRUD para {@link Item}s */
    private ItemsRepositorio itemsRepositorio;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia vacía del objeto {@link FacturasController}
     */
    public FacturasController() {
        super();
    }

    // Métodos
    // -----------------------------------------------------------------------------------------

    /**
     * Inicializa el {@link HttpServlet}
     * 
     * @throws ServletException si un error interrumpe el funcionamiento normal del
     *                          {@link HttpServlet}
     */
    @Override
    public void init() throws ServletException {
        // Contexto
        ServletContext contexto = this.getServletContext();

        // Obtener configuración
        String url = contexto.getInitParameter("jdbcURL");
        String user = contexto.getInitParameter("jdbcUsername");
        String pass = contexto.getInitParameter("jdbcPassword");

        // Inicializar instancia de conexión
        Conexion conexion = Conexion.getInstance(url, user, pass);

        // Inicializar repositorios
        facturasRepositorio = new FacturasRepositorio(conexion);
        itemsRepositorio = new ItemsRepositorio(conexion);
    }

    /**
     * Muestra el listado de facturas almacenadas en el repositorio
     * 
     * @param req objeto {@link HttpServletRequest} con la consulta que le hace el
     *            cliente al {@link HttpServlet}
     * @param res objeto {@link HttpServletResponse} con la respuesta que le envía
     *            el {@link HttpServlet} al cliente
     * @throws ServletException si un error interrumpe el normal funcionamiento del
     *                          {@link HttpServlet}
     * @throws IOException      si un error de entrada/salida ocurre mientras el
     *                          {@link HttpServlet} intenta resolver la respuesta
     */
    private void mostrarListado(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Buscar todas las facturas
        List<Factura> facturas = facturasRepositorio.buscarTodos();

        // Verificar si hay resultados
        if (facturas != null) {
            // Buscar los ítems de las facturas
            facturas.forEach(factura -> {
                List<Item> items = itemsRepositorio.buscarPorFacturaId(factura.getFacturaId());

                // Verificar si hay resultados
                if (items != null) {
                    // Agregar los items a la factura
                    items.forEach(item -> factura.agregarItem(item));
                }
            });
        }

        // Agregar el listado al request
        req.setAttribute("facturas", facturas);

        // Mostrar vista
        req.getRequestDispatcher("/WEB-INF/vistas/facturas.jsp").forward(req, res);
    }

    /**
     * Muestra el listado de facturas almacenadas en el repositorio
     * 
     * @param req objeto {@link HttpServletRequest} con la consulta que le hace el
     *            cliente al {@link HttpServlet}
     * @param res objeto {@link HttpServletResponse} con la respuesta que le envía
     *            el {@link HttpServlet} al cliente
     * @param id  identificador numérico de la {@link Factura}
     * @throws ServletException si un error interrumpe el normal funcionamiento del
     *                          {@link HttpServlet}
     * @throws IOException      si un error de entrada/salida ocurre mientras el
     *                          {@link HttpServlet} intenta resolver la respuesta
     */
    private void mostrarDetalles(HttpServletRequest req, HttpServletResponse res, int id)
            throws ServletException, IOException {
        // Buscar factura
        Factura factura = facturasRepositorio.buscarPorId(id);

        // Verificar si existe
        if (factura != null) {
            // Buscar items
            List<Item> items = itemsRepositorio.buscarPorFacturaId(id);

            // Verificar si hay items
            if (items != null) {
                // Agregar items a la factura
                items.forEach(item -> factura.agregarItem(item));
            }

            // Agregar a la solicitud
            req.setAttribute("factura", factura);
            req.setAttribute("items", items);
        } else {
            // Agregar error
            req.setAttribute("error", "La Factura con el identificador '" + id + "' no existe en el repositorio");
        }

        // Mostrar vista
        req.getRequestDispatcher("/WEB-INF/vistas/facturas/detalles.jsp").forward(req, res);
    }

    // Solicitudes
    // -----------------------------------------------------------------------------------------

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtener URI
        String uri = req.getRequestURI();

        // Crear matcher para detalles
        Matcher detalles = Pattern.compile("\\/alog\\/facturas\\/([0-9]+)").matcher(uri);

        // Filtrar uri
        if (uri.equals("/alog/")) {
            this.mostrarListado(req, resp);
        } else if (detalles.matches()) {
            // Extraer identificador
            int id = Integer.parseInt(detalles.group(1));

            // Ejecutar método correspondiente
            this.mostrarDetalles(req, resp, id);
        }
    }
    
}
