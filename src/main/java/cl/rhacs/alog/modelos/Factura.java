package cl.rhacs.alog.modelos;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class Factura {

    // Constantes
    // -----------------------------------------------------------------------------------------

    /** Impuesto al Valor Agregado */
    public static final int IVA = 19;

    // Atributos
    // -----------------------------------------------------------------------------------------

    /** Identificador numérico de la {@link Factura} en la base de datos */
    private int facturaId;

    /** Fecha en la que se emitió la factura */
    private Date fecha;

    /** Listado de {@link Item}s de la {@link Factura} */
    private Set<Item> items;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia vacía del objeto {@link Factura}
     */
    public Factura() {
        this.items = new HashSet<Item>();
    }

    /**
     * Crea una nueva instancia del objeto {@link Factura}
     * 
     * @param facturaId identificador numérico
     * @param fecha     fecha de emisión
     * @param items     listado de {@link Item}s correspondientes a la
     *                  {@link Factura}
     */
    public Factura(int facturaId, Date fecha, Set<Item> items) {
        this.facturaId = facturaId;
        this.fecha = fecha;
        this.items = items;
    }

    // Métodos
    // -----------------------------------------------------------------------------------------

    /**
     * Agrega un {@link Item} al listado
     * 
     * @param item objeto {@link Item} a agregar
     * @return {@code true} si el objeto no existía, de lo contrario {@code false}
     */
    public boolean agregarItem(Item item) {
        return this.items.add(item);
    }

    /**
     * Elimina un {@link Item} del listado
     * 
     * @param item objeto {@link Item} a eliminar
     * @return {@code true} si el objeto estaba presente, de lo contrario
     *         {@code false}
     */
    public boolean quitarItem(Item item) {
        return this.items.remove(item);
    }

    /**
     * @return el número de elementos presentes en el listado
     */
    public int contarItems() {
        return this.items.size();
    }

    public double calcularSubtotal() {
        // Verificar si no hay elementos
        if (items.isEmpty()) {
            return 0;
        }

        // Inicializar subtotal
        double subtotal = 0;

        // Por cada item, sumar su precio unitario por la cantidad al subtotal
        for (Item item : items) {
            subtotal += item.calcularSubtotal();
        }

        return subtotal;
    }

    /**
     * @return el valor total, incluido el iva, de la {@link Factura}
     */
    public double calcularTotal() {
        // Verificar si no hay elementos
        if (items.isEmpty()) {
            return 0;
        }

        // Inicializar total
        double total = 0;

        // Por cada item, obtener el total
        for (Item item : items) {
            total += item.calcularTotal();
        }

        return total;
    }

    /**
     * @return el valor del Impuesto
     */
    public double calcularImpuesto() {
        return this.calcularTotal() - this.calcularSubtotal();
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return el identificador numérico
     */
    public int getFacturaId() {
        return facturaId;
    }

    /**
     * @return la fecha de emisión
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @return el listado de {@link Item}s
     */
    public Set<Item> getItems() {
        return items;
    }

    // Setters
    // -----------------------------------------------------------------------------------------

    /**
     * @param facturaId el identificador numérico a establecer
     */
    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
    }

    /**
     * @param fecha la fecha de emisión a establecer
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @param items el listado de {@link Item}s a establecer
     */
    public void setItems(Set<Item> items) {
        this.items = items;
    }

    // Herencias (Object)
    // -----------------------------------------------------------------------------------------

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + facturaId;

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        Factura other = (Factura) obj;

        if (facturaId != other.facturaId)
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "Factura [facturaId=" + facturaId + ", fecha=" + fecha + ", items=" + items + "]";
    }

}
