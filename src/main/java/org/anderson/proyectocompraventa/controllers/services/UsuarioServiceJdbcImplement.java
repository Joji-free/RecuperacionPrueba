package org.anderson.proyectocompraventa.controllers.services;

import org.anderson.proyectocompraventa.controllers.models.Usuario;
import org.anderson.proyectocompraventa.controllers.repositories.UsuarioRepositoryJdbcImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsuarioServiceJdbcImplement implements UsuarioService {

    private final UsuarioRepositoryJdbcImpl repository;

    public UsuarioServiceJdbcImplement(Connection conn) {
        this.repository = new UsuarioRepositoryJdbcImpl(conn);
    }

    @Override
    public List<Usuario> listar() {
        try {
            return repository.listar();
        } catch (SQLException e) {
            throw new ServiceJdcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Usuario> porId(Long id) {
        try {
            return Optional.ofNullable(repository.porId(id));
        } catch (SQLException e) {
            throw new ServiceJdcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void guardar(Usuario usuario) {
        try {
            repository.guardar(usuario);
        } catch (SQLException e) {
            throw new ServiceJdcException(e.getMessage(), e.getCause());
        }
    }
}
