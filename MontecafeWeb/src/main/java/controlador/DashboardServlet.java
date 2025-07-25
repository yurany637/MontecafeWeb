
package controlador;

import dao.DashboardDAO;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    private DashboardDAO dao = new DashboardDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("totalProveedores", dao.contarProveedores());
        request.setAttribute("totalProductos", dao.contarProductos());
        request.setAttribute("totalVentas", dao.contarVentas());
        request.setAttribute("totalPedidos", dao.contarPedidos());

        request.getRequestDispatcher("vistas/dashboard.jsp").forward(request, response);
    }
}