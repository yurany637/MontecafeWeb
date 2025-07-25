
package controlador;

import dao.ConfiguracionDAO;
import modelo.Configuracion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/ConfiguracionServlet")
public class ConfiguracionServlet extends HttpServlet {

    private final ConfiguracionDAO dao = new ConfiguracionDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "listar";

        switch (action) {
            case "nuevo":
                // Va al formulario vacío
                request.setAttribute("config", new Configuracion());
                request.getRequestDispatcher("vistas/configuracion-form.jsp").forward(request, response);
                break;

            case "editar":
                int idEditar = parseIntOrZero(request.getParameter("id"));
                Configuracion cEditar = dao.obtenerPorId(idEditar);
                if (cEditar == null) {
                    response.sendRedirect("ConfiguracionServlet?action=listar&msg=notfound");
                    return;
                }
                request.setAttribute("config", cEditar);
                request.getRequestDispatcher("vistas/configuracion-form.jsp").forward(request, response);
                break;

            case "eliminar":
                int idEliminar = parseIntOrZero(request.getParameter("id"));
                boolean eliminado = dao.eliminar(idEliminar);
                response.sendRedirect("ConfiguracionServlet?action=listar&msg=" + (eliminado ? "deleted" : "error"));
                break;

            case "ver":
                int idVer = parseIntOrZero(request.getParameter("id"));
                Configuracion cVer = dao.obtenerPorId(idVer);
                if (cVer == null) {
                    response.sendRedirect("ConfiguracionServlet?action=listar&msg=notfound");
                    return;
                }
                request.setAttribute("config", cVer);
                request.getRequestDispatcher("vistas/configuracion-detalle.jsp").forward(request, response);
                break;

            case "listar":
            default:
                List<Configuracion> lista = dao.listar();
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("vistas/configuracion.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        int id = parseIntOrZero(idParam);

        String nombreUsuario = request.getParameter("nombreUsuario");
        String correo = request.getParameter("correo");
        String password = request.getParameter("password");

        Configuracion config = new Configuracion();
        config.setId(id); // 0 => INSERT, >0 => UPDATE (según dao.guardar)
        config.setNombreUsuario(nombreUsuario);
        config.setCorreo(correo);
        config.setPassword(password);

        boolean exito = dao.guardar(config);

        if (exito) {
            response.sendRedirect("ConfiguracionServlet?action=listar&msg=ok");
        } else {
            // puedes reenviar al form con los datos cargados si quieres
            response.sendRedirect("ConfiguracionServlet?action=listar&msg=error");
        }
    }

    private int parseIntOrZero(String value) {
        try {
            return (value == null || value.isEmpty()) ? 0 : Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}