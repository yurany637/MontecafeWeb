
package modelo;

public class Inventario {
    private int id;
    private String codigoProducto;
    private String producto;
    private String categoria;
    private int stock;
    private String unidad;
    private String estado;

    public Inventario() {}

    public Inventario(int id, String codigoProducto, String producto, String categoria, int stock, String unidad, String estado) {
        this.id = id;
        this.codigoProducto = codigoProducto;
        this.producto = producto;
        this.categoria = categoria;
        this.stock = stock;
        this.unidad = unidad;
        this.estado = estado;
    }

    // Getters y Setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCodigoProducto() { return codigoProducto; }
    public void setCodigoProducto(String codigoProducto) { this.codigoProducto = codigoProducto; }

    public String getProducto() { return producto; }
    public void setProducto(String producto) { this.producto = producto; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}