
package dao;

import java.sql.*;
import java.util.*;
import modelo.Inventario;

public class InventarioDAO {

    String url = "jdbc:mysql://localhost:3306/montecafeweb?useSSL=false&serverTimezone=UTC";
    String user = "root";
    String password = "1234"; // Ajusta si es necesario

    public boolean registrar(Inventario inv) {
        String sql = "INSERT INTO inventario (producto, categoria, stock, unidad, estado) VALUES (?, ?, ?, ?, ?)";
        boolean exito = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(url, user, password);
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, inv.getProducto());
                ps.setString(2, inv.getCategoria());
                ps.setInt(3, inv.getStock());
                ps.setString(4, inv.getUnidad());
                ps.setString(5, inv.getEstado());

                int filasAfectadas = ps.executeUpdate();
                exito = (filasAfectadas > 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return exito;
    }

    public List<Inventario> listar() {
        List<Inventario> lista = new ArrayList<>();
        String sql = "SELECT * FROM inventario";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(url, user, password);
                 Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery(sql)) {

                while (rs.next()) {
                    Inventario inv = new Inventario();
                    inv.setId(rs.getInt("id"));
                    inv.setProducto(rs.getString("producto"));
                    inv.setCategoria(rs.getString("categoria"));
                    inv.setStock(rs.getInt("stock"));
                    inv.setUnidad(rs.getString("unidad"));
                    inv.setEstado(rs.getString("estado"));
                    lista.add(inv);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}