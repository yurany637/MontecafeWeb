
<%@page import="java.sql.ResultSet"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Reporte de Ventas</title>
</head>
<body>
    <h2>Reporte de Ventas</h2>

    <table border="1">
        <tr>
            <th>Cliente</th>
            <th>Producto</th>
            <th>Cantidad</th>
            <th>Precio Unitario</th>
            <th>Total</th>
            <th>Fecha</th>
        </tr>

        <%
            ResultSet rs = (ResultSet)request.getAttribute("ventas");
            while(rs.next()){
        %>
        <tr>
            <td><%= rs.getString("nombre_cliente") %></td>
            <td><%= rs.getString("producto") %></td>
            <td><%= rs.getInt("cantidad") %></td>
            <td><%= rs.getDouble("precio_unitario") %></td>
            <td><%= rs.getDouble("total") %></td>
            <td><%= rs.getDate("fecha") %></td>
        </tr>
        <% } 
        rs.close();
        %>
    </table>

    <br>
    <a href="reportes.jsp">Volver al módulo de reportes</a>

</body>
</html>
