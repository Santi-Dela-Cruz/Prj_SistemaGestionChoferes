package dataAccesComponent.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataAccesComponent.IDAO;
import dataAccesComponent.dataHelper.DataHelper;
import dataAccesComponent.entity.RegistroPenalizaciones;

public class RegistroPenalizacionesDAO implements IDAO<RegistroPenalizaciones> {
    private Connection connection;

    public RegistroPenalizacionesDAO() {
        connection = DataHelper.conectar();
    }

    @Override
    public boolean create(RegistroPenalizaciones registroPenalizaciones) throws Exception {
        String sql = "INSERT INTO registro_penalizaciones (n_Infracciones, penalizacion_Chofer, id_Chofer, estado) VALUES (?, ?, ?, 'A')";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, registroPenalizaciones.getNInfracciones());
            preparedStatement.setString(2, registroPenalizaciones.getPenalizacionChofer());
            preparedStatement.setInt(3, registroPenalizaciones.getIdChofer());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al crear registro de penalización", e);
        }
    }

    @Override
    public List<RegistroPenalizaciones> readAll() throws Exception {
        List<RegistroPenalizaciones> registros = new ArrayList<>();
        String sql = "SELECT * FROM registro_penalizaciones WHERE estado = 'A'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                RegistroPenalizaciones registro = new RegistroPenalizaciones();
                registro.setIdRegPen(rs.getInt("id_RegPen"));
                registro.setNInfracciones(rs.getInt("n_Infracciones"));
                registro.setPenalizacionChofer(rs.getString("penalizacion_Chofer"));
                registro.setIdChofer(rs.getInt("id_Chofer"));
                registro.setEstado(rs.getString("estado"));
                registros.add(registro);
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer registros de penalización", e);
        }
        return registros;
    }

    @Override
    public boolean update(RegistroPenalizaciones registroPenalizaciones) throws Exception {
        String sql = "UPDATE registro_penalizaciones SET n_Infracciones = ?, penalizacion_Chofer = ? WHERE id_RegPen = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, registroPenalizaciones.getNInfracciones());
            preparedStatement.setString(2, registroPenalizaciones.getPenalizacionChofer());
            preparedStatement.setInt(3, registroPenalizaciones.getIdRegPen());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar registro de penalización", e);
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String sql = "UPDATE registro_penalizaciones SET estado = 'X' WHERE id_RegPen = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al eliminar registro de penalización", e);
        }
    }

    @Override
    public RegistroPenalizaciones readBy(Integer id) throws Exception {
        RegistroPenalizaciones registro = null;
        String sql = "SELECT * FROM registro_penalizaciones WHERE id_RegPen = ? AND estado = 'A'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    registro = new RegistroPenalizaciones();
                    registro.setIdRegPen(rs.getInt("id_RegPen"));
                    registro.setNInfracciones(rs.getInt("n_Infracciones"));
                    registro.setPenalizacionChofer(rs.getString("penalizacion_Chofer"));
                    registro.setIdChofer(rs.getInt("id_Chofer"));
                    registro.setEstado(rs.getString("estado"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer registro de penalización por ID", e);
        }
        return registro;
    }

    public boolean actualizarPenalizaciones(int idChofer) throws Exception {
        try {
            PreparedStatement checkPenalizaciones = connection
                    .prepareStatement(
                            "SELECT n_Infracciones FROM registro_penalizaciones WHERE id_Chofer=? AND estado='A'");
            checkPenalizaciones.setInt(1, idChofer);
            ResultSet rs = checkPenalizaciones.executeQuery();

            if (rs.next()) {
                int infracciones = rs.getInt("n_Infracciones") + 1;
                PreparedStatement updatePenalizaciones = connection.prepareStatement(
                        "UPDATE registro_penalizaciones SET n_Infracciones=?, penalizacion_Chofer=?, estado=? WHERE id_Chofer=?");
                updatePenalizaciones.setInt(1, infracciones);
                updatePenalizaciones.setString(2, infracciones >= 3 ? "Despedido" : "Advertencia");
                updatePenalizaciones.setString(3, infracciones >= 3 ? "X" : "A");
                updatePenalizaciones.setInt(4, idChofer);
                updatePenalizaciones.executeUpdate();

                if (infracciones >= 3) {
                    actualizarEstadoChofer(idChofer);
                    return true;
                }
            } else {
                PreparedStatement insertPenalizaciones = connection.prepareStatement(
                        "INSERT INTO registro_penalizaciones (n_Infracciones, penalizacion_Chofer, id_Chofer, estado) VALUES (1, 'Advertencia', ?, 'A')");
                insertPenalizaciones.setInt(1, idChofer);
                insertPenalizaciones.executeUpdate();
            }
        } catch (SQLException e) {
            throw new Exception("Error al actualizar penalizaciones", e);
        }
        return false;
    }

    private void actualizarEstadoChofer(int idChofer) throws SQLException {
        PreparedStatement updateChofer = connection
                .prepareStatement("UPDATE choferes SET estado='X' WHERE id_Chofer=?");
        updateChofer.setInt(1, idChofer);
        updateChofer.executeUpdate();

        PreparedStatement updateHuella = connection.prepareStatement("UPDATE huella SET estado='X' WHERE id_Chofer=?");
        updateHuella.setInt(1, idChofer);
        updateHuella.executeUpdate();

        PreparedStatement updateVehiculo = connection
                .prepareStatement("UPDATE vehiculo SET estado='X' WHERE id_Chofer=?");
        updateVehiculo.setInt(1, idChofer);
        updateVehiculo.executeUpdate();

        PreparedStatement updateRuta = connection.prepareStatement("UPDATE rutas SET estado='X' WHERE id_Chofer=?");
        updateRuta.setInt(1, idChofer);
        updateRuta.executeUpdate();
    }

}
