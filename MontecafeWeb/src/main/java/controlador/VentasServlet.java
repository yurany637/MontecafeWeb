
package controlador;

import dao.VentaDAO;
import modelo.Venta;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/VentasServlet")
public class VentasServlet extends HttpServlet {
    private VentaDAO dao = new VentaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "listar";

        switch (action) {
            case "editar":
                int idEditar = Integer.parseInt(request.getParameter("id"));
                Venta ventaEditar = dao.obtenerPorId(idEditar);
                request.setAttribute("venta", ventaEditar);
                request.getRequestDispatcher("vistas/ventas-form.jsp").forward(request, response);
                break;

            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                dao.eliminar(idEliminar);
                response.sendRedirect("VentasServlet");
                break;

            default: // listar
                List<Venta> lista = dao.listar();
                request.setAttribute("listaVentas", lista);
                request.getRequestDispatcher("vistas/ventas.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String cliente = request.getParameter("cliente");
        String producto = request.getParameter("producto");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        double total = Double.parseDouble(request.getParameter("total"));

        Venta venta = new Venta();
        if (id != null && !id.isEmpty()) {
            venta.setId(Integer.parseInt(id));
        }
        venta.setCliente(cliente);
        venta.setProducto(producto);
        venta.setCantidad(cantidad);
        venta.setTotal(total);

        dao.guardar(venta);
        response.sendRedirect("VentasServlet");
    }
}