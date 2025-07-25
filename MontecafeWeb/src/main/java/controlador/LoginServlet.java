
package controlador;

import dao.UsuarioDAO;
import modelo.Usuario;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena");

        if (usuario == null || usuario.trim().isEmpty() ||
            contrasena == null || contrasena.trim().isEmpty()) {
            response.sendRedirect("vistas/index.jsp?error=campos");
            return;
        }

        UsuarioDAO dao = new UsuarioDAO();
        Usuario user = dao.validar(usuario, contrasena);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogueado", user);
            response.sendRedirect("vistas/dashboard.jsp");
        } else {
            response.sendRedirect("vistas/index.jsp?error=login");
        }
    }
}