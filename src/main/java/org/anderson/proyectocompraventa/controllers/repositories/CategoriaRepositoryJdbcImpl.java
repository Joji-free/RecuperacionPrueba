package org.anderson.proyectocompraventa.controllers.repositories;

import org.anderson.proyectocompraventa.controllers.models.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepositoryJdbcImpl implements Repository<Categoria> {
    //Obtenemos la conexión
    private Connection conn;

    public CategoriaRepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select * from categoria")) {
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getLong("idCategoria"));
                categoria.setNombre(rs.getString("nombre"));
                categoria.setDescripcion(rs.getString("descripcion"));
                categoria.setCondicion(rs.getInt("condicion"));
                categorias.add(categoria);
            }

        }
        return categorias;
    }

    @Override
    public Categoria porId(Long id) throws SQLException {
        Categoria categoria = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM categorias WHERE idCategoria = ?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    categoria = new Categoria();
                }
            }
        }
       return categoria;
    }


    @Override
    public void guardar(Categoria categoria) throws SQLException {
        String sql;
        boolean esActualizar = categoria.getIdCategoria() != null && categoria.getIdCategoria() > 0;

        if (esActualizar) {
            sql = "UPDATE categoria SET nombre = ?, descripcion = ?, condicion = ? WHERE idCategoria = ?";
        } else {
            sql = "INSERT INTO categoria (nombre, descripcion, condicion) VALUES (?, ?, ?)";
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, categoria.getNombre());
            stmt.setString(2, categoria.getDescripcion());
            stmt.setInt(3, categoria.getCondicion());

            if (esActualizar) {
                stmt.setLong(4, categoria.getIdCategoria());
            }

            stmt.executeUpdate();
        }
    }



    @Override
    public void eliminar(Long id) throws SQLException {

    }

    private static Categoria getCategoria(ResultSet rs) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(rs.getLong("idCategoria"));
        categoria.setNombre(rs.getString("nombre"));
        categoria.setDescripcion(rs.getString("descripcion"));
        categoria.setCondicion(rs.getInt("condicion"));
        return categoria;
    }
}
