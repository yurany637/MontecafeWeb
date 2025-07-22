
package controlador;

import dao.ProveedorDAO;
import modelo.Proveedor;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/ProveedorServlet")
public class ProveedorServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String contacto = request.getParameter("contacto");
        String correo = request.getParameter("correo");
        String direccion = request.getParameter("direccion");

        Proveedor p = new Proveedor(nombre, contacto, correo, direccion);
        ProveedorDAO dao = new ProveedorDAO();

        try {
            dao.registrar(p);
            response.sendRedirect("vistas/proveedores.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error al registrar proveedor: " + e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProveedorDAO dao = new ProveedorDAO();
        try {
            List<Proveedor> lista = dao.listar();
            request.setAttribute("proveedores", lista);
            RequestDispatcher rd = request.getRequestDispatcher("vistas/proveedores.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error al listar proveedores: " + e.getMessage());
        }
    }
}