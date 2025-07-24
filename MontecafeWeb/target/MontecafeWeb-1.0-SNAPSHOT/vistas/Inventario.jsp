
<%@ page import="modelo.Inventario" %>
<%@ page import="dao.InventarioDAO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Inventario - Montecafe</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="layout">
    <div class="sidebar">
        <h2>Montecafe</h2>
        <a href="dashboard.jsp">Inicio</a>
        <a href="proveedores.jsp">Proveedores</a>
        <a href="materia_prima.jsp">Materia Prima</a>
        <a href="inventario.jsp" class="active">Inventario</a>
        <a href="ventas.jsp">Ventas</a>
        <a href="clientes.jsp">Clientes</a>
        <a href="reportes.jsp">Reportes</a>
        <a href="configuracion.jsp">Configuración</a>
        <a href="index.jsp" class="logout-btn">Cerrar sesión</a>
    </div>

    <div class="main-content">
        <h1>Módulo de Inventario</h1>

        <%
            String mensaje = request.getParameter("mensaje");
            if ("ok".equals(mensaje)) {
        %>
            <p style="color:green;">Producto registrado exitosamente.</p>
        <% } else if ("error".equals(mensaje)) { %>
            <p style="color:red;">Error al registrar el producto.</p>
        <% } %>

        <div class="form">
            <h2>Agregar Producto</h2>
            <form method="post" action="${pageContext.request.contextPath}/InventarioServlet">
                <div class="form-group"><label>Producto:</label><input type="text" name="producto" required></div>
                <div class="form-group"><label>Categoría:</label><input type="text" name="categoria" required></div>
                <div class="form-group"><label>Stock:</label><input type="number" name="stock" required></div>
                <div class="form-group"><label>Unidad:</label><input type="text" name="unidad" required></div>
                <button type="submit">Registrar</button>
            </form>
        </div>

        <table>
            <thead>
            <tr><th>ID</th><th>Producto</th><th>Categoría</th><th>Stock</th><th>Unidad</th><th>Estado</th></tr>
            </thead>
            <tbody>
            <%
                try {
                    InventarioDAO dao = new InventarioDAO();
                    List<Inventario> lista = dao.listar();
                    for (Inventario i : lista) {
            %>
                <tr>
                    <td><%= i.getId() %></td>
                    <td><%= i.getProducto() %></td>
                    <td><%= i.getCategoria() %></td>
                    <td><%= i.getStock() %></td>
                    <td><%= i.getUnidad() %></td>
                    <td><%= i.getEstado() %></td>
                </tr>
            <%
                    }
                } catch (Exception e) {
                    out.print("<tr><td colspan='6'>Error al listar productos</td></tr>");
                }
            %>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>