
package dao;

import modelo.Ventas;
import java.sql.*;
import java.util.*;

public class VentasDAO {
    String url = "jdbc:mysql://localhost:3306/montecafeweb?useSSL=false&serverTimezone=UTC";
    String user = "root";
    String password = "1234";

    public boolean registrar(Ventas v) {
        String sql = "INSERT INTO ventas (cliente, producto, cantidad, precioUnitario, total, fecha) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, v.getCliente());
            ps.setString(2, v.getProducto());
            ps.setInt(3, v.getCantidad());
            ps.setDouble(4, v.getPrecioUnitario());
            ps.setDouble(5, v.getTotal());
            ps.setString(6, v.getFecha());

            int resultado = ps.executeUpdate();
            ps.close();
            con.close();

            return resultado > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Ventas> listar() {
        List<Ventas> lista = new ArrayList<>();
        String sql = "SELECT * FROM ventas";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Ventas v = new Ventas();
                v.setId(rs.getInt("id"));
                v.setCliente(rs.getString("cliente"));
                v.setProducto(rs.getString("producto"));
                v.setCantidad(rs.getInt("cantidad"));
                v.setPrecioUnitario(rs.getDouble("precioUnitario")); // 👈 Asegúrate que en la tabla esté como 'precioUnitario'
                v.setTotal(rs.getDouble("total"));
                v.setFecha(rs.getString("fecha"));
                lista.add(v);
            }

            rs.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}