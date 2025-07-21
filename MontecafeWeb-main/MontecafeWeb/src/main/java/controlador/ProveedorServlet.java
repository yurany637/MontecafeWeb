
package controlador;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dao.ProveedorDAO;
import modelo.Proveedor;

@WebServlet("/ProveedorServlet")
public class ProveedorServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String contacto = request.getParameter("contacto");
        String correo = request.getParameter("correo");
        String direccion = request.getParameter("direccion");

        Proveedor p = new Proveedor(0, nombre, contacto, correo, direccion);

        try {
            ProveedorDAO dao = new ProveedorDAO();
            dao.registrar(p);
            request.setAttribute("mensaje", "Proveedor registrado correctamente");
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error: " + e.getMessage());
        }

        request.getRequestDispatcher("vistas/respuesta.jsp").forward(request, response);
    }
}