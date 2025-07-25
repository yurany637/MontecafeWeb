
package dao;

import java.sql.*;

public class DashboardDAO {
    private String url = "jdbc:mysql://localhost:3306/montecafeweb?useSSL=false&serverTimezone=UTC";
    private String user = "root";
    private String password = "1234"; // Ajusta tu clave

    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }

    public int contarProveedores() {
        return contar("SELECT COUNT(*) FROM proveedores");
    }

    public int contarProductos() {
        return contar("SELECT COUNT(*) FROM inventario");
    }

    public int contarVentas() {
        return contar("SELECT COUNT(*) FROM ventas WHERE MONTH(fecha)=MONTH(CURDATE())");
    }

    public int contarPedidos() {
        return contar("SELECT COUNT(*) FROM pedidos WHERE estado='activo'");
    }

    private int contar(String sql) {
        int total = 0;
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }
}