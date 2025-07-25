
package dao;

import modelo.Proveedor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/montecafeweb?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private static final String SQL_INSERT = 
        "INSERT INTO proveedores (nombre, telefono, correo) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE = 
        "UPDATE proveedores SET nombre=?, telefono=?, correo=? WHERE id=?";
    private static final String SQL_DELETE = 
        "DELETE FROM proveedores WHERE id=?";
    private static final String SQL_SELECT_ALL = 
        "SELECT * FROM proveedores";
    private static final String SQL_SELECT_BY_ID = 
        "SELECT * FROM proveedores WHERE id=?";

    public boolean insertar(Proveedor p) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(SQL_INSERT)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getTelefono());
            ps.setString(3, p.getCorreo());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Proveedor p) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(SQL_UPDATE)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getTelefono());
            ps.setString(3, p.getCorreo());
            ps.setInt(4, p.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(SQL_DELETE)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Proveedor obtenerPorId(int id) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(SQL_SELECT_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Proveedor p = new Proveedor();
                    p.setId(rs.getInt("id"));
                    p.setNombre(rs.getString("nombre"));
                    p.setTelefono(rs.getString("telefono"));
                    p.setCorreo(rs.getString("correo"));
                    return p;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Proveedor> listar() {
        List<Proveedor> lista = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(SQL_SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setTelefono(rs.getString("telefono"));
                p.setCorreo(rs.getString("correo"));
                lista.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}