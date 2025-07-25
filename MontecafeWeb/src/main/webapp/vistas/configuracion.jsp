
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Configuración - Montecafe</title>
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

    <!-- Contenido principal -->
    <div class="main-content">
        <div class="header-top">
            <h1>Configuración del Sistema</h1>
            <div class="admin-controls">
                <strong>Admin</strong>
                <a href="index.jsp" class="logout-btn">Cerrar sesión</a>
            </div>
        </div>

        <!-- Mensajes -->
        <c:choose>
            <c:when test="${param.msg == 'ok'}">
                <p style="color:green;">Operación realizada correctamente.</p>
            </c:when>
            <c:when test="${param.msg == 'error'}">
                <p style="color:red;">Ocurrió un error al procesar la solicitud.</p>
            </c:when>
            <c:when test="${param.msg == 'deleted'}">
                <p style="color:green;">Registro eliminado correctamente.</p>
            </c:when>
            <c:when test="${param.msg == 'notfound'}">
                <p style="color:orange;">Registro no encontrado.</p>
            </c:when>
        </c:choose>

        <div class="settings-box">
            <div class="header-box">
                <h2>Lista de configuraciones</h2>
                <a class="btn" href="${pageContext.request.contextPath}/ConfiguracionServlet?action=nuevo">Nuevo</a>
            </div>

            <table class="tabla">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre Usuario</th>
                    <th>Correo</th>
                    <th>Acciones</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="c" items="${lista}">
                    <tr>
                        <td>${c.id}</td>
                        <td>${c.nombreUsuario}</td>
                        <td>${c.correo}</td>
                        <td>
                            <a class="btn btn-sm" href="${pageContext.request.contextPath}/ConfiguracionServlet?action=ver&id=${c.id}">Ver</a>
                            <a class="btn btn-sm" href="${pageContext.request.contextPath}/ConfiguracionServlet?action=editar&id=${c.id}">Editar</a>
                            <a class="btn btn-sm btn-danger" href="${pageContext.request.contextPath}/ConfiguracionServlet?action=eliminar&id=${c.id}" 
                               onclick="return confirm('¿Seguro que deseas eliminar este registro?');">
                                Eliminar
                            </a>
                        </td>
                    </tr>
                </c:forEach>

                <c:if test="${empty lista}">
                    <tr>
                        <td colspan="4" style="text-align:center;">No hay configuraciones registradas.</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>