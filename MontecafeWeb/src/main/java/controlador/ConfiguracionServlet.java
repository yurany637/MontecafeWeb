package controlador;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dao.ConfiguracionDAO;
import modelo.Configuracion;

@WebServlet("/UsuarioServlet")
public class ConfiguracionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombreUsuario = request.getParameter("nombreUsuario");
        String correo = request.getParameter("correo");
        String password = request.getParameter("password");
        String rol = request.getParameter("rol");

        Configuracion u = new Configuracion(0, nombreUsuario, correo, password, rol);

        try {
            ConfiguracionDAO dao = new ConfiguracionDAO();
            dao.registrar(u);
            request.setAttribute("mensaje", "Usuario registrado correctamente");
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error: " + e.getMessage());
        }
        request.getRequestDispatcher("vistas/respuesta.jsp").forward(request, response);
    }
}
