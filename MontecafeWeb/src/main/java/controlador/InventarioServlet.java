
package controlador;

import dao.InventarioDAO;
import modelo.Inventario;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/InventarioServlet")
public class InventarioServlet extends HttpServlet {
    private InventarioDAO dao = new InventarioDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "listar";

        switch (action) {
            case "editar":
                int idEditar = Integer.parseInt(request.getParameter("id"));
                Inventario inventarioEditar = dao.obtenerPorId(idEditar);
                request.setAttribute("inventario", inventarioEditar);
                request.getRequestDispatcher("vistas/inventario-form.jsp").forward(request, response);
                break;

            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                dao.eliminar(idEliminar);
                response.sendRedirect("InventarioServlet");
                break;

            default: // listar
                List<Inventario> lista = dao.listar();
                request.setAttribute("listaInventario", lista);
                request.getRequestDispatcher("vistas/inventario.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String producto = request.getParameter("producto");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        Inventario inv = new Inventario();
        if (id != null && !id.isEmpty()) {
            inv.setId(Integer.parseInt(id));
        }
        inv.setProducto(producto);
        inv.setCantidad(cantidad);

        dao.guardar(inv);
        response.sendRedirect("InventarioServlet");
    }
}