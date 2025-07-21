
package modelo;

public class materiaPrima {
    private int id;
    private String nombre;
    private int cantidad;
    private int stock;

    public materiaPrima() {}

    public materiaPrima(int id, String nombre, int cantidad, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.stock = stock;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}
