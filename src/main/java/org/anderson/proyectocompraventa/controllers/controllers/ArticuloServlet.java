package org.anderson.proyectocompraventa.controllers.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.anderson.proyectocompraventa.controllers.models.Articulo;
import org.anderson.proyectocompraventa.controllers.services.ArticuloService;
import org.anderson.proyectocompraventa.controllers.services.ArticuloServiceJdbcImplement;
import org.anderson.proyectocompraventa.controllers.services.LoginService;
import org.anderson.proyectocompraventa.controllers.services.LoginServiceSessionImplement;
import org.anderson.proyectocompraventa.controllers.services.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet("/articulo")
public class ArticuloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");
        Connection conn = (Connection) req.getAttribute("conn");
        ArticuloService servicio = new ArticuloServiceJdbcImplement(conn);

        if ("listar".equalsIgnoreCase(accion)) {
            List<Articulo> articulos = servicio.listar();
            enviarComoJson(resp, articulos);
            return;
        }

        List<Articulo> articulos = servicio.listar();
        LoginService auth = new LoginServiceSessionImplement();
        Optional<String> usernameOptional = auth.getUserName(req);

        req.setAttribute("articulos", articulos);
        req.setAttribute("username", usernameOptional);
        getServletContext().getRequestDispatcher("/articulo.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ArticuloService servicio = new ArticuloServiceJdbcImplement(conn);

        Long idCategoria = Long.valueOf(req.getParameter("idcategoria"));
        String codigo = req.getParameter("codigo");
        String nombre = req.getParameter("nombre");
        int stock = Integer.parseInt(req.getParameter("stock"));
        String descripcion = req.getParameter("descripcion");
        String imagen = req.getParameter("imagen");

        Articulo articulo = new Articulo();
        articulo.setIdCategoria(idCategoria);
        articulo.setCodigo(codigo);
        articulo.setNombre(nombre);
        articulo.setStock(stock);
        articulo.setDescripcion(descripcion);
        articulo.setImagen(imagen);
        articulo.setCondicion(1); // activo

        try {
            servicio.guardar(articulo);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al guardar el artículo");
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/articulo?accion=listar");
    }

    private void enviarComoJson(HttpServletResponse response, List<Articulo> articulos) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonObject responseJson = new JsonObject();
        responseJson.add("data", new Gson().toJsonTree(articulos));
        response.getWriter().write(responseJson.toString());
    }
}
