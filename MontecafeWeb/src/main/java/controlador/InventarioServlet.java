
package controlador;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dao.InventarioDAO;
import modelo.Inventario;

@WebServlet("/InventarioServlet")
public class InventarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String codigoProducto = request.getParameter("codigoProducto");
        String producto = request.getParameter("producto");
        String categoria = request.getParameter("categoria");
        int stock = Integer.parseInt(request.getParameter("stock"));
        String unidad = request.getParameter("unidad");
        String estado = request.getParameter("estado");

        Inventario i = new Inventario(0, codigoProducto, producto, categoria, stock, unidad, estado);

        try {
            InventarioDAO dao = new InventarioDAO();
            dao.registrar(i);
            request.setAttribute("mensaje", "Inventario registrado correctamente");
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error: " + e.getMessage());
        }

        request.getRequestDispatcher("vistas/respuesta.jsp").forward(request, response);
    }
}