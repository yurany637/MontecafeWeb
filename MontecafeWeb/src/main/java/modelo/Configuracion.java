
package modelo;

import java.io.Serializable;

public class Configuracion implements Serializable {
    private int id;
    private String nombreUsuario;
    private String correo;
    private String password;

    public Configuracion() {}

    public Configuracion(int id, String nombreUsuario, String correo, String password) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.password = password;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}