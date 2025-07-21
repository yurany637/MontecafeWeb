
package modelo;

public class Proveedor {
    private int id;
    private String nombre;
    private String contacto;
    private String correo;
    private String direccion;

    public Proveedor() {}

    public Proveedor(int id, String nombre, String contacto, String correo, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.contacto = contacto;
        this.correo = correo;
        this.direccion = direccion;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getContacto() { return contacto; }
    public void setContacto(String contacto) { this.contacto = contacto; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
}
