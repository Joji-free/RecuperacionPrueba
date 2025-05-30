package org.anderson.proyectocompraventa.controllers.models;

public class Articulo {
    private Long idArticulo;
    private Long idCategoria;
    private String codigo;
    private String nombre;
    private int stock;
    private String descripcion;
    private String imagen;
    private int condicion;

    public Articulo() {}

    public Articulo(Long idArticulo, Long idCategoria, String codigo, String nombre, int stock, String descripcion, String imagen, int condicion) {
        this.idArticulo = idArticulo;
        this.idCategoria = idCategoria;
        this.codigo = codigo;
        this.nombre = nombre;
        this.stock = stock;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.condicion = condicion;
    }

    public Long getIdArticulo() { return idArticulo; }
    public void setIdArticulo(Long idArticulo) { this.idArticulo = idArticulo; }

    public Long getIdCategoria() { return idCategoria; }
    public void setIdCategoria(Long idCategoria) { this.idCategoria = idCategoria; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public int getCondicion() { return condicion; }
    public void setCondicion(int condicion) { this.condicion = condicion; }
}
