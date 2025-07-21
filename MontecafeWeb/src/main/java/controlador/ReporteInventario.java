
package controlador;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ReporteInventario")
public class ReporteInventario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/montecafeweb", "root", "");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM inventario");

            request.setAttribute("inventario", rs);
            request.getRequestDispatcher("vistas/reporteInventario.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("mensaje", "Error: " + e.getMessage());
            request.getRequestDispatcher("vistas/respuesta.jsp").forward(request, response);
        }
    }
}