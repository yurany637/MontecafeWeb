
package dao;

import java.sql.*;
import modelo.Ventas;

public class VentasDAO {

    String url = "jdbc:mysql://localhost:3306/montecafeweb";
    String user = "root";
    String password = "";

    public void registrar(Ventas v) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO ventas (nombre_cliente, producto, cantidad, precio_unitario, total, fecha) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, v.getNombreCliente());
                ps.setString(2, v.getProducto());
                ps.setInt(3, v.getCantidad());
                ps.setDouble(4, v.getPrecioUnitario());
                ps.setDouble(5, v.getTotal());
                ps.setString(6, v.getFecha());
                ps.executeUpdate();
            }
        }
    }
}