package org.anderson.proyectocompraventa.controllers.repositories;

import org.anderson.proyectocompraventa.controllers.models.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositoryJdbcImpl implements Repository<Usuario> {

    private final Connection conn;

    public UsuarioRepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Usuario> listar() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM usuario")) {
            while (rs.next()) {
                usuarios.add(mapearUsuario(rs));
            }
        }
        return usuarios;
    }

    @Override
    public Usuario porId(Long id) throws SQLException {
        Usuario u = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuario WHERE idusuario = ?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    u = mapearUsuario(rs);
                }
            }
        }
        return u;
    }

    @Override
    public void guardar(Usuario u) throws SQLException {
        String sql = (u.getIdusuario() != null && u.getIdusuario() > 0)
                ? "UPDATE usuario SET nombre=?, tipo_documento=?, num_documento=?, direccion=?, telefono=?, email=?, cargo=?, login=?, clave=?, imagen=?, condicion=? WHERE idusuario=?"
                : "INSERT INTO usuario (nombre, tipo_documento, num_documento, direccion, telefono, email, cargo, login, clave, imagen, condicion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, u.getNombre());
            stmt.setString(2, u.getTipoDocumento());
            stmt.setString(3, u.getNumDocumento());
            stmt.setString(4, u.getDireccion());
            stmt.setString(5, u.getTelefono());
            stmt.setString(6, u.getEmail());
            stmt.setString(7, u.getCargo());
            stmt.setString(8, u.getLogin());
            stmt.setString(9, u.getClave());
            stmt.setString(10, u.getImagen());
            stmt.setInt(11, u.getCondicion());

            if (u.getIdusuario() != null && u.getIdusuario() > 0) {
                stmt.setLong(12, u.getIdusuario());
            }

            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM usuario WHERE idusuario = ?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private Usuario mapearUsuario(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setIdusuario(rs.getLong("idusuario"));
        u.setNombre(rs.getString("nombre"));
        u.setTipoDocumento(rs.getString("tipo_documento"));
        u.setNumDocumento(rs.getString("num_documento"));
        u.setDireccion(rs.getString("direccion"));
        u.setTelefono(rs.getString("telefono"));
        u.setEmail(rs.getString("email"));
        u.setCargo(rs.getString("cargo"));
        u.setLogin(rs.getString("login"));
        u.setClave(rs.getString("clave"));
        u.setImagen(rs.getString("imagen"));
        u.setCondicion(rs.getInt("condicion"));
        return u;
    }
}
