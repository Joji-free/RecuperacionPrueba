package org.anderson.proyectocompraventa.controllers.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.anderson.proyectocompraventa.controllers.services.ServiceJdcException;
import org.anderson.proyectocompraventa.controllers.util.ConexionBDD;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class ConexionFilter implements Filter {

    //El filter esta orientado al trabajo con la petición request



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try(Connection conn = ConexionBDD.getConnection()){
            if(conn .getAutoCommit()){
                conn.setAutoCommit(false);
            }
            try {
                servletRequest.setAttribute("conn", conn);
                filterChain.doFilter(servletRequest,servletResponse);
                conn.commit();
            }catch(SQLException | ServiceJdcException e){
                conn.rollback();
                ((HttpServletResponse)servletResponse).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
