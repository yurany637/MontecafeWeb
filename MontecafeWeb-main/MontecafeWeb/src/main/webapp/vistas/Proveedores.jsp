
<%@ page import="modelo.Proveedor" %>
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
  </div>

  <div class="main-content">
    <div class="header-top">
      <h1>Módulo de Proveedores</h1>
      <div class="admin-controls">
        <strong>Admin</strong>
        <a href="../index.jsp" class="logout-btn">Cerrar sesión</a>
      </div>
    </div>

    <form action="../ProveedorServlet" method="post">
      <h2>Registrar Proveedor</h2>
      <input type="text" name="nombre" placeholder="Nombre" required>
      <input type="text" name="contacto" placeholder="Teléfono" required>
      <input type="email" name="correo" placeholder="Correo" required>
      <input type="text" name="direccion" placeholder="Ciudad" required>
      <button type="submit">Registrar</button>
    </form>

    <h2>Lista de Proveedores</h2>
    <table>
      <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Teléfono</th>
        <th>Correo</th>
        <th>Dirección</th>
      </tr>
      <%
          List<Proveedor> lista = (List<Proveedor>) request.getAttribute("proveedores");
          if (lista != null) {
              for (Proveedor p : lista) {
      %>
      <tr>
        <td><%= p.getId() %></td>
        <td><%= p.getNombre() %></td>
        <td><%= p.getContacto() %></td>
        <td><%= p.getCorreo() %></td>
        <td><%= p.getDireccion() %></td>
      </tr>
      <%
              }
          }
      %>
    </table>
  </div>
</div>
</body>
</html>