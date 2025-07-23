package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Clientes;

public class ClientesDAO {

    String url = "jdbc:mysql://localhost:3306/montecafeweb?useSSL=false&serverTimezone=UTC";
    String user = "root";
    String password = "1234"; // SIN espacio

    public void registrar(Clientes c) {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/montecafeweb?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "1234"; // Sin espacios extra

        Connection con = DriverManager.getConnection(url, user, password);
        String sql = "INSERT INTO clientes (nombre, telefono, correo, direccion) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, c.getNombre());
        ps.setString(2, c.getTelefono());
        ps.setString(3, c.getCorreo());
        ps.setString(4, c.getDireccion());

        ps.executeUpdate();
        ps.close();
        con.close();
    } catch (Exception e) {
        // MOSTRAR EL ERROR REAL
        System.err.println("⚠ Error al registrar el cliente: " + e.getMessage());
        e.printStackTrace();
        throw new RuntimeException("Error al registrar cliente", e);
    }
}

    public List<Clientes> listar() throws Exception {
        List<Clientes> lista = new ArrayList<>();

        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM clientes";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Clientes c = new Clientes();
                    c.setId(rs.getInt("id"));
                    c.setNombre(rs.getString("nombre"));
                    c.setTelefono(rs.getString("telefono"));
                    c.setCorreo(rs.getString("correo"));
                    c.setDireccion(rs.getString("direccion"));
                    lista.add(c);
                }
                rs.close();
            }
        }

        return lista;
    }
}
