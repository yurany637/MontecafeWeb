
package controlador;

import dao.ProveedorDAO;
import modelo.Proveedor;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ProveedorServlet")
public class ProveedorServlet extends HttpServlet {

    private ProveedorDAO dao = new ProveedorDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession(false);
        if (sesion == null || sesion.getAttribute("usuario") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        switch (accion) {
            case "editar":
                int idEditar = Integer.parseInt(request.getParameter("id"));
                Proveedor proveedorEditar = dao.obtenerPorId(idEditar);
                request.setAttribute("proveedor", proveedorEditar);
                request.getRequestDispatcher("vistas/proveedores.jsp").forward(request, response);
                break;

            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                dao.eliminar(idEliminar);
                response.sendRedirect("ProveedorServlet");
                break;

            default:
                List<Proveedor> lista = dao.listar();
                request.setAttribute("listaProveedores", lista);
                request.getRequestDispatcher("vistas/proveedores.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");

        Proveedor p = new Proveedor();
        p.setNombre(nombre);
        p.setTelefono(telefono);
        p.setCorreo(correo);

        if (id == null || id.isEmpty()) {
            dao.insertar(p);
        } else {
            p.setId(Integer.parseInt(id));
            dao.actualizar(p);
        }
        response.sendRedirect("ProveedorServlet");
    }
}