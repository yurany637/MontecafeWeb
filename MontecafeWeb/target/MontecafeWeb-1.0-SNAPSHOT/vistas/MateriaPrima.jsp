
<%@ page import="modelo.MateriaPrima" %>
<%@ page import="dao.MateriaPrimaDAO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Materia Prima - Montecafe</title>
    <link rel="stylesheet" href="../css/styles.css">
</head>
<body>
<div class="layout">
    <div class="sidebar">
        <h2>Montecafe</h2>
        <a href="dashboard.jsp">Inicio</a>
        <a href="proveedores.jsp">Proveedores</a>
        <a href="materia_prima.jsp" class="active">Materia Prima</a>
        <a href="inventario.jsp">Inventario</a>
        <a href="ventas.jsp">Ventas</a>
        <a href="clientes.jsp">Clientes</a>
        <a href="reportes.jsp">Reportes</a>
        <a href="configuracion.jsp">Configuración</a>
        <a href="index.jsp" class="logout-btn">Cerrar sesión</a>
    </div>

    <div class="main-content">
        <div class="header-top">
            <h1>Módulo de Materia Prima</h1>
            <div class="admin-controls">
                <strong>Admin</strong>
            </div>
        </div>

        <%
            String mensaje = request.getParameter("mensaje");
            if ("ok".equals(mensaje)) {
        %>
        <p style="color:green;">Insumo registrado exitosamente.</p>
        <% } else if ("error".equals(mensaje)) { %>
        <p style="color:red;">Error al registrar el insumo.</p>
        <% } %>

        <div class="form">
            <h2>Registrar Insumo</h2>
            <form method="post" action="../MateriaPrimaServlet">
                <div class="form-group">
                    <label>Nombre del insumo</label>
                    <input type="text" name="nombre" required>
                </div>
                <div class="form-group">
                    <label>Unidad</label>
                    <input type="text" name="unidad" required>
                </div>
                <div class="form-group">
                    <label>Stock inicial</label>
                    <input type="number" name="stock" required>
                </div>
                <button type="submit">Registrar Insumo</button>
            </form>
        </div>

        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Insumo</th>
                <th>Unidad</th>
                <th>Stock</th>
            </tr>
            </thead>
            <tbody>
            <%
                try {
                    MateriaPrimaDAO dao = new MateriaPrimaDAO();
                    List<MateriaPrima> lista = dao.listar();
                    for (MateriaPrima m : lista) {
            %>
                <tr>
                    <td><%= m.getId() %></td>
                    <td><%= m.getNombre() %></td>
                    <td><%= m.getUnidad() %></td>
                    <td><%= m.getStock() %></td>
                </tr>
            <%
                    }
                } catch (Exception e) {
                    out.print("<tr><td colspan='4'>Error al listar insumos</td></tr>");
                }
            %>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
