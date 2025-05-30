package org.anderson.proyectocompraventa.controllers.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class LoginServiceSessionImplement implements LoginService {
    @Override
    public Optional<String> getUserName(HttpServletRequest request) {
        //Btengo la Sesion
        HttpSession session = request.getSession();
        //Covierto el objeto de tipo Sesion a String
        String username = (String) session.getAttribute("username");
        if (username != null) {
            return Optional.of(username);
        }
        return Optional.empty();
    }
}
