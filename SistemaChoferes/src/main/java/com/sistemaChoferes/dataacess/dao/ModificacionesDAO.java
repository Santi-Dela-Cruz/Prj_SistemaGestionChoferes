package com.sistemaChoferes.dataacess.dao;
import com.sistemaChoferes.dataacess.datahelper.DataHelper;
import com.sistemaChoferes.dataacess.entity.Modificaciones;
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
        String sql = "INSERT INTO modificaciones (id_Administrador, fechaModificacion, horaModificacion, accionAdmin) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, modificaciones.getIdAdministrador());
            preparedStatement.setDate(2, new java.sql.Date(modificaciones.getFechaModificacion().getTime()));
            preparedStatement.setString(3, modificaciones.getHoraModificacion());
            preparedStatement.setString(4, modificaciones.getAccionAdmin());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al crear modificación", e);
        }
    }

    @Override
    public List<Modificaciones> readAll() throws Exception {
        List<Modificaciones> modificacionesList = new ArrayList<>();
        String sql = "SELECT * FROM modificaciones";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                Modificaciones modificaciones = new Modificaciones();
                modificaciones.setId(rs.getInt("id"));
                modificaciones.setIdAdministrador(rs.getInt("id_Administrador"));
                modificaciones.setFechaModificacion(rs.getDate("fechaModificacion"));
                modificaciones.setHoraModificacion(rs.getString("horaModificacion"));
                modificaciones.setAccionAdmin(rs.getString("accionAdmin"));
                modificacionesList.add(modificaciones);
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer modificaciones", e);
        }
        return modificacionesList;
    }

    @Override
    public boolean update(Modificaciones modificaciones) throws Exception {
        String sql = "UPDATE modificaciones SET id_Administrador = ?, fechaModificacion = ?, horaModificacion = ?, accionAdmin = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, modificaciones.getIdAdministrador());
            preparedStatement.setDate(2, new java.sql.Date(modificaciones.getFechaModificacion().getTime()));
            preparedStatement.setString(3, modificaciones.getHoraModificacion());
            preparedStatement.setString(4, modificaciones.getAccionAdmin());
            preparedStatement.setInt(5, modificaciones.getId());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar modificación", e);
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String sql = "DELETE FROM modificaciones WHERE id = ?";
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
        String sql = "SELECT * FROM modificaciones WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    modificaciones = new Modificaciones();
                    modificaciones.setId(rs.getInt("id"));
                    modificaciones.setIdAdministrador(rs.getInt("id_Administrador"));
                    modificaciones.setFechaModificacion(rs.getDate("fechaModificacion"));
                    modificaciones.setHoraModificacion(rs.getString("horaModificacion"));
                    modificaciones.setAccionAdmin(rs.getString("accionAdmin"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer modificación por ID", e);
        }
        return modificaciones;
    }
}
