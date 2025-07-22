
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

        // Recibir parámetros del formulario
        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena");

        // Validar que no estén vacíos
        if (usuario == null || usuario.trim().isEmpty() ||
            contrasena == null || contrasena.trim().isEmpty()) {
            
            response.sendRedirect("vistas/index.jsp?error=campos");
            return; // Detener ejecución
        }

        // Crear objeto Usuario para enviar al DAO
        Usuario user = new Usuario(usuario, contrasena);
        UsuarioDAO dao = new UsuarioDAO();

        try {
            // Validar usuario en la base de datos
            Usuario usuarioValidado = dao.validar(user);

            if (usuarioValidado != null) {
                // Crear sesión y guardar atributos
                HttpSession sesion = request.getSession();
                sesion.setAttribute("usuario", usuarioValidado.getNombreUsuario());
                sesion.setAttribute("rol", usuarioValidado.getRol());

                // Redirigir según rol
                if ("Admin".equalsIgnoreCase(usuarioValidado.getRol())) {
                    response.sendRedirect("vistas/dashboard.jsp");
                } else if ("Vendedor".equalsIgnoreCase(usuarioValidado.getRol())) {
                    response.sendRedirect("vistas/ventas.jsp");
                } else {
                    response.sendRedirect("vistas/index.jsp?error=rol");
                }
            } else {
                // Usuario o contraseña incorrectos
                response.sendRedirect("vistas/index.jsp?error=login");
            }

        } catch (Exception e) {
            e.printStackTrace(); // Para ver el error en consola
            response.sendRedirect("vistas/index.jsp?error=servidor");
        }
    }
}