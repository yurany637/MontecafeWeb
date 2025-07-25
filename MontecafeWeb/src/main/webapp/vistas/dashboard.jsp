
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    if (session.getAttribute("usuarioLogueado") == null) {
        response.sendRedirect("index.jsp?auth=required");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Dashboard - Montecafe</title>
  <link rel="stylesheet" href="../css/styles.css">
</head>
<body>
  <div class="layout">
    <jsp:include page="partials/sidebar.jspf" />

    <div class="main-content">
      <div class="header-top">
        <h1>Dashboard Principal</h1>
        <div class="admin-controls">
          <strong><%= ((modelo.Usuario)session.getAttribute("usuarioLogueado")).getNombreUsuario() %></strong>
          <a href="../LogoutServlet" class="logout-btn">Cerrar sesión</a>
        </div>
      </div>

      <div class="cards">
        <div class="card"><strong>40</strong><br>Total proveedores</div>
        <div class="card"><strong>350</strong><br>Total productos stock</div>
        <div class="card"><strong>120</strong><br>Ventas del mes</div>
        <div class="card"><strong>8</strong><br>Pedidos activos</div>
      </div>

      <div class="alerts-calendar">
        <div class="alert-box">
          <h3>Alertas Activas</h3>
          <ul>
            <li>⚠ Productos con stock mínimo</li>
            <li>⚠ Lotes próximos a caducar</li>
            <li>⚠ Pedidos en espera</li>
          </ul>
        </div>
        <div class="calendar-box">
          <h3>Calendario de Producción/Envíos</h3>
          <p><strong>Julio 2025</strong></p>
          <!-- Mantén tu calendario original -->
        </div>
      </div>
    </div>
  </div>
</body>
</html>
