
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="modelo.MateriaPrima" %>
<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("../index.jsp?error=login");
        return;
    }
    List<MateriaPrima> listaMaterias = (List<MateriaPrima>) request.getAttribute("listaMaterias");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Materia Prima - Montecafe</title>
    <link rel="stylesheet" href="../css/styles.css">
</head>
<body class="pagina-materia-prima">
<div class="layout">
    <jsp:include page="partials/sidebar.jspf" />
    <div class="main-content">
        <div class="header-top">
            <h1>Gestión de Materia Prima</h1>
            <div class="admin-controls">
                <strong><%= usuario %></strong>
                <a href="../LogoutServlet" class="logout-btn">Cerrar sesión</a>
            </div>
        </div>
        <div class="form-container">
            <h2>Registrar Materia Prima</h2>
            <form method="post" action="../MateriaPrimaServlet">
                <input type="hidden" name="accion" value="guardar">
                <div class="form-group"><label>Nombre</label><input type="text" name="nombre" required></div>
                <div class="form-group"><label>Cantidad</label><input type="number" name="cantidad" required></div>
                <div class="form-group"><label>Unidad</label><input type="text" name="unidad" required></div>
                <div class="form-group"><label>Stock</label><input type="number" name="stock" required></div>
                <button type="submit">Guardar</button>
            </form>
        </div>
        <div class="table-container">
            <h2>Lista de Materias Primas</h2>
            <table>
                <thead>
                    <tr><th>ID</th><th>Nombre</th><th>Cantidad</th><th>Unidad</th><th>Stock</th><th>Acciones</th></tr>
                </thead>
                <tbody>
                <%
                    if (listaMaterias != null && !listaMaterias.isEmpty()) {
                        for (MateriaPrima mp : listaMaterias) {
                %>
                    <tr>
                        <td><%= mp.getId() %></td>
                        <td><%= mp.getNombre() %></td>
                        <td><%= mp.getCantidad() %></td>
                        <td><%= mp.getUnidad() %></td>
                        <td><%= mp.getStock() %></td>
                        <td>
                            <form method="get" action="../MateriaPrimaServlet" style="display:inline;">
                                <input type="hidden" name="accion" value="eliminar">
                                <input type="hidden" name="id" value="<%= mp.getId() %>">
                                <button type="submit" onclick="return confirm('¿Eliminar esta materia prima?')">Eliminar</button>
                            </form>
                        </td>
                    </tr>
                <%
                        }
                    } else {
                %>
                    <tr><td colspan="6">No hay materias primas registradas.</td></tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>