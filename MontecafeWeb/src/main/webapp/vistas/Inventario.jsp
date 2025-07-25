
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Inventario" %>
<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("../index.jsp?error=login");
        return;
    }
    List<Inventario> listaInventario = (List<Inventario>) request.getAttribute("listaInventario");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Inventario - Montecafe</title>
    <link rel="stylesheet" href="../css/styles.css">
</head>
<body class="pagina-inventario">
<div class="layout">
    <jsp:include page="partials/sidebar.jspf" />
    <div class="main-content">
        <div class="header-top">
            <h1>Gestión de Inventario</h1>
            <div class="admin-controls">
                <strong><%= usuario %></strong>
                <a href="../LogoutServlet" class="logout-btn">Cerrar sesión</a>
            </div>
        </div>
        <div class="form-container">
            <h2>Registrar Producto en Inventario</h2>
            <form method="post" action="../InventarioServlet">
                <input type="hidden" name="accion" value="guardar">
                <div class="form-group"><label>Nombre</label><input type="text" name="nombre" required></div>
                <div class="form-group"><label>Cantidad</label><input type="number" name="cantidad" required></div>
                <div class="form-group"><label>Precio</label><input type="number" name="precio" step="0.01" required></div>
                <button type="submit">Guardar</button>
            </form>
        </div>
        <div class="table-container">
            <h2>Lista de Inventario</h2>
            <table>
                <thead>
                    <tr><th>ID</th><th>Nombre</th><th>Cantidad</th><th>Precio</th><th>Acciones</th></tr>
                </thead>
                <tbody>
                <%
                    if (listaInventario != null && !listaInventario.isEmpty()) {
                        for (Inventario i : listaInventario) {
                %>
                    <tr>
                        <td><%= i.getId() %></td>
                        <td><%= i.getNombre() %></td>
                        <td><%= i.getCantidad() %></td>
                        <td><%= i.getPrecio() %></td>
                        <td>
                            <form method="get" action="../InventarioServlet" style="display:inline;">
                                <input type="hidden" name="accion" value="eliminar">
                                <input type="hidden" name="id" value="<%= i.getId() %>">
                                <button type="submit" onclick="return confirm('¿Eliminar este producto?')">Eliminar</button>
                            </form>
                        </td>
                    </tr>
                <%
                        }
                    } else {
                %>
                    <tr><td colspan="5">No hay productos en inventario.</td></tr>
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