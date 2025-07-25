
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Configuración - Form</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body class="pagina-configuracion">
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
        <a href="${pageContext.request.contextPath}/ConfiguracionServlet?action=listar" class="active">Configuración</a>
        <a href="index.jsp" class="logout-btn">Cerrar sesión</a>
    </div>

    <div class="main-content">
        <div class="header-top">
            <h1>${config.id == 0 ? "Nueva configuración" : "Editar configuración"}</h1>
            <div class="admin-controls">
                <strong>Admin</strong>
                <a href="index.jsp" class="logout-btn">Cerrar sesión</a>
            </div>
        </div>

        <div class="settings-box">
            <form method="post" action="${pageContext.request.contextPath}/ConfiguracionServlet">
                <input type="hidden" name="id" value="${config.id}" />

                <div class="form-group">
                    <label>Nombre del usuario</label>
                    <input type="text" name="nombreUsuario" value="${config.nombreUsuario}" required>
                </div>

                <div class="form-group">
                    <label>Correo electrónico</label>
                    <input type="email" name="correo" value="${config.correo}" required>
                </div>

                <div class="form-group">
                    <label>Contraseña</label>
                    <input type="password" name="password" value="${config.password}">
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn">Guardar</button>
                    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/ConfiguracionServlet?action=listar">Cancelar</a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
