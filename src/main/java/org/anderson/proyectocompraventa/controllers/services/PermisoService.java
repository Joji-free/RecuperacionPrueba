package org.anderson.proyectocompraventa.controllers.services;

import org.anderson.proyectocompraventa.controllers.models.Permiso;

import java.util.List;
import java.util.Optional;

public interface PermisoService {
    List<Permiso> listar();
    Optional<Permiso> porId(Long id);
    void guardar(Permiso permiso);
}
