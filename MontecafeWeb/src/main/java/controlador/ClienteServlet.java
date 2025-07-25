
package controlador;

import dao.ClienteDAO;
import modelo.Cliente;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ClienteServlet")
public class ClienteServlet extends HttpServlet {

    private ClienteDAO dao = new ClienteDAO();

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
                Cliente clienteEditar = dao.obtenerPorId(idEditar);
                request.setAttribute("cliente", clienteEditar);
                request.getRequestDispatcher("vistas/clientes.jsp").forward(request, response);
                break;

            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                dao.eliminar(idEliminar);
                response.sendRedirect("ClienteServlet");
                break;

            default:
                List<Cliente> lista = dao.listar();
                request.setAttribute("listaClientes", lista);
                request.getRequestDispatcher("vistas/clientes.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String telefono = request.getParameter("telefono");

        Cliente c = new Cliente();
        c.setNombre(nombre);
        c.setCorreo(correo);
        c.setTelefono(telefono);

        if (id == null || id.isEmpty()) {
            dao.insertar(c);
        } else {
            c.setId(Integer.parseInt(id));
            dao.actualizar(c);
        }
        response.sendRedirect("ClienteServlet");
    }
}