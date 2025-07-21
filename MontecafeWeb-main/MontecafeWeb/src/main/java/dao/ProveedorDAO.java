
package dao;

import java.sql.*;
import modelo.Proveedor;

public class ProveedorDAO {

    String url = "jdbc:mysql://localhost:3306/montecafeweb";
    String user = "root";
    String password = "";

    public void registrar(Proveedor p) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, user, password);
        String sql = "INSERT INTO proveedores (nombre, contacto, correo, direccion) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, p.getNombre());
        ps.setString(2, p.getContacto());
        ps.setString(3, p.getCorreo());
        ps.setString(4, p.getDireccion());
        ps.executeUpdate();
        ps.close();
        con.close();
    }
}
