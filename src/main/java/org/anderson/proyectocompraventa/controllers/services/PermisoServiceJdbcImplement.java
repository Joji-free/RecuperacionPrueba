package org.anderson.proyectocompraventa.controllers.services;

import org.anderson.proyectocompraventa.controllers.models.Permiso;
import org.anderson.proyectocompraventa.controllers.repositories.PermisoRepositoryJdbcImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PermisoServiceJdbcImplement implements PermisoService {

    private final PermisoRepositoryJdbcImpl repository;

    public PermisoServiceJdbcImplement(Connection conn) {
        this.repository = new PermisoRepositoryJdbcImpl(conn);
    }

    @Override
    public List<Permiso> listar() {
        try {
            return repository.listar();
        } catch (SQLException e) {
            throw new ServiceJdcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Permiso> porId(Long id) {
        try {
            return Optional.ofNullable(repository.porId(id));
        } catch (SQLException e) {
            throw new ServiceJdcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void guardar(Permiso permiso) {
        try {
            repository.guardar(permiso);
        } catch (SQLException e) {
            throw new ServiceJdcException(e.getMessage(), e.getCause());
        }
    }
}
