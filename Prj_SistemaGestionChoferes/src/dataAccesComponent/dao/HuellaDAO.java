package dataAccesComponent.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dataAccesComponent.IDAO;
import dataAccesComponent.dataHelper.DataHelper;
import dataAccesComponent.entity.Huella;

public class HuellaDAO implements IDAO<Huella> {
    private Connection connection;

    public HuellaDAO() {
        connection = DataHelper.conectar();
    }

    @Override
    public boolean create(Huella huella) throws Exception {
        String sql = "INSERT INTO huella_digital (huella_codigo, chofer_id, estado) VALUES (?, ?, 'A')";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, huella.getIdCodigoHuella());
            preparedStatement.setInt(2, huella.getIdChofer());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al crear huella", e);
        }
    }

    @Override
    public List<Huella> readAll() throws Exception {
        List<Huella> huellas = new ArrayList<>();
        String sql = "SELECT * FROM huella_digital WHERE estado = 'A'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                Huella huella = new Huella();
                huella.setIdHuella(rs.getInt("huella_id"));
                huella.setIdCodigoHuella(rs.getString("huella_codigo"));
                huella.setIdChofer(rs.getInt("chofer_id"));
                huella.setEstado(rs.getString("estado"));
                huellas.add(huella);
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer huellas", e);
        }
        return huellas;
    }

    @Override
    public boolean update(Huella huella) throws Exception {
        String sql = "UPDATE huella_digital SET huella_codigo = ?, chofer_id = ? WHERE huella_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, huella.getIdCodigoHuella());
            preparedStatement.setInt(2, huella.getIdChofer());
            preparedStatement.setInt(3, huella.getIdHuella());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar huella", e);
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String sql = "UPDATE huella_digital SET estado = 'X' WHERE huella_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al eliminar huella", e);
        }
    }

    @Override
    public Huella readBy(Integer id) throws Exception {
        Huella huella = null;
        String sql = "SELECT * FROM huella_digital WHERE huella_id = ? AND estado = 'A'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    huella = new Huella();
                    huella.setIdHuella(rs.getInt("huella_id"));
                    huella.setIdCodigoHuella(rs.getString("huella_codigo"));
                    huella.setIdChofer(rs.getInt("chofer_id"));
                    huella.setEstado(rs.getString("estado"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer huella por ID", e);
        }
        return huella;
    }

    public Huella obtenerHuellaPorChoferId(int idChofer) throws Exception {
        String sql = "SELECT * FROM huella_digital WHERE chofer_id = ? AND estado = 'A'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idChofer);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    Huella huella = new Huella();
                    huella.setIdHuella(rs.getInt("huella_id"));
                    huella.setIdCodigoHuella(rs.getString("huella_codigo"));
                    huella.setIdChofer(rs.getInt("chofer_id"));
                    huella.setEstado(rs.getString("estado"));
                    return huella;
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al obtener huella por chofer ID", e);
        }
        return null;
    }

    public Integer getHuellaIdByCode(String codigoHuella) throws Exception {
        String sql = "SELECT huella_id FROM huella_digital WHERE huella_codigo = ? AND estado='A'";
        try (Connection connection = DataHelper.conectar();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, codigoHuella);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("huella_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al verificar la huella: " + e.getMessage());
        }
        return null;
    }
}
