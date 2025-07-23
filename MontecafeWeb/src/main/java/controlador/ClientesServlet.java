
package controlador;

import dao.ClientesDAO;
import modelo.Clientes;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ClienteServlet") // ✔ IMPORTANTE: este nombre exacto
public class ClientesServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");
        String direccion = request.getParameter("direccion");

        Clientes cliente = new Clientes(nombre, telefono, correo, direccion);
        ClientesDAO dao = new ClientesDAO();

        try {
            dao.registrar(cliente);
            response.sendRedirect("vistas/Clientes.jsp?mensaje=ok");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("vistas/Clientes.jsp?mensaje=error");
        }
    }
}