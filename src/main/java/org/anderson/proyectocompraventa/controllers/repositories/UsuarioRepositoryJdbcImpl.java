package org.anderson.proyectocompraventa.controllers.repositories;

import org.anderson.proyectocompraventa.controllers.models.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositoryJdbcImpl implements Repository<Usuario> {

    private Connection conn;

    public UsuarioRepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Usuario> listar() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM usuario")) {
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getLong("idUsuario"));
                u.setNombre(rs.getString("nombre"));
                u.setCorreo(rs.getString("correo"));
                u.setClave(rs.getString("clave"));
                u.setEstado(rs.getInt("estado"));
                usuarios.add(u);
            }
        }
        return usuarios;
    }

    @Override
    public Usuario porId(Long id) throws SQLException {
        Usuario u = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuario WHERE idUsuario = ?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    u = new Usuario();
                    u.setIdUsuario(rs.getLong("idUsuario"));
                    u.setNombre(rs.getString("nombre"));
                    u.setCorreo(rs.getString("correo"));
                    u.setClave(rs.getString("clave"));
                    u.setEstado(rs.getInt("estado"));
                }
            }
        }
        return u;
    }

    @Override
    public void guardar(Usuario u) throws SQLException {
        String sql = (u.getIdUsuario() != null && u.getIdUsuario() > 0) ?
                "UPDATE usuario SET nombre = ?, correo = ?, clave = ?, estado = ? WHERE idUsuario = ?" :
                "INSERT INTO usuario (nombre, correo, clave, estado) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, u.getNombre());
            stmt.setString(2, u.getCorreo());
            stmt.setString(3, u.getClave());
            stmt.setInt(4, u.getEstado());

            if (u.getIdUsuario() != null && u.getIdUsuario() > 0) {
                stmt.setLong(5, u.getIdUsuario());
            }

            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM usuario WHERE idUsuario = ?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
