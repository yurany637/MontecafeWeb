package controlador;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dao.materiaPrimaDAO;
import modelo.materiaPrima;

@WebServlet("/MateriaPrimaServlet")
public class materiaPrimaServlet extends HttpServlet {

    /**
     * Procesa las solicitudes HTTP POST. Recibe datos del formulario y guarda
     * en la base de datos.
     *
     * @param request La solicitud HTTP del cliente.
     * @param response La respuesta HTTP al cliente.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        int stock = Integer.parseInt(request.getParameter("stock"));

        materiaPrima m = new materiaPrima(0, nombre, cantidad, stock);

        try {
            materiaPrimaDAO dao = new materiaPrimaDAO();
            dao.registrar(m);
            request.setAttribute("mensaje", "Materia prima registrada correctamente");
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error: " + e.getMessage());
        }

        request.getRequestDispatcher("vistas/respuesta.jsp").forward(request, response);
    }
}
