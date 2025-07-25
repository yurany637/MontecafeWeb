
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Proveedor" %>
<%
    // Verificar sesión
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("../index.jsp?error=login");
        return;
    }

    // Obtener lista de proveedores del servlet
    List<Proveedor> listaProveedores = (List<Proveedor>) request.getAttribute("listaProveedores");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Proveedores - Montecafe</title>
    <link rel="stylesheet" href="../css/styles.css">
</head>
<body class="pagina-proveedores">
<div class="layout">
    <!-- Menú lateral -->
    <jsp:include page="partials/sidebar.jspf" />

    <!-- Contenido principal -->
    <div class="main-content">
        <div class="header-top">
            <h1>Gestión de Proveedores</h1>
            <div class="admin-controls">
                <strong><%= usuario %></strong>
                <a href="../LogoutServlet" class="logout-btn">Cerrar sesión</a>
            </div>
        </div>

        <!-- Formulario de Proveedores -->
        <div class="form-container">
            <h2>Registrar Proveedor</h2>
            <form method="post" action="../ProveedorServlet">
                <input type="hidden" name="accion" value="guardar">
                <div class="form-group">
                    <label>Nombre</label>
                    <input type="text" name="nombre" required>
                </div>
                <div class="form-group">
                    <label>Teléfono</label>
                    <input type="text" name="telefono" required>
                </div>
                <div class="form-group">
                    <label>Correo</label>
                    <input type="email" name="correo" required>
                </div>
                <div class="form-group">
                    <label>Dirección</label>
                    <input type="text" name="direccion" required>
                </div>
                <button type="submit">Guardar</button>
            </form>
        </div>

        <!-- Tabla de Proveedores -->
        <div class="table-container">
            <h2>Lista de Proveedores</h2>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Teléfono</th>
                        <th>Correo</th>
                        <th>Dirección</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                <%
                    if (listaProveedores != null && !listaProveedores.isEmpty()) {
                        for (Proveedor p : listaProveedores) {
                %>
                    <tr>
                        <td><%= p.getId() %></td>
                        <td><%= p.getNombre() %></td>
                        <td><%= p.getTelefono() %></td>
                        <td><%= p.getCorreo() %></td>
                        <td><%= p.getDireccion() %></td>
                        <td>
                            <form method="get" action="../ProveedorServlet" style="display:inline;">
                                <input type="hidden" name="accion" value="eliminar">
                                <input type="hidden" name="id" value="<%= p.getId() %>">
                                <button type="submit" onclick="return confirm('¿Eliminar este proveedor?')">Eliminar</button>
                            </form>
                        </td>
                    </tr>
                <%
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="6">No hay proveedores registrados.</td>
                    </tr>
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