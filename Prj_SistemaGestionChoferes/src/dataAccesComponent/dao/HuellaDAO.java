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
        String sql = "INSERT INTO huella (id_Codigo_Huella, fecha_RegHuella, id_Chofer, estado) VALUES (?, NOW(), ?, 'A')";
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
        String sql = "SELECT * FROM huella WHERE estado = 'A'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                Huella huella = new Huella();
                huella.setIdHuella(rs.getInt("id_Huella"));
                huella.setIdCodigoHuella(rs.getString("id_Codigo_Huella"));
                huella.setFechaRegHuella(rs.getDate("fecha_RegHuella"));
                huella.setIdChofer(rs.getInt("id_Chofer"));
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
        String sql = "UPDATE huella SET id_Codigo_Huella = ?, fecha_RegHuella = ?, id_Chofer = ? WHERE id_Huella = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, huella.getIdCodigoHuella());
            preparedStatement.setDate(2, new java.sql.Date(huella.getFechaRegHuella().getTime()));
            preparedStatement.setInt(3, huella.getIdChofer());
            preparedStatement.setInt(4, huella.getIdHuella());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar huella", e);
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String sql = "UPDATE huella SET estado = 'X' WHERE id_Huella = ?";
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
        String sql = "SELECT * FROM huella WHERE id_Huella = ? AND estado = 'A'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    huella = new Huella();
                    huella.setIdHuella(rs.getInt("id_Huella"));
                    huella.setIdCodigoHuella(rs.getString("id_Codigo_Huella"));
                    huella.setFechaRegHuella(rs.getDate("fecha_RegHuella"));
                    huella.setIdChofer(rs.getInt("id_Chofer"));
                    huella.setEstado(rs.getString("estado"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer huella por ID", e);
        }
        return huella;
    }

    public Huella obtenerHuellaPorChoferId(int idChofer) throws Exception {
        String sql = "SELECT * FROM huella WHERE id_Chofer = ? AND estado = 'A'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idChofer);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    Huella huella = new Huella();
                    huella.setIdHuella(rs.getInt("id_Huella"));
                    huella.setIdCodigoHuella(rs.getString("id_Codigo_Huella"));
                    huella.setFechaRegHuella(rs.getDate("fecha_RegHuella"));
                    huella.setIdChofer(rs.getInt("id_Chofer"));
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
        String sql = "SELECT id_Huella FROM huella WHERE id_Codigo_Huella = ? AND estado='A'";
        try (Connection connection = DataHelper.conectar();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, codigoHuella);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id_Huella");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al verificar la huella: " + e.getMessage());
        }
        return null;
    }
}
