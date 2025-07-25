
package modelo;

public class Inventario {
    private int id;
    private String producto;
    private int cantidad;

    // Constructor vacío
    public Inventario() {}

    // Constructor con parámetros
    public Inventario(int id, String producto, int cantidad) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Inventario(String producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}