package dataAccesComponent.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataAccesComponent.IDAO;
import dataAccesComponent.dataHelper.DataHelper;
import dataAccesComponent.entity.RegistroEstados;

public class RegistroEstadosDAO implements IDAO<RegistroEstados> {
    private Connection connection;

    public RegistroEstadosDAO() {
        connection = DataHelper.conectar();
    }

    @Override
    public boolean create(RegistroEstados registroEstado) throws Exception {
        String sql = "INSERT INTO registro_estado (registro_fecha, registro_hora, estado_Chofer, autorizacion, chofer_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDate(1, new java.sql.Date(registroEstado.getFechaIngreso().getTime()));
            preparedStatement.setTime(2, registroEstado.getHoraIngreso());
            preparedStatement.setString(3, registroEstado.getEstadoChofer());
            preparedStatement.setBoolean(4, registroEstado.isAutorizacionChofer());
            preparedStatement.setInt(5, registroEstado.getIdChofer());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al crear registro de estado", e);
        }
    }

    @Override
    public List<RegistroEstados> readAll() throws Exception {
        List<RegistroEstados> registros = new ArrayList<>();
        String sql = "SELECT * FROM registro_estado WHERE estado = 'A'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                RegistroEstados registroEstado = new RegistroEstados();
                registroEstado.setIdRegEst(rs.getInt("registro_estado_id"));
                registroEstado.setFechaIngreso(rs.getDate("registro_fecha"));
                registroEstado.setHoraIngreso(rs.getTime("registro_hora"));
                registroEstado.setEstadoChofer(rs.getString("estado_Chofer"));
                registroEstado.setAutorizacionChofer(rs.getBoolean("autorizacion"));
                registroEstado.setIdChofer(rs.getInt("chofer_id"));
                registroEstado.setEstado(rs.getString("estado"));
                registros.add(registroEstado);
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer registros de estado", e);
        }
        return registros;
    }

    @Override
    public boolean update(RegistroEstados registroEstado) throws Exception {
        String sql = "UPDATE registro_estado SET registro_fecha = ?, registro_hora = ?, estado_Chofer = ?, autorizacion = ?, chofer_id = ? WHERE registro_estado_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDate(1, new java.sql.Date(registroEstado.getFechaIngreso().getTime()));
            preparedStatement.setTime(2, registroEstado.getHoraIngreso());
            preparedStatement.setString(3, registroEstado.getEstadoChofer());
            preparedStatement.setBoolean(4, registroEstado.isAutorizacionChofer());
            preparedStatement.setInt(5, registroEstado.getIdChofer());

            preparedStatement.setInt(6, registroEstado.getIdRegEst());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar registro de estado", e);
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String sql = "UPDATE registro_estado SET estado = 'X' WHERE registro_estado_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al eliminar registro de estado", e);
        }
    }

    @Override
    public RegistroEstados readBy(Integer id) throws Exception {
        RegistroEstados registroEstado = null;
        String sql = "SELECT * FROM registro_estado WHERE registro_estado_id = ? AND estado = 'A'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    registroEstado = new RegistroEstados();
                    registroEstado.setIdRegEst(rs.getInt("registro_estado_id"));
                    registroEstado.setFechaIngreso(rs.getDate("registro_fecha"));
                    registroEstado.setHoraIngreso(rs.getTime("registro_hora"));
                    registroEstado.setEstadoChofer(rs.getString("estado_Chofer"));
                    registroEstado.setAutorizacionChofer(rs.getBoolean("autorizacion"));
                    registroEstado.setIdChofer(rs.getInt("chofer_id"));
                    registroEstado.setEstado(rs.getString("estado"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer registro de estado por ID", e);
        }
        return registroEstado;
    }
}
