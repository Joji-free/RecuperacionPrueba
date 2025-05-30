package org.anderson.proyectocompraventa.controllers.repositories;

import org.anderson.proyectocompraventa.controllers.models.Articulo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticuloRepositoryJdbcImpl implements Repository<Articulo> {

    private Connection conn;

    public ArticuloRepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Articulo> listar() throws SQLException {
        List<Articulo> articulos = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM articulo")) {
            while (rs.next()) {
                articulos.add(getArticulo(rs));
            }
        }
        return articulos;
    }

    @Override
    public Articulo porId(Long id) throws SQLException {
        Articulo articulo = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM articulo WHERE idArticulo = ?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    articulo = getArticulo(rs);
                }
            }
        }
        return articulo;
    }

    @Override
    public void guardar(Articulo articulo) throws SQLException {
        String sql = (articulo.getIdArticulo() != null && articulo.getIdArticulo() > 0) ?
                "UPDATE articulo SET idcategoria=?, codigo=?, nombre=?, stock=?, descripcion=?, imagen=?, condicion=? WHERE idArticulo=?" :
                "INSERT INTO articulo (idcategoria, codigo, nombre, stock, descripcion, imagen, condicion) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, articulo.getIdCategoria());
            stmt.setString(2, articulo.getCodigo());
            stmt.setString(3, articulo.getNombre());
            stmt.setInt(4, articulo.getStock());
            stmt.setString(5, articulo.getDescripcion());
            stmt.setString(6, articulo.getImagen());
            stmt.setInt(7, articulo.getCondicion());

            if (articulo.getIdArticulo() != null && articulo.getIdArticulo() > 0) {
                stmt.setLong(8, articulo.getIdArticulo());
            }

            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM articulo WHERE idArticulo = ?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private Articulo getArticulo(ResultSet rs) throws SQLException {
        Articulo articulo = new Articulo();
        articulo.setIdArticulo(rs.getLong("idArticulo"));
        articulo.setIdCategoria(rs.getLong("idcategoria"));
        articulo.setCodigo(rs.getString("codigo"));
        articulo.setNombre(rs.getString("nombre"));
        articulo.setStock(rs.getInt("stock"));
        articulo.setDescripcion(rs.getString("descripcion"));
        articulo.setImagen(rs.getString("imagen"));
        articulo.setCondicion(rs.getInt("condicion"));
        return articulo;
    }
}
