
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Cliente" %>
<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("../index.jsp?error=login");
        return;
    }
    List<Cliente> listaClientes = (List<Cliente>) request.getAttribute("listaClientes");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Clientes - Montecafe</title>
    <link rel="stylesheet" href="../css/styles.css">
</head>
<body class="pagina-clientes">
<div class="layout">
    <jsp:include page="partials/sidebar.jspf" />
    <div class="main-content">
        <div class="header-top">
            <h1>Gestión de Clientes</h1>
            <div class="admin-controls">
                <strong><%= usuario %></strong>
                <a href="../LogoutServlet" class="logout-btn">Cerrar sesión</a>
            </div>
        </div>
        <div class="form-container">
            <h2>Registrar Cliente</h2>
            <form method="post" action="../ClienteServlet">
                <input type="hidden" name="accion" value="guardar">
                <div class="form-group"><label>Nombre</label><input type="text" name="nombre" required></div>
                <div class="form-group"><label>Teléfono</label><input type="text" name="telefono" required></div>
                <div class="form-group"><label>Correo</label><input type="email" name="correo" required></div>
                <div class="form-group"><label>Dirección</label><input type="text" name="direccion" required></div>
                <button type="submit">Guardar</button>
            </form>
        </div>
        <div class="table-container">
            <h2>Lista de Clientes</h2>
            <table>
                <thead>
                    <tr><th>ID</th><th>Nombre</th><th>Teléfono</th><th>Correo</th><th>Dirección</th><th>Acciones</th></tr>
                </thead>
                <tbody>
                <%
                    if (listaClientes != null && !listaClientes.isEmpty()) {
                        for (Cliente c : listaClientes) {
                %>
                    <tr>
                        <td><%= c.getId() %></td>
                        <td><%= c.getNombre() %></td>
                        <td><%= c.getTelefono() %></td>
                        <td><%= c.getCorreo() %></td>
                        <td><%= c.getDireccion() %></td>
                        <td>
                            <form method="get" action="../ClienteServlet" style="display:inline;">
                                <input type="hidden" name="accion" value="eliminar">
                                <input type="hidden" name="id" value="<%= c.getId() %>">
                                <button type="submit" onclick="return confirm('¿Eliminar este cliente?')">Eliminar</button>
                            </form>
                        </td>
                    </tr>
                <%
                        }
                    } else {
                %>
                    <tr><td colspan="6">No hay clientes registrados.</td></tr>
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