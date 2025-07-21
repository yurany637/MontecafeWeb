
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Reportes - Montecafe</title>
  <link rel="stylesheet" href="../css/styles.css">
</head>
<body class="pagina-reportes">
  <div class="layout">
    
    <!-- Menú lateral -->
    <div class="sidebar">
      <h2>Montecafe</h2>
      <a href="dashboard.jsp">Inicio</a>
      <a href="proveedores.jsp">Proveedores</a>
      <a href="materia_prima.jsp">Materia Prima</a>
      <a href="inventario.jsp">Inventario</a>
      <a href="ventas.jsp">Ventas</a>
      <a href="clientes.jsp">Clientes</a>
      <a href="reportes.jsp" class="active">Reportes</a>
      <a href="configuracion.jsp">Configuración</a>
    </div>

    <!-- Contenido principal -->
    <div class="main-content">
      <div class="header-top">
        <h1>Módulo de Reportes</h1>
        <div class="admin-controls">
          <strong>Admin</strong>
          <a href="../index.html" class="logout-btn">Cerrar sesión</a>
        </div>
      </div>

      <!-- Formularios de reportes -->
      <h3>Reporte de Ventas</h3>
      <form method="get" action="../ReporteVentas">
        <label for="fechaInicialVentas">Desde:</label>
        <input type="date" name="fechaInicial" id="fechaInicialVentas" required>

        <label for="fechaFinalVentas">Hasta:</label>
        <input type="date" name="fechaFinal" id="fechaFinalVentas" required>

        <button type="submit">Generar Reporte de Ventas</button>
      </form>

      <h3>Reporte de Inventario</h3>
      <form method="get" action="../ReporteInventario">
        <button type="submit">Generar Reporte de Inventario</button>
      </form>

    </div>
  </div>
</body>
</html>