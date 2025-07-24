
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, modelo.Ventas, dao.VentasDAO" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Ventas - Montecafe</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body class="pagina-ventas">
<div class="layout">

  <!-- Menú lateral -->
  <div class="sidebar">
    <h2>Montecafe</h2>
    <a href="dashboard.jsp">Inicio</a>
    <a href="proveedores.jsp">Proveedores</a>
    <a href="materia_prima.jsp">Materia Prima</a>
    <a href="inventario.jsp">Inventario</a>
    <a href="ventas.jsp" class="active">Ventas</a>
    <a href="clientes.jsp">Clientes</a>
    <a href="reportes.jsp">Reportes</a>
    <a href="configuracion.jsp">Configuración</a>
    <a href="index.jsp" class="logout-btn">Cerrar sesión</a>
  </div>

  <!-- Contenido principal -->
  <div class="main-content">
    <div class="header-top">
      <h1>Módulo de Ventas</h1>
      <div class="admin-controls">
        <strong>Admin</strong>
        <a href="index.jsp" class="logout-btn">Cerrar sesión</a>
      </div>
    </div>

    <!-- Mostrar mensaje -->
    <%
      String mensaje = request.getParameter("mensaje");
      if ("ok".equals(mensaje)) {
    %>
        <p style="color: green;">Venta registrada correctamente.</p>
    <%
      } else if ("error".equals(mensaje)) {
    %>
        <p style="color: red;">Error al registrar la venta.</p>
    <%
      }
    %>

    <!-- Formulario de ventas -->
    <div class="sales-form">
      <form method="post" action="../VentasServlet">
        <div class="form-group">
          <label>Nombre Cliente</label>
          <input type="text" name="cliente" id="cliente" placeholder="Nombre del Cliente" required>
        </div>

        <div class="form-group">
          <label>Producto</label>
          <select name="producto" required>
            <option value="">Seleccione un producto</option>
            <option value="Café tostado">Café tostado</option>
            <option value="Materia prima A">Materia prima A</option>
            <option value="Envases">Envases</option>
          </select>
        </div>

        <div class="form-group">
          <label>Cantidad</label>
          <input type="number" name="cantidad" id="cantidad" placeholder="Cantidad" required>
        </div>

        <div class="form-group">
          <label>Precio Unitario</label>
          <input type="number" step="0.01" name="precioUnitario" id="precioUnitario" placeholder="Precio Unitario" required>
        </div>

        <div class="form-group">
          <label>Valor Total</label>
          <input type="number" step="0.01" name="valorTotal" id="valorTotal" placeholder="Total Venta" readonly required>
        </div>

        <div class="form-group">
          <label>Fecha</label>
          <input type="date" name="fecha" required>
        </div>

        <button type="submit" class="confirmar">Confirmar Venta</button>
        <button type="reset" class="cancelar">Cancelar</button>
      </form>
    </div>

    <!-- Tabla de ventas registradas -->
    <h2>Ventas Registradas</h2>
    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Cliente</th>
          <th>Producto</th>
          <th>Cantidad</th>
          <th>Precio Unitario</th>
          <th>Total</th>
          <th>Fecha</th>
        </tr>
      </thead>
      <tbody>
        <%
          VentasDAO dao = new VentasDAO();
          List<Ventas> lista = dao.listar();
          for (Ventas v : lista) {
        %>
          <tr>
            <td><%= v.getId() %></td>
            <td><%= v.getCliente() %></td>
            <td><%= v.getProducto() %></td>
            <td><%= v.getCantidad() %></td>
            <td>$<%= String.format("%,.2f", v.getPrecioUnitario()) %></td>
            <td>$<%= String.format("%,.2f", v.getTotal()) %></td>
            <td><%= v.getFecha() %></td>
          </tr>
        <%
          }
        %>
      </tbody>
    </table>
  </div>
</div>

<!-- Script para calcular automáticamente el total -->
<script>
  function calcularTotal() {
    const cantidad = parseFloat(document.getElementById("cantidad").value) || 0;
    const precio = parseFloat(document.getElementById("precioUnitario").value) || 0;
    const total = cantidad * precio;
    document.getElementById("valorTotal").value = total.toFixed(2);
  }

  document.getElementById("cantidad").addEventListener("input", calcularTotal);
  document.getElementById("precioUnitario").addEventListener("input", calcularTotal);
</script>

</body>
</html>