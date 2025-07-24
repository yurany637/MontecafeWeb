
package modelo;

public class MateriaPrima {
    private int id;
    private String nombre;
    private String unidad;
    private int stock;

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}