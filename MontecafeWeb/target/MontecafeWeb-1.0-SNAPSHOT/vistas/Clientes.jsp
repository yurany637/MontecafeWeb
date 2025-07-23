
<%@ page import="modelo.Clientes" %>
<%@ page import="dao.ClientesDAO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Clientes - Montecafe</title>
    <link rel="stylesheet" href="../css/styles.css">
</head>
<body>
<div class="layout">
    <div class="sidebar">
        <h2>Montecafe</h2>
        <a href="dashboard.jsp">Inicio</a>
        <a href="proveedores.jsp">Proveedores</a>
        <a href="materia_prima.jsp">Materia Prima</a>
        <a href="inventario.jsp">Inventario</a>
        <a href="ventas.jsp">Ventas</a>
        <a href="clientes.jsp" class="active">Clientes</a>
        <a href="reportes.jsp">Reportes</a>
        <a href="configuracion.jsp">Configuración</a>
    </div>

    <div class="main-content">
        <h1>Módulo de Clientes</h1>

        <%
            String mensaje = request.getParameter("mensaje");
            if ("ok".equals(mensaje)) {
        %>
            <p style="color:green;">Cliente registrado exitosamente.</p>
        <% } else if ("error".equals(mensaje)) { %>
            <p style="color:red;">Error al registrar el cliente.</p>
        <% } %>

        <div class="form">
            <h2>Registrar Cliente</h2>
            <form method="post" action="../ClienteServlet">
                <div class="form-group"><label>Nombre:</label><input type="text" name="nombre" required></div>
                <div class="form-group"><label>Teléfono:</label><input type="tel" name="telefono" required></div>
                <div class="form-group"><label>Correo:</label><input type="email" name="correo" required></div>
                <div class="form-group"><label>Dirección:</label><input type="text" name="direccion" required></div>
                <button type="submit">Registrar</button>
            </form>
        </div>

        <table>
            <thead>
            <tr><th>ID</th><th>Nombre</th><th>Teléfono</th><th>Correo</th><th>Dirección</th></tr>
            </thead>
            <tbody>
            <%
                try {
                    ClientesDAO dao = new ClientesDAO();
                    List<Clientes> lista = dao.listar();
                    for (Clientes c : lista) {
            %>
                <tr>
                    <td><%= c.getId() %></td>
                    <td><%= c.getNombre() %></td>
                    <td><%= c.getTelefono() %></td>
                    <td><%= c.getCorreo() %></td>
                    <td><%= c.getDireccion() %></td>
                </tr>
            <%
                    }
                } catch (Exception e) {
                    out.print("<tr><td colspan='5'>Error al listar clientes</td></tr>");
                }
            %>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>