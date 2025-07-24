package controlador;

import dao.VentasDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Ventas;


@WebServlet("/VentasServlet")
public class VentasServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Ventas v = new Ventas();
        VentasDAO dao = new VentasDAO();

        try {
            String cliente = request.getParameter("cliente");
            String producto = request.getParameter("producto");
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            double precioUnitario = Double.parseDouble(request.getParameter("precioUnitario"));
            String fecha = request.getParameter("fecha");

            double total = cantidad * precioUnitario;

            v.setCliente(cliente);
            v.setProducto(producto);
            v.setCantidad(cantidad);
            v.setPrecioUnitario(precioUnitario);
            v.setTotal(total);
            v.setFecha(fecha);

            dao.registrar(v);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("lista", dao.listar());
        request.getRequestDispatcher("vistas/ventas.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VentasDAO dao = new VentasDAO();
        request.setAttribute("lista", dao.listar());
        request.getRequestDispatcher("vistas/ventas.jsp").forward(request, response);
    }
}