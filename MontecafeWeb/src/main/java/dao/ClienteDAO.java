
package dao;

import modelo.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/montecafeweb?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private static final String SQL_INSERT = 
        "INSERT INTO clientes (nombre, correo, telefono) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE = 
        "UPDATE clientes SET nombre=?, correo=?, telefono=? WHERE id=?";
    private static final String SQL_DELETE = 
        "DELETE FROM clientes WHERE id=?";
    private static final String SQL_SELECT_ALL = 
        "SELECT * FROM clientes";
    private static final String SQL_SELECT_BY_ID = 
        "SELECT * FROM clientes WHERE id=?";

    public boolean insertar(Cliente c) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(SQL_INSERT)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getCorreo());
            ps.setString(3, c.getTelefono());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Cliente c) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(SQL_UPDATE)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getCorreo());
            ps.setString(3, c.getTelefono());
            ps.setInt(4, c.getId());
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

    public Cliente obtenerPorId(int id) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(SQL_SELECT_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cliente c = new Cliente();
                    c.setId(rs.getInt("id"));
                    c.setNombre(rs.getString("nombre"));
                    c.setCorreo(rs.getString("correo"));
                    c.setTelefono(rs.getString("telefono"));
                    return c;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(SQL_SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setCorreo(rs.getString("correo"));
                c.setTelefono(rs.getString("telefono"));
                lista.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}