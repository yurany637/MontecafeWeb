
package modelo;

public class MateriaPrima {
    private int id;
    private String nombre;
    private int cantidad;
    private String unidad;

    // Constructor vacío
    public MateriaPrima() {}

    // Constructor con parámetros
    public MateriaPrima(int id, String nombre, int cantidad, String unidad) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidad = unidad;
    }

    public MateriaPrima(String nombre, int cantidad, String unidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidad = unidad;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }
}