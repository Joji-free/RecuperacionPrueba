package org.anderson.proyectocompraventa.controllers.models;

public class Categoria {
    private Long idCategoria;
    private String nombre;
    private String descripcion;
    private int condicion;

    //implementamso el constructor
    public Categoria() {

    }
    //Inicializamso el contructor con todos los parametros
    public Categoria(Long idCategoria, String nombre, String descripcion, int condicion) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.condicion = condicion;
    }

    //Implementamos los métodos get y set


    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCondicion() {
        return condicion;
    }

    public void setCondicion(int condicion) {
        this.condicion = condicion;
    }
}
