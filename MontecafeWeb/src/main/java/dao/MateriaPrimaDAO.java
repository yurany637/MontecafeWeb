
package dao;

import modelo.MateriaPrima;
import java.sql.*;
import java.util.*;

public class MateriaPrimaDAO {
    String url = "jdbc:mysql://localhost:3306/montecafeweb?useSSL=false&serverTimezone=UTC";
    String user = "root";
    String password = "1234"; // Ajusta tu contraseña

    public boolean registrar(MateriaPrima m) {
        String sql = "INSERT INTO materia_prima (nombre, unidad, stock) VALUES (?, ?, ?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(url, user, password);
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, m.getNombre());
                ps.setString(2, m.getUnidad());
                ps.setInt(3, m.getStock());

                ps.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<MateriaPrima> listar() {
        List<MateriaPrima> lista = new ArrayList<>();
        String sql = "SELECT * FROM materia_prima";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(url, user, password);
                 Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery(sql)) {

                while (rs.next()) {
                    MateriaPrima m = new MateriaPrima();
                    m.setId(rs.getInt("id"));
                    m.setNombre(rs.getString("nombre"));
                    m.setUnidad(rs.getString("unidad"));
                    m.setStock(rs.getInt("stock"));
                    lista.add(m);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}