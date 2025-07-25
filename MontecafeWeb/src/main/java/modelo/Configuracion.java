
package modelo;

import java.io.Serializable;

public class Configuracion implements Serializable {
    private int id;
    private String nombreUsuario;
    private String correo;
    private String password;

    // Constructor vacío (requerido por JavaBeans y frameworks)
    public Configuracion() {
    }

    // Constructor con parámetros
    public Configuracion(int id, String nombreUsuario, String correo, String password) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.password = password;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    // toString (útil para depuración)
    @Override
    public String toString() {
        return "Configuracion{" +
                "id=" + id +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", correo='" + correo + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}