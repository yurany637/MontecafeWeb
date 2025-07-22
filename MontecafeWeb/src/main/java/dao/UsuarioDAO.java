
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Usuario;

public class UsuarioDAO {

    String url = "jdbc:mysql://localhost:3306/montecafeweb?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    String user = "root";
    String password = "TuContraseña";

    public Usuario validar(Usuario usuario) throws Exception {
        Usuario resultado = null;

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, user, password);

        String sql = "SELECT * FROM configuracion WHERE nombre_usuario = ? AND password = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, usuario.getNombreUsuario());
        ps.setString(2, usuario.getPassword());

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            resultado = new Usuario();
            resultado.setNombreUsuario(rs.getString("nombre_usuario"));
            resultado.setRol(rs.getString("rol"));
        }

        rs.close();
        ps.close();
        con.close();

        return resultado; // Si no encontró nada, devuelve null.
    }
}