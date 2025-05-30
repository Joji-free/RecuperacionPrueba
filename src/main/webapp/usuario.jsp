<%--
  Created by IntelliJ IDEA.
  User: ander
  Date: 30/5/2025
  Time: 3:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.anderson.proyectocompraventa.controllers.models.Usuario" %>
<%@ page import="java.util.List" %>
<%
    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
    String username = (String) request.getAttribute("username");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Usuarios</title>
</head>
<body>
<h1>Bienvenido <%= username != null ? username : "Invitado" %></h1>
<h2>Registrar nuevo usuario</h2>
<form action="usuario" method="post">
    <label>Nombre:</label><input type="text" name="nombre" required><br>
    <label>Tipo Documento:</label><input type="text" name="tipo_documento" required><br>
    <label>Número Documento:</label><input type="text" name="num_documento" required><br>
    <label>Dirección:</label><input type="text" name="direccion"><br>
    <label>Teléfono:</label><input type="text" name="telefono"><br>
    <label>Email:</label><input type="email" name="email"><br>
    <label>Cargo:</label><input type="text" name="cargo"><br>
    <label>Login:</label><input type="text" name="login" required><br>
    <label>Clave:</label><input type="password" name="clave" required><br>
    <label>Imagen:</label><input type="text" name="imagen"><br> <!-- o usa input type="file" si manejas archivos -->
    <label>Condición (1=Activo, 0=Inactivo):</label>
    <input type="number" name="condicion" min="0" max="1" required><br>
    <input type="submit" value="Guardar">
</form>

<h2>Lista de Usuarios</h2>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Tipo Documento</th>
        <th>Número Documento</th>
        <th>Dirección</th>
        <th>Teléfono</th>
        <th>Email</th>
        <th>Cargo</th>
        <th>Login</th>
        <th>Clave</th>
        <th>Imagen</th>
        <th>Condición</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (Usuario u : usuarios) {
    %>
    <tr>
        <td><%= u.getIdusuario() %></td>
        <td><%= u.getNombre() %></td>
        <td><%= u.getTipoDocumento() %></td>
        <td><%= u.getNumDocumento() %></td>
        <td><%= u.getDireccion() %></td>
        <td><%= u.getTelefono() %></td>
        <td><%= u.getEmail() %></td>
        <td><%= u.getCargo() %></td>
        <td><%= u.getLogin() %></td>
        <td><%= u.getClave() %></td>
        <td><%= u.getImagen() %></td>
        <td><%= u.getCondicion() == 1 ? "Activo" : "Inactivo" %></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>
