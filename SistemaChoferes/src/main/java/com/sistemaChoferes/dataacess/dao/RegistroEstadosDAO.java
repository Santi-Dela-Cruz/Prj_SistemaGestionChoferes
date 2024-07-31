package com.sistemaChoferes.dataacess.dao;

import com.sistemaChoferes.dataacess.entity.RegistroEstados;
import com.sistemaChoferes.dataacess.datahelper.DataHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistroEstadosDAO implements IDAO<RegistroEstados> {
    private Connection connection;

    public RegistroEstadosDAO() {
        connection = DataHelper.conectar();
    }

    @Override
    public boolean create(RegistroEstados registroEstado) throws Exception {
        String sql = "INSERT INTO registro_estados (fecha_Ingreso, hora_Ingreso, estado_Chofer, autorizacion_Chofer, id_Chofer, estado) VALUES (?, ?, ?, ?, ?, 'A')";
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
        String sql = "SELECT * FROM registro_estados WHERE estado = 'A'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                RegistroEstados registroEstado = new RegistroEstados();
                registroEstado.setIdRegEst(rs.getInt("id_RegEst"));
                registroEstado.setFechaIngreso(rs.getDate("fecha_Ingreso"));
                registroEstado.setHoraIngreso(rs.getTime("hora_Ingreso"));
                registroEstado.setEstadoChofer(rs.getString("estado_Chofer"));
                registroEstado.setAutorizacionChofer(rs.getBoolean("autorizacion_Chofer"));
                registroEstado.setIdChofer(rs.getInt("id_Chofer"));
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
        String sql = "UPDATE registro_estados SET fecha_Ingreso = ?, hora_Ingreso = ?, estado_Chofer = ?, autorizacion_Chofer = ?, id_Chofer = ?, estado = ? WHERE id_RegEst = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDate(1, new java.sql.Date(registroEstado.getFechaIngreso().getTime()));
            preparedStatement.setTime(2, registroEstado.getHoraIngreso());
            preparedStatement.setString(3, registroEstado.getEstadoChofer());
            preparedStatement.setBoolean(4, registroEstado.isAutorizacionChofer());
            preparedStatement.setInt(5, registroEstado.getIdChofer());
            preparedStatement.setString(6, registroEstado.getEstado());
            preparedStatement.setInt(7, registroEstado.getIdRegEst());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar registro de estado", e);
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String sql = "UPDATE registro_estados SET estado = 'X' WHERE id_RegEst = ?";
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
        String sql = "SELECT * FROM registro_estados WHERE id_RegEst = ? AND estado = 'A'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    registroEstado = new RegistroEstados();
                    registroEstado.setIdRegEst(rs.getInt("id_RegEst"));
                    registroEstado.setFechaIngreso(rs.getDate("fecha_Ingreso"));
                    registroEstado.setHoraIngreso(rs.getTime("hora_Ingreso"));
                    registroEstado.setEstadoChofer(rs.getString("estado_Chofer"));
                    registroEstado.setAutorizacionChofer(rs.getBoolean("autorizacion_Chofer"));
                    registroEstado.setIdChofer(rs.getInt("id_Chofer"));
                    registroEstado.setEstado(rs.getString("estado"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer registro de estado por ID", e);
        }
        return registroEstado;
    }
}
