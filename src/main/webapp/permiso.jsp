<%--
  Created by IntelliJ IDEA.
  User: ander
  Date: 30/5/2025
  Time: 4:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.anderson.proyectocompraventa.controllers.models.Permiso" %>
<%@ page import="java.util.List" %>

<%
    List<Permiso> permisos = (List<Permiso>) request.getAttribute("permisos");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Permisos</title>
    <style>
        table, th, td {
            border: 1px solid #000;
            border-collapse: collapse;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
        }
        form {
            margin-top: 20px;
        }
    </style>
</head>
<body>
<h1>Gestión de Permisos</h1>

<h2>Registrar Nuevo Permiso</h2>
<form action="permiso" method="post">
    <label>Nombre del Permiso:</label>
    <input type="text" name="nombre" required />
    <button type="submit">Guardar</button>
</form>

<h2>Lista de Permisos</h2>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Nombre</th>
    </tr>
    </thead>
    <tbody>
    <% if (permisos != null && !permisos.isEmpty()) {
        for (Permiso p : permisos) { %>
    <tr>
        <td><%= p.getIdPermiso() %></td>
        <td><%= p.getNombre() %></td>
    </tr>
    <%  }
    } else { %>
    <tr><td colspan="2">No hay permisos registrados.</td></tr>
    <% } %>
    </tbody>
</table>
</body>
</html>

