
package dao;

import modelo.MateriaPrima;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MateriaPrimaDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/montecafeweb?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private static final String SQL_INSERT =
        "INSERT INTO materia_prima (nombre, cantidad, unidad) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE =
        "UPDATE materia_prima SET nombre=?, cantidad=?, unidad=? WHERE id=?";
    private static final String SQL_DELETE =
        "DELETE FROM materia_prima WHERE id=?";
    private static final String SQL_FIND_ALL =
        "SELECT * FROM materia_prima";
    private static final String SQL_FIND_BY_ID =
        "SELECT * FROM materia_prima WHERE id=?";

    public List<MateriaPrima> listar() {
        List<MateriaPrima> lista = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(SQL_FIND_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                MateriaPrima mp = new MateriaPrima();
                mp.setId(rs.getInt("id"));
                mp.setNombre(rs.getString("nombre"));
                mp.setCantidad(rs.getInt("cantidad"));
                mp.setUnidad(rs.getString("unidad"));
                lista.add(mp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public MateriaPrima obtenerPorId(int id) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    MateriaPrima mp = new MateriaPrima();
                    mp.setId(rs.getInt("id"));
                    mp.setNombre(rs.getString("nombre"));
                    mp.setCantidad(rs.getInt("cantidad"));
                    mp.setUnidad(rs.getString("unidad"));
                    return mp;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertar(MateriaPrima mp) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(SQL_INSERT)) {
            ps.setString(1, mp.getNombre());
            ps.setInt(2, mp.getCantidad());
            ps.setString(3, mp.getUnidad());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean actualizar(MateriaPrima mp) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(SQL_UPDATE)) {
            ps.setString(1, mp.getNombre());
            ps.setInt(2, mp.getCantidad());
            ps.setString(3, mp.getUnidad());
            ps.setInt(4, mp.getId());
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