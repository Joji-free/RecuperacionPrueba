package org.anderson.proyectocompraventa.controllers.repositories;

import org.anderson.proyectocompraventa.controllers.models.Permiso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PermisoRepositoryJdbcImpl implements Repository<Permiso> {

    private Connection conn;

    public PermisoRepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Permiso> listar() throws SQLException {
        List<Permiso> permisos = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM permiso")) {
            while (rs.next()) {
                Permiso p = new Permiso();
                p.setIdPermiso(rs.getLong("idpermiso"));
                p.setNombre(rs.getString("nombre"));
                permisos.add(p);
            }
        }
        return permisos;
    }

    @Override
    public Permiso porId(Long id) throws SQLException {
        Permiso p = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM permiso WHERE idpermiso = ?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    p = new Permiso();
                    p.setIdPermiso(rs.getLong("idpermiso"));
                    p.setNombre(rs.getString("nombre"));
                }
            }
        }
        return p;
    }

    @Override
    public void guardar(Permiso permiso) throws SQLException {
        String sql = (permiso.getIdPermiso() != null && permiso.getIdPermiso() > 0)
                ? "UPDATE permiso SET nombre = ? WHERE idpermiso = ?"
                : "INSERT INTO permiso (nombre) VALUES (?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, permiso.getNombre());

            if (permiso.getIdPermiso() != null && permiso.getIdPermiso() > 0) {
                stmt.setLong(2, permiso.getIdPermiso());
            }

            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM permiso WHERE idpermiso = ?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
