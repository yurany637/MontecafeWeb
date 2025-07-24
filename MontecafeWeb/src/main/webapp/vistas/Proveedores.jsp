
<%@ page import="modelo.Proveedor" %>
<%@ page import="dao.ProveedorDAO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Proveedores - Montecafe</title>
  <link rel="stylesheet" href="../css/styles.css">
</head>
<body>
<div class="layout">
  <div class="sidebar">
    <h2>Montecafe</h2>
    <a href="dashboard.jsp">Inicio</a>
    <a href="proveedores.jsp" class="active">Proveedores</a>
    <a href="materia_prima.jsp">Materia Prima</a>
    <a href="inventario.jsp">Inventario</a>
    <a href="ventas.jsp">Ventas</a>
    <a href="clientes.jsp">Clientes</a>
    <a href="reportes.jsp">Reportes</a>
    <a href="configuracion.jsp">Configuración</a>
    <a href="index.jsp" class="logout-btn">Cerrar sesión</a>
  </div>

  <div class="main-content">
    <div class="header-top">
      <h1>Módulo de Proveedores</h1>
      <div class="admin-controls">
        <strong>Admin</strong>
        <a href="index.jsp" class="logout-btn">Cerrar sesión</a>
      </div>
    </div>

    <% String mensaje = request.getParameter("mensaje"); %>
    <% if ("ok".equals(mensaje)) { %>
      <p style="color:green;">Proveedor registrado exitosamente.</p>
    <% } else if ("error".equals(mensaje)) { %>
      <p style="color:red;">Error al registrar el proveedor.</p>
    <% } %>

    <div class="form">
      <h2>Registrar Proveedor</h2>
      <form method="post" action="../ProveedorServlet">
        <div class="form-group">
          <label>Nombre</label>
          <input type="text" name="nombre" required>
        </div>
        <div class="form-group">
          <label>Contacto</label>
          <input type="text" name="telefono" required>
        </div>
        <div class="form-group">
          <label>Correo</label>
          <input type="email" name="correo" required>
        </div>
        <div class="form-group">
          <label>Dirección</label>
          <input type="text" name="ciudad" required>
        </div>
        <button type="submit">Registrar</button>
      </form>
    </div>

    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Nombre</th>
          <th>Contacto</th>
          <th>Correo</th>
          <th>Dirección</th>
        </tr>
      </thead>
      <tbody>
        <%
          try {
            ProveedorDAO dao = new ProveedorDAO();
            List<Proveedor> lista = dao.listar();
            for (Proveedor p : lista) {
        %>
        <tr>
          <td><%= p.getId() %></td>
          <td><%= p.getNombre() %></td>
          <td><%= p.getContacto() %></td>
          <td><%= p.getCorreo() %></td>
          <td><%= p.getDireccion() %></td>
        </tr>
        <% } } catch (Exception e) { %>
        <tr><td colspan="5">Error al listar proveedores</td></tr>
        <% } %>
      </tbody>
    </table>
  </div>
</div>
</body>
</html>