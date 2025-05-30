package org.anderson.proyectocompraventa.controllers.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.anderson.proyectocompraventa.controllers.models.Usuario;
import org.anderson.proyectocompraventa.controllers.services.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet("/usuario")
public class UsuarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");
        Connection conn = (Connection) req.getAttribute("conn");
        UsuarioService servicio = new UsuarioServiceJdbcImplement(conn);

        if ("listar".equalsIgnoreCase(accion)) {
            List<Usuario> usuarios = servicio.listar();
            enviarComoJson(resp, usuarios);
            return;
        }

        List<Usuario> usuarios = servicio.listar();
        LoginService auth = new LoginServiceSessionImplement();
        Optional<String> usernameOptional = auth.getUserName(req);

        req.setAttribute("usuarios", usuarios);
        req.setAttribute("username", usernameOptional);
        getServletContext().getRequestDispatcher("/usuario.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        UsuarioService servicio = new UsuarioServiceJdbcImplement(conn);

        Usuario u = new Usuario();
        u.setNombre(req.getParameter("nombre"));
        u.setTipoDocumento(req.getParameter("tipo_documento"));
        u.setNumDocumento(req.getParameter("num_documento"));
        u.setDireccion(req.getParameter("direccion"));
        u.setTelefono(req.getParameter("telefono"));
        u.setEmail(req.getParameter("email"));
        u.setCargo(req.getParameter("cargo"));
        u.setLogin(req.getParameter("login"));
        u.setClave(req.getParameter("clave"));
        u.setImagen(req.getParameter("imagen")); // si es subida por multipart, debes ajustar esto
        u.setCondicion(Integer.parseInt(req.getParameter("condicion")));

        try {
            servicio.guardar(u);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al guardar el usuario");
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/usuario?accion=listar");
    }

    private void enviarComoJson(HttpServletResponse response, List<Usuario> usuarios) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonObject responseJson = new JsonObject();
        responseJson.add("data", new Gson().toJsonTree(usuarios));
        response.getWriter().write(responseJson.toString());
    }
}
