package dataAccesComponent.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataAccesComponent.IDAO;
import dataAccesComponent.dataHelper.DataHelper;
import dataAccesComponent.entity.Ruta;

public class RutaDAO implements IDAO<Ruta> {
    private Connection connection;

    public RutaDAO() {
        connection = DataHelper.conectar();
    }

    @Override
    public boolean create(Ruta ruta) throws Exception {
        String sql = "INSERT INTO ruta (ruta_nombre, chofer_id, estado) VALUES (?, ?, 'A')";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, ruta.getNombreRuta());
            preparedStatement.setInt(2, ruta.getIdChofer());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al crear ruta", e);
        }
    }

    @Override
    public List<Ruta> readAll() throws Exception {
        List<Ruta> rutas = new ArrayList<>();
        String sql = "SELECT * FROM ruta WHERE estado = 'A'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                Ruta ruta = new Ruta();
                ruta.setIdRuta(rs.getInt("ruta_id"));
                ruta.setNombreRuta(rs.getString("ruta_nombre"));
                ruta.setIdChofer(rs.getInt("chofer_id"));
                ruta.setEstado(rs.getString("estado"));
                rutas.add(ruta);
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer rutas", e);
        }
        return rutas;
    }

    @Override
    public boolean update(Ruta ruta) throws Exception {
        String sql = "UPDATE ruta SET ruta_nombre = ?, chofer_id = ? WHERE ruta_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, ruta.getNombreRuta());
            preparedStatement.setInt(2, ruta.getIdChofer());
            preparedStatement.setInt(3, ruta.getIdRuta());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar ruta", e);
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String sql = "UPDATE ruta SET estado = 'X' WHERE ruta_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al eliminar ruta", e);
        }
    }

    @Override
    public Ruta readBy(Integer id) throws Exception {
        Ruta ruta = null;
        String sql = "SELECT * FROM ruta WHERE ruta_id = ? AND estado = 'A'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    ruta = new Ruta();
                    ruta.setIdRuta(rs.getInt("ruta_id"));
                    ruta.setNombreRuta(rs.getString("ruta_nombre"));
                    ruta.setIdChofer(rs.getInt("chofer_id"));
                    ruta.setEstado(rs.getString("estado"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer ruta por ID", e);
        }
        return ruta;
    }

    public boolean existeRuta(int idChofer) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM ruta WHERE chofer_id=? AND estado='A'");
            preparedStatement.setInt(1, idChofer);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Ruta obtenerRutaPorChoferId(int idChofer) throws Exception {
        String sql = "SELECT * FROM ruta WHERE chofer_id = ? AND estado = 'A'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idChofer);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    Ruta ruta = new Ruta();
                    ruta.setIdRuta(rs.getInt("ruta_id"));
                    ruta.setNombreRuta(rs.getString("ruta_nombre"));
                    ruta.setIdChofer(rs.getInt("chofer_id"));
                    ruta.setEstado(rs.getString("estado"));
                    return ruta;
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al obtener ruta por chofer ID", e);
        }
        return null;
    }
}
