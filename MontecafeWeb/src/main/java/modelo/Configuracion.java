
package modelo;

public class Configuracion {
    private int id;
    private String nombreUsuario;
    private String correo;
    private String password;
    private String rol;

    public Configuracion() {}

    public Configuracion(int id, String nombreUsuario, String correo, String password, String rol) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.password = password;
        this.rol = rol;
    }

    // Getters y Setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}