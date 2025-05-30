package org.anderson.proyectocompraventa.controllers.services;

import org.anderson.proyectocompraventa.controllers.models.Categoria;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    List<Categoria> listar();
    Optional<Categoria> porId(Long id) throws SQLException;
    void guardar(Categoria categoria);

}
