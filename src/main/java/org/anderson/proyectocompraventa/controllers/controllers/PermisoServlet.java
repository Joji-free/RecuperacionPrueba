package org.anderson.proyectocompraventa.controllers.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.anderson.proyectocompraventa.controllers.models.Permiso;
import org.anderson.proyectocompraventa.controllers.services.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/permiso")
public class PermisoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");
        Connection conn = (Connection) req.getAttribute("conn");
        PermisoService servicio = new PermisoServiceJdbcImplement(conn);

        if ("listar".equalsIgnoreCase(accion)) {
            List<Permiso> permisos = servicio.listar();
            enviarComoJson(resp, permisos);
            return;
        }

        List<Permiso> permisos = servicio.listar();
        req.setAttribute("permisos", permisos);
        getServletContext().getRequestDispatcher("/permiso.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        PermisoService servicio = new PermisoServiceJdbcImplement(conn);

        String nombre = req.getParameter("nombre");

        Permiso permiso = new Permiso();
        permiso.setNombre(nombre);

        try {
            servicio.guardar(permiso);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al guardar el permiso");
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/permiso?accion=listar");
    }

    private void enviarComoJson(HttpServletResponse response, List<Permiso> permisos) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonObject responseJson = new JsonObject();
        responseJson.add("data", new Gson().toJsonTree(permisos));
        response.getWriter().write(responseJson.toString());
    }
}
