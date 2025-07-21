
package controlador;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dao.VentasDAO;
import modelo.Ventas;

@WebServlet("/VentaServlet")
public class VentasServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombreCliente = request.getParameter("nombreCliente");
        String producto = request.getParameter("producto");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        double precioUnitario = Double.parseDouble(request.getParameter("precioUnitario"));
        double total = Double.parseDouble(request.getParameter("total"));
        String fecha = request.getParameter("fecha");

        Ventas v = new Ventas(0, nombreCliente, producto, cantidad, precioUnitario, total, fecha);

        try {
            VentasDAO dao = new VentasDAO();
            dao.registrar(v);
            request.setAttribute("mensaje", "Venta registrada correctamente");
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error: " + e.getMessage());
        }

        request.getRequestDispatcher("vistas/respuesta.jsp").forward(request, response);
    }
}