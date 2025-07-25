
package controlador;

import dao.MateriaPrimaDAO;
import modelo.MateriaPrima;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/MateriaPrimaServlet")
public class MateriaPrimaServlet extends HttpServlet {
    private MateriaPrimaDAO dao = new MateriaPrimaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "listar";

        switch (action) {
            case "editar":
                int idEditar = Integer.parseInt(request.getParameter("id"));
                MateriaPrima materiaEditar = dao.obtenerPorId(idEditar);
                request.setAttribute("materia", materiaEditar);
                request.getRequestDispatcher("vistas/materia-prima-form.jsp").forward(request, response);
                break;

            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                dao.eliminar(idEliminar);
                response.sendRedirect("MateriaPrimaServlet");
                break;

            default: // listar
                List<MateriaPrima> lista = dao.listar();
                request.setAttribute("listaMateria", lista);
                request.getRequestDispatcher("vistas/materia-prima.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        String unidad = request.getParameter("unidad");

        MateriaPrima materia = new MateriaPrima();
        if (id != null && !id.isEmpty()) {
            materia.setId(Integer.parseInt(id));
        }
        materia.setNombre(nombre);
        materia.setCantidad(cantidad);
        materia.setUnidad(unidad);

        dao.guardar(materia);
        response.sendRedirect("MateriaPrimaServlet");
    }
}
