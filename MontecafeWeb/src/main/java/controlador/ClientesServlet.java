
package controlador;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dao.ClientesDAO;
import modelo.Clientes;

@WebServlet("/ClienteServlet")
public class ClientesServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");
        String direccion = request.getParameter("direccion");

        Clientes c = new Clientes(0, nombre, telefono, correo, direccion);

        try {
            ClientesDAO dao = new ClientesDAO();
            dao.registrar(c);
            request.setAttribute("mensaje", "Cliente registrado correctamente");
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error: " + e.getMessage());
        }

        request.getRequestDispatcher("vistas/respuesta.jsp").forward(request, response);
    }
}