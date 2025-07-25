package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Configuracion;

public class ConfiguracionDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/montecafeweb?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private static final String SQL_INSERT
            = "INSERT INTO configuracion (nombreUsuario, correo, password) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE
            = "UPDATE configuracion SET nombreUsuario = ?, correo = ?, password = ? WHERE id = ?";
    private static final String SQL_FIND_BY_ID
            = "SELECT id, nombreUsuario, correo, password FROM configuracion WHERE id = ?";
    private static final String SQL_FIND_ALL
            = "SELECT id, nombreUsuario, correo, password FROM configuracion";
    private static final String SQL_DELETE
            = "DELETE FROM configuracion WHERE id = ?";

    // INSERT - devuelve el id generado
    public int insertar(Configuracion c) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement ps = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {

                ps.setString(1, c.getNombreUsuario());
                ps.setString(2, c.getCorreo());
                ps.setString(3, c.getPassword());

                int filas = ps.executeUpdate();
                if (filas == 0) {
                    return 0;
                }

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idGenerado = rs.getInt(1);
                        c.setId(idGenerado);
                        return idGenerado;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // UPDATE
    public boolean actualizar(Configuracion c) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement ps = con.prepareStatement(SQL_UPDATE)) {

                ps.setString(1, c.getNombreUsuario());
                ps.setString(2, c.getCorreo());
                ps.setString(3, c.getPassword());
                ps.setInt(4, c.getId());
                return ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // SELECT por id
    public Configuracion obtenerPorId(int id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID)) {

                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Configuracion c = new Configuracion();
                        c.setId(rs.getInt("id"));
                        c.setNombreUsuario(rs.getString("nombreUsuario"));
                        c.setCorreo(rs.getString("correo"));
                        c.setPassword(rs.getString("password"));
                        return c;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // SELECT * 
    public List<Configuracion> listar() {
        List<Configuracion> lista = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement ps = con.prepareStatement(SQL_FIND_ALL); ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Configuracion c = new Configuracion();
                    c.setId(rs.getInt("id"));
                    c.setNombreUsuario(rs.getString("nombreUsuario"));
                    c.setCorreo(rs.getString("correo"));
                    c.setPassword(rs.getString("password"));
                    lista.add(c);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    // DELETE
    public boolean eliminar(int id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement ps = con.prepareStatement(SQL_DELETE)) {

                ps.setInt(1, id);
                return ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Helper: guarda (insert/update según id)
    public boolean guardar(Configuracion c) {
        if (c.getId() == 0) {
            return insertar(c) > 0;
        } else {
            return actualizar(c);
        }
    }
    // LOGIN: verificar si existe el usuario

    public Configuracion autenticar(String nombreUsuario, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sql = "SELECT id, nombreUsuario, correo, password FROM configuracion WHERE nombreUsuario = ? AND password = ?";
            try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, nombreUsuario);
                ps.setString(2, password);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Configuracion c = new Configuracion();
                        c.setId(rs.getInt("id"));
                        c.setNombreUsuario(rs.getString("nombreUsuario"));
                        c.setCorreo(rs.getString("correo"));
                        c.setPassword(rs.getString("password"));
                        return c;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
