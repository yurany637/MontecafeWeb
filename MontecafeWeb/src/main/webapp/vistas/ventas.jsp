
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Venta" %>
<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("../index.jsp?error=login");
        return;
    }
    List<Venta> listaVentas = (List<Venta>) request.getAttribute("listaVentas");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Ventas - Montecafe</title>
    <link rel="stylesheet" href="../css/styles.css">
</head>
<body class="pagina-ventas">
<div class="layout">
    <jsp:include page="partials/sidebar.jspf" />
    <div class="main-content">
        <div class="header-top">
            <h1>Gestión de Ventas</h1>
            <div class="admin-controls">
                <strong><%= usuario %></strong>
                <a href="../LogoutServlet" class="logout-btn">Cerrar sesión</a>
            </div>
        </div>
        <div class="form-container">
            <h2>Registrar Venta</h2>
            <form method="post" action="../VentasServlet">
                <input type="hidden" name="accion" value="guardar">
                <div class="form-group"><label>Producto</label><input type="text" name="producto" required></div>
                <div class="form-group"><label>Cantidad</label><input type="number" name="cantidad" required></div>
                <div class="form-group"><label>Precio Unitario</label><input type="number" name="precio" step="0.01" required></div>
                <button type="submit">Registrar Venta</button>
            </form>
        </div>
        <div class="table-container">
            <h2>Lista de Ventas</h2>
            <table>
                <thead>
                    <tr><th>ID</th><th>Producto</th><th>Cantidad</th><th>Precio</th><th>Total</th><th>Acciones</th></tr>
                </thead>
                <tbody>
                <%
                    if (listaVentas != null && !listaVentas.isEmpty()) {
                        for (Venta v : listaVentas) {
                %>
                    <tr>
                        <td><%= v.getId() %></td>
                        <td><%= v.getProducto() %></td>
                        <td><%= v.getCantidad() %></td>
                        <td><%= v.getPrecio() %></td>
                        <td><%= v.getCantidad() * v.getPrecio() %></td>
                        <td>
                            <form method="get" action="../VentasServlet" style="display:inline;">
                                <input type="hidden" name="accion" value="eliminar">
                                <input type="hidden" name="id" value="<%= v.getId() %>">
                                <button type="submit" onclick="return confirm('¿Eliminar esta venta?')">Eliminar</button>
                            </form>
                        </td>
                    </tr>
                <%
                        }
                    } else {
                %>
                    <tr><td colspan="6">No hay ventas registradas.</td></tr>
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