package org.anderson.proyectocompraventa.controllers.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.anderson.proyectocompraventa.controllers.models.Usuario;
import org.anderson.proyectocompraventa.controllers.services.LoginService;
import org.anderson.proyectocompraventa.controllers.services.LoginServiceSessionImplement;
import org.anderson.proyectocompraventa.controllers.services.UsuarioService;
import org.anderson.proyectocompraventa.controllers.services.UsuarioServiceJdbcImplement;
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

        String nombre = req.getParameter("nombre");
        String correo = req.getParameter("correo");
        String clave = req.getParameter("clave");

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setCorreo(correo);
        usuario.setClave(clave);
        usuario.setEstado(1); // activo

        try {
            servicio.guardar(usuario);
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
