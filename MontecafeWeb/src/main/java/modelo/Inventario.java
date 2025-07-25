
package modelo;

public class Inventario {
    private int id;
    private String producto;
    private int stock;
    private String categoria;

    public Inventario() {
    }

    public Inventario(int id, String producto, int stock, String categoria) {
        this.id = id;
        this.producto = producto;
        this.stock = stock;
        this.categoria = categoria;
    }

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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}