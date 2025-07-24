
package controlador;

import dao.InventarioDAO;
import modelo.Inventario;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/InventarioServlet")
public class InventarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        InventarioDAO dao = new InventarioDAO();
        Inventario inv = new Inventario();

        String producto = request.getParameter("producto");
        String categoria = request.getParameter("categoria");
        String stockStr = request.getParameter("stock");
        String unidad = request.getParameter("unidad");

        try {
            if (producto != null && categoria != null && stockStr != null && unidad != null) {
                int stock = Integer.parseInt(stockStr);

                inv.setProducto(producto);
                inv.setCategoria(categoria);
                inv.setStock(stock);
                inv.setUnidad(unidad);
                inv.setEstado(stock <= 20 ? "Stock bajo" : "Activo");

                boolean exito = dao.registrar(inv);

                if (exito) {
                    // Redirige con mensaje de éxito
                    response.sendRedirect(request.getContextPath() + "/vistas/Inventario.jsp?mensaje=ok");
                    return;
                }
            }

            // Si algo falló, redirige con mensaje de error
            response.sendRedirect(request.getContextPath() + "/vistas/Inventario.jsp?mensaje=error");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/vistas/Inventario.jsp?mensaje=error");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Esta parte puede no ser necesaria si no estás usando GET.
        InventarioDAO dao = new InventarioDAO();
        request.setAttribute("lista", dao.listar());
        request.getRequestDispatcher("vistas/Inventario.jsp").forward(request, response);
    }
}
