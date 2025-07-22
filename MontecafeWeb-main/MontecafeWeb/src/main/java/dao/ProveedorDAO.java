
package dao;

import modelo.Proveedor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {
    String url = "jdbc:mysql://localhost:3306/montecafeweb";
    String user = "root";
    String password = "";

    public void registrar(Proveedor p) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO proveedores (nombre, contacto, correo, direccion) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getContacto());
            ps.setString(3, p.getCorreo());
            ps.setString(4, p.getDireccion());
            ps.executeUpdate();
        }
    }

    public List<Proveedor> listar() throws Exception {
        List<Proveedor> lista = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM proveedores";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setContacto(rs.getString("contacto"));
                p.setCorreo(rs.getString("correo"));
                p.setDireccion(rs.getString("direccion"));
                lista.add(p);
            }
        }
        return lista;
    }
}