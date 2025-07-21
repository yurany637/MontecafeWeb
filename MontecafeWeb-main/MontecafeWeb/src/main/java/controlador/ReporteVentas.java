
package controlador;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ReporteVentas")
public class ReporteVentas extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/montecafeweb", "root", "");

            String sql = "SELECT * FROM ventas WHERE fecha BETWEEN ? AND ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, fechaInicial);
            ps.setString(2, fechaFinal);

            ResultSet rs = ps.executeQuery();

            request.setAttribute("ventas", rs);
            request.getRequestDispatcher("vistas/reporteVentas.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("mensaje", "Error: " + e.getMessage());
            request.getRequestDispatcher("vistas/respuesta.jsp").forward(request, response);
        }
    }
}