
package modelo;

public class Usuario {
    private String nombreUsuario;
    private String password;
    private String rol;

    public Usuario() {}

    public Usuario(String nombreUsuario, String password) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
    }

    // Getters y Setters

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}