package org.anderson.proyectocompraventa.controllers.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.anderson.proyectocompraventa.controllers.services.LoginService;
import org.anderson.proyectocompraventa.controllers.services.LoginServiceSessionImplement;

import java.io.IOException;
import java.util.Optional;

@WebFilter({"/categoria", "/agregar-carro", "/actualizarcarro"})
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LoginService service = new LoginServiceSessionImplement();
        Optional<String> username = service.getUserName((HttpServletRequest) servletRequest);
        if (username.isPresent()) {
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            ((HttpServletResponse)servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED,"Lo sentimos no estas autoriazado para ingresar a esta página");
        }

    }
}
