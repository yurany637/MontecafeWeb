
package controlador;

import dao.ProveedorDAO;
import modelo.Proveedor;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/ProveedorServlet")
public class ProveedorServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProveedorDAO dao = new ProveedorDAO();
        Proveedor p = new Proveedor();

        try {
            p.setNombre(request.getParameter("nombre"));
            p.setContacto(request.getParameter("telefono"));
            p.setCorreo(request.getParameter("correo"));
            p.setDireccion(request.getParameter("ciudad"));

            boolean exito = dao.registrar(p);
            if (exito) {
                response.sendRedirect("vistas/Proveedores.jsp?mensaje=ok");
            } else {
                response.sendRedirect("vistas/Proveedores.jsp?mensaje=error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("vistas/Proveedores.jsp?mensaje=error");
        }
    }
}