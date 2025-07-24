
package dao;

import modelo.Proveedor;
import java.sql.*;
import java.util.*;

public class ProveedorDAO {
    String url = "jdbc:mysql://localhost:3306/montecafeweb";
    String user = "root";
    String password = "1234";

    public boolean registrar(Proveedor p) {
        String sql = "INSERT INTO proveedores (nombre, contacto, correo, direccion) VALUES (?, ?, ?, ?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            int resultado;
            try (Connection con = DriverManager.getConnection(url, user, password)) {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, p.getNombre());
                ps.setString(2, p.getContacto());
                ps.setString(3, p.getCorreo());
                ps.setString(4, p.getDireccion());
                resultado = ps.executeUpdate();
                ps.close();
            }

            return resultado > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Proveedor> listar() {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM proveedores";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setContacto(rs.getString("contacto"));
                p.setCorreo(rs.getString("correo"));
                p.setDireccion(rs.getString("direccion"));
                lista.add(p);
            }

            rs.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}