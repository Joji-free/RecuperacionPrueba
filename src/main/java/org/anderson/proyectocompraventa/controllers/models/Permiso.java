package org.anderson.proyectocompraventa.controllers.models;


public class Permiso {
    private Long idPermiso;
    private String nombre;

    public Permiso() {}

    public Permiso(Long idPermiso, String nombre) {
        this.idPermiso = idPermiso;
        this.nombre = nombre;
    }

    public Long getIdPermiso() { return idPermiso; }
    public void setIdPermiso(Long idPermiso) { this.idPermiso = idPermiso; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
