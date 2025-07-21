
package dao;

import java.sql.*;
import modelo.Inventario;

public class InventarioDAO {

    String url = "jdbc:mysql://localhost:3306/montecafeweb";
    String user = "root";
    String password = "";

    public void registrar(Inventario i) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO inventario (codigo_producto, producto, categoria, stock, unidad, estado) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, i.getCodigoProducto());
                ps.setString(2, i.getProducto());
                ps.setString(3, i.getCategoria());
                ps.setInt(4, i.getStock());
                ps.setString(5, i.getUnidad());
                ps.setString(6, i.getEstado());
                ps.executeUpdate();
            }
        }
    }
}