
package dao;

import java.sql.*;
import modelo.Configuracion;

public class ConfiguracionDAO {

    String url = "jdbc:mysql://localhost:3306/montecafeweb";
    String user = "root";
    String password = "";

    public void registrar(Configuracion u) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO configuracion (nombre_usuario, correo, password, rol) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, u.getNombreUsuario());
                ps.setString(2, u.getCorreo());
                ps.setString(3, u.getPassword());
                ps.setString(4, u.getRol());
                ps.executeUpdate();
            }
        }
    }
}
