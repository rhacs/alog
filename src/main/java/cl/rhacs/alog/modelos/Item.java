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
     */
    public Item(int itemId, String descripcion, double precioUnitario) {
        this.itemId = itemId;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
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

    // Herencias (Object)
    // -----------------------------------------------------------------------------------------

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
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

        if (descripcion == null) {
            if (other.descripcion != null)
                return false;
        } else if (!descripcion.equals(other.descripcion))
            return false;

        if (itemId != other.itemId)
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "Item [itemId=" + itemId + ", descripcion=" + descripcion + ", precioUnitario=" + precioUnitario + "]";
    }
}
