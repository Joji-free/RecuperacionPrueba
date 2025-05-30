package org.anderson.proyectocompraventa.controllers.models;

public class Usuario {
    private Long idUsuario;
    private String nombre;
    private String correo;
    private String clave;
    private int estado;

    public Usuario() {}

    public Usuario(Long idUsuario, String nombre, String correo, String clave, int estado) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.correo = correo;
        this.clave = clave;
        this.estado = estado;
    }

    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }

    public int getEstado() { return estado; }
    public void setEstado(int estado) { this.estado = estado; }
}

