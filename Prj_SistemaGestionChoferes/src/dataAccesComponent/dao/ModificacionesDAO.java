package dataAccesComponent.dao;

import dataAccesComponent.IDAO;
import dataAccesComponent.dataHelper.DataHelper;
import dataAccesComponent.entity.Modificaciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModificacionesDAO implements IDAO<Modificaciones> {
    private Connection connection;

    public ModificacionesDAO() {
        connection = DataHelper.conectar();
    }

    @Override
    public boolean create(Modificaciones modificaciones) throws Exception {
        String sql = "INSERT INTO modificacion (admin_id, chofer_id_modificado, modificacion_fecha, modificacion_hora, modificacion_accion) "
                +
                "VALUES (?, ?, CURRENT_DATE(), CURRENT_TIME(), ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, modificaciones.getIdAdministrador());
            preparedStatement.setInt(2, modificaciones.getIdChoferModificacion());
            preparedStatement.setString(3, modificaciones.getAccionAdmin());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al crear modificación", e);
        }
    }

    @Override
    public List<Modificaciones> readAll() throws Exception {
        List<Modificaciones> modificacionesList = new ArrayList<>();
        String sql = "SELECT * FROM modificacion";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                Modificaciones modificaciones = new Modificaciones();
                modificaciones.setId(rs.getInt("modificacion_id"));
                modificaciones.setIdAdministrador(rs.getInt("admin_id"));
                modificaciones.setIdChoferModificacion(rs.getInt("chofer_id_modificado"));
                modificaciones.setFechaModificacion(rs.getDate("modificacion_fecha"));
                modificaciones.setHoraModificacion(rs.getString("modificacion_hora"));
                modificaciones.setAccionAdmin(rs.getString("modificacion_accion"));
                modificacionesList.add(modificaciones);
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer modificaciones", e);
        }
        return modificacionesList;
    }

    @Override
    public boolean update(Modificaciones modificaciones) throws Exception {
        String sql = "UPDATE modificacion SET admin_id = ?, chofer_id_modificado = ?, modificacion_fecha = ?, modificacion_hora = ?, modificacion_accion = ? WHERE modificacion_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, modificaciones.getIdAdministrador());
            preparedStatement.setInt(2, modificaciones.getIdChoferModificacion());
            preparedStatement.setDate(3, new java.sql.Date(modificaciones.getFechaModificacion().getTime()));
            preparedStatement.setString(4, modificaciones.getHoraModificacion());
            preparedStatement.setString(5, modificaciones.getAccionAdmin());
            preparedStatement.setInt(6, modificaciones.getId());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar modificación", e);
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String sql = "DELETE FROM modificacion WHERE modificacion_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al eliminar modificación", e);
        }
    }

    @Override
    public Modificaciones readBy(Integer id) throws Exception {
        Modificaciones modificaciones = null;
        String sql = "SELECT * FROM modificacion WHERE modificacion_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    modificaciones = new Modificaciones();
                    modificaciones.setId(rs.getInt("modificacion_id"));
                    modificaciones.setIdAdministrador(rs.getInt("admin_id"));
                    modificaciones.setIdChoferModificacion(rs.getInt("chofer_id_modificado"));
                    modificaciones.setFechaModificacion(rs.getDate("modificacion_fecha"));
                    modificaciones.setHoraModificacion(rs.getString("modificacion_hora"));
                    modificaciones.setAccionAdmin(rs.getString("modificacion_accion"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer modificación por ID", e);
        }
        return modificaciones;
    }

    public Modificaciones obtenerPrimeraModificacionPorChoferId(int idChofer) throws Exception {
        Modificaciones modificaciones = null;
        String sql = "SELECT * FROM modificacion WHERE chofer_id_modificado = ? ORDER BY modificacion_fecha ASC, modificacion_hora ASC LIMIT 1";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idChofer);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    modificaciones = new Modificaciones();
                    modificaciones.setId(rs.getInt("modificacion_id"));
                    modificaciones.setIdAdministrador(rs.getInt("admin_id"));
                    modificaciones.setIdChoferModificacion(rs.getInt("chofer_id_modificado"));
                    modificaciones.setFechaModificacion(rs.getDate("modificacion_fecha"));
                    modificaciones.setHoraModificacion(rs.getString("modificacion_hora"));
                    modificaciones.setAccionAdmin(rs.getString("modificacion_accion"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al obtener la primera modificación por chofer ID", e);
        }
        return modificaciones;
    }

    public Modificaciones obtenerUltimaModificacionPorChoferId(int idChofer) throws Exception {
        Modificaciones modificaciones = null;
        String sql = "SELECT * FROM modificacion WHERE chofer_id_modificado = ? AND modificacion_accion = 'Chofer actualizado' ORDER BY modificacion_fecha DESC, modificacion_hora DESC LIMIT 1";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idChofer);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    modificaciones = new Modificaciones();
                    modificaciones.setId(rs.getInt("modificacion_id"));
                    modificaciones.setIdAdministrador(rs.getInt("admin_id"));
                    modificaciones.setIdChoferModificacion(rs.getInt("chofer_id_modificado"));
                    modificaciones.setFechaModificacion(rs.getDate("modificacion_fecha"));
                    modificaciones.setHoraModificacion(rs.getString("modificacion_hora"));
                    modificaciones.setAccionAdmin(rs.getString("modificacion_accion"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al obtener la última modificación por chofer ID y acción 'Chofer actualizado'",
                    e);
        }
        return modificaciones;
    }

}
