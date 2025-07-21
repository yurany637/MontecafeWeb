
package dao;

import java.sql.*;
import modelo.materiaPrima;

public class materiaPrimaDAO {

    String url = "jdbc:mysql://localhost:3306/montecafeweb";
    String user = "root";
    String password = "";

    public void registrar(materiaPrima m) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO materia_prima (nombre, cantidad, stock) VALUES (?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, m.getNombre());
                ps.setInt(2, m.getCantidad());
                ps.setInt(3, m.getStock());
                ps.executeUpdate();
            }
        }
    }
}