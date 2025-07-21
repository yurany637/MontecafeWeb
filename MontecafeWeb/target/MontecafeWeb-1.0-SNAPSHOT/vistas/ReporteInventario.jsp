
<%@page import="java.sql.ResultSet"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <title>Reporte de Inventario</title>
    </head>
    <body>
        <h2>Inventario Actual</h2>

        <table border="1">
            <tr>
                <th>Código Producto</th>
                <th>Producto</th>
                <th>Categoría</th>
                <th>Stock</th>
                <th>Unidad</th>
                <th>Estado</th>
            </tr>

            <%
                ResultSet rs = (ResultSet) request.getAttribute("inventario");
                if (rs != null) {
                    while (rs.next()) {
            %>
            <tr>
                <td><%= rs.getString("codigo_producto")%></td>
                <td><%= rs.getString("producto")%></td>
                <td><%= rs.getString("categoria")%></td>
                <td><%= rs.getInt("stock")%></td>
                <td><%= rs.getString("unidad")%></td>
                <td><%= rs.getString("estado")%></td>
            </tr>
            <%
                }
                rs.close();
            } else {
            %>
            <tr><td colspan="6">No se pudo obtener el inventario.</td></tr>
            <%
                }
            %>
        </table>

        <br>
        <a href="reportes.jsp">Volver al módulo de reportes</a>

    </body>
</html>