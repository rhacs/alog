package cl.rhacs.alog.modelos;

public class Item {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /** Identificador numérico del {@link Item} en la base de datos */
    private int itemId;

    /** Descripción del {@link Item} */
    private String descripcion;

    /** Precio unitario del {@link Item} */
    private double precioUnitario;

    /** Cantidad del {@link Item} */
    private int cantidad;

    /**
     * Identificador numérico de la {@link Factura} a la que pertenece el
     * {@link Item}
     */
    private int facturaId;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia vacía del objeto {@link Item}
     */
    public Item() {

    }

    /**
     * Crea una nueva instancia del objeto {@link Item}
     * 
     * @param itemId         identificador numérico
     * @param descripcion    descripción del producto
     * @param precioUnitario valor unitario
     * @param cantidad       cantidad del producto
     * @param facturaId      identificador numérico de la {@link Factura}
     */
    public Item(int itemId, String descripcion, double precioUnitario, int cantidad, int facturaId) {
        this.itemId = itemId;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
    }

    // Métodos
    // -----------------------------------------------------------------------------------------

    /**
     * @return el subtotal del {@link Item}
     */
    public double calcularSubtotal() {
        // Calcular el subtotal y devolver el resultado
        return precioUnitario * cantidad;
    }

    /**
     * @return el total, incluido el IVA, del {@link Item}
     */
    public double calcularTotal() {
        // Obtener el subtotal
        double subtotal = this.calcularSubtotal();

        // Calcular el IVA y devolver resultado
        return subtotal + ((subtotal * Factura.IVA) / 100);
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return el identificador numérico
     */
    public int getItemId() {
        return itemId;
    }

    /**
     * @return la descripción del producto
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @return el precio unitario
     */
    public double getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     * @return la cantidad del producto
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @return el identificador numérico de la {@link Factura}
     */
    public int getFacturaId() {
        return facturaId;
    }

    // Setters
    // -----------------------------------------------------------------------------------------

    /**
     * @param itemId el identificador numérico a establecer
     */
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    /**
     * @param descripcion la descripción a establecer
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @param precioUnitario el precio unitario a establecer
     */
    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    /**
     * @param cantidad la cantidad del producto a establecer
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @param facturaId el identificador numérico de la {@link Factura} a establecer
     */
    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
    }

    // Herencias (Object)
    // -----------------------------------------------------------------------------------------

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + itemId;

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

        Item other = (Item) obj;

        if (itemId != other.itemId)
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "Item [itemId=" + itemId + ", descripcion=" + descripcion + ", precioUnitario=" + precioUnitario
                + ", cantidad=" + cantidad + ", facturaId=" + facturaId + "]";
    }
}
