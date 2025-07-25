
package dao;

import modelo.Venta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentasDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/montecafeweb?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private static final String SQL_INSERT =
        "INSERT INTO ventas (cliente, producto, cantidad, total) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE =
        "UPDATE ventas SET cliente=?, producto=?, cantidad=?, total=? WHERE id=?";
    private static final String SQL_DELETE =
        "DELETE FROM ventas WHERE id=?";
    private static final String SQL_FIND_ALL =
        "SELECT * FROM ventas";
    private static final String SQL_FIND_BY_ID =
        "SELECT * FROM ventas WHERE id=?";

    public List<Venta> listar() {
        List<Venta> lista = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(SQL_FIND_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Venta v = new Venta();
                v.setId(rs.getInt("id"));
                v.setCliente(rs.getString("cliente"));
                v.setProducto(rs.getString("producto"));
                v.setCantidad(rs.getInt("cantidad"));
                v.setTotal(rs.getDouble("total"));
                lista.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Venta obtenerPorId(int id) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Venta v = new Venta();
                    v.setId(rs.getInt("id"));
                    v.setCliente(rs.getString("cliente"));
                    v.setProducto(rs.getString("producto"));
                    v.setCantidad(rs.getInt("cantidad"));
                    v.setTotal(rs.getDouble("total"));
                    return v;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertar(Venta v) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(SQL_INSERT)) {
            ps.setString(1, v.getCliente());
            ps.setString(2, v.getProducto());
            ps.setInt(3, v.getCantidad());
            ps.setDouble(4, v.getTotal());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean actualizar(Venta v) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(SQL_UPDATE)) {
            ps.setString(1, v.getCliente());
            ps.setString(2, v.getProducto());
            ps.setInt(3, v.getCantidad());
            ps.setDouble(4, v.getTotal());
            ps.setInt(5, v.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminar(int id) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(SQL_DELETE)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}