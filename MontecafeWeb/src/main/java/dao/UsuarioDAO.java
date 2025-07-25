
package dao;

import modelo.Usuario;
import java.sql.*;

public class UsuarioDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/montecafeweb?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private static final String SQL_VALIDAR = 
        "SELECT * FROM usuarios WHERE nombreUsuario=? AND contrasena=?";

    public Usuario validar(Usuario user) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement ps = con.prepareStatement(SQL_VALIDAR)) {

                ps.setString(1, user.getNombreUsuario());
                ps.setString(2, user.getContrasena());

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Usuario u = new Usuario();
                        u.setId(rs.getInt("id"));
                        u.setNombreUsuario(rs.getString("nombreUsuario"));
                        u.setContrasena(rs.getString("contrasena"));
                        u.setRol(rs.getString("rol"));
                        return u;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}