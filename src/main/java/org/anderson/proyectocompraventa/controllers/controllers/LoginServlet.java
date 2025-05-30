package org.anderson.proyectocompraventa.controllers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.anderson.proyectocompraventa.controllers.services.LoginService;
import org.anderson.proyectocompraventa.controllers.services.LoginServiceSessionImplement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet({"/login","/login.html"})
public class LoginServlet extends HttpServlet {
    final static String USERNAME ="admin";
    final static String PASSWORD ="admin123";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Instanciamos el objeto de la sesion
        LoginService auth= new LoginServiceSessionImplement();
        Optional<String> usernameOptional = auth.getUserName(req);
        if (usernameOptional.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8");
            try(PrintWriter out = resp.getWriter()) {
                //Creo la plantilla html
                out.print("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"utf-8\">");
                out.println("<title>Hola " + usernameOptional.get() + "</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Hola " + usernameOptional.get() + " ya has iniciado sesión anteriormente</h1>");
                out.println("<p><a href='"+req.getContextPath()+"/plantilla.jsp'>Volver al inicio</a></p>");
                out.println("<p><a href='"+req.getContextPath()+"/logout'>Cerrar sesion</a></p>");
                out.println("</body>");
                out.println("</html>");
            }
            /*Caso contrario me muestre un error
             * de me devuleve al login  */
        }else{
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {


            //no poner la siguinet linea de código antes de usar el metodo get

            //Creo la sesion
            HttpSession session=req.getSession();
            session.setAttribute("username", username);

            resp.sendRedirect(req.getContextPath()+"/categoria");
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos no esta autorizado para ingresar a esta página!");
        }
    }
}
