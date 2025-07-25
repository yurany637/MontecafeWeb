
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Configuración - Detalle</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body class="pagina-configuracion">
<div class="layout">
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
            <h1>Detalle configuración</h1>
            <div class="admin-controls">
                <strong>Admin</strong>
                <a href="index.jsp" class="logout-btn">Cerrar sesión</a>
            </div>
        </div>

        <div class="settings-box">
            <p><strong>ID:</strong> ${config.id}</p>
            <p><strong>Nombre usuario:</strong> ${config.nombreUsuario}</p>
            <p><strong>Correo:</strong> ${config.correo}</p>
            <p><strong>Contraseña:</strong> ${config.password}</p>

            <div class="form-actions">
                <a class="btn" href="${pageContext.request.contextPath}/ConfiguracionServlet?action=editar&id=${config.id}">Editar</a>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/ConfiguracionServlet?action=listar">Volver</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>