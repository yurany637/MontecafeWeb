
package controlador;

import dao.MateriaPrimaDAO;
import modelo.MateriaPrima;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/MateriaPrimaServlet")
public class MateriaPrimaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MateriaPrima m = new MateriaPrima();
        m.setNombre(request.getParameter("nombre"));
        m.setUnidad(request.getParameter("unidad"));
        m.setStock(Integer.parseInt(request.getParameter("stock")));

        MateriaPrimaDAO dao = new MateriaPrimaDAO();
        boolean exito = dao.registrar(m);

        if (exito) {
            response.sendRedirect("vistas/MateriaPrima.jsp?mensaje=ok");
        } else {
            response.sendRedirect("vistas/MateriaPrima.jsp?mensaje=error");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MateriaPrimaDAO dao = new MateriaPrimaDAO();
        request.setAttribute("lista", dao.listar());
        request.getRequestDispatcher("vistas/MateriaPrima.jsp").forward(request, response);
    }
}