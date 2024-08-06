package dataAccesComponent.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataAccesComponent.IDAO;
import dataAccesComponent.dataHelper.DataHelper;
import dataAccesComponent.entity.Vehiculo;

public class VehiculoDAO implements IDAO<Vehiculo> {
    private Connection connection;

    public VehiculoDAO() {
        connection = DataHelper.conectar();
    }

    @Override
    public boolean create(Vehiculo vehiculo) throws Exception {
        String sql = "INSERT INTO vehiculo (vehiculo_placa, vehiculo_tipo, chofer_id, vehiculo_marca, vehiculo_modelo, estado) VALUES (?, ?, ?, ?, ?, 'A')";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, vehiculo.getIdPlaca());
            preparedStatement.setString(2, vehiculo.getTipoVehiculo());
            preparedStatement.setInt(3, vehiculo.getIdChofer());
            preparedStatement.setString(4, vehiculo.getMarcaVehiculo());
            preparedStatement.setString(5, vehiculo.getModeloVehiculo());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al crear vehículo", e);
        }
    }

    @Override
    public List<Vehiculo> readAll() throws Exception {
        List<Vehiculo> vehiculos = new ArrayList<>();
        String sql = "SELECT * FROM vehiculo WHERE estado = 'A'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                Vehiculo vehiculo = new Vehiculo();
                vehiculo.setIdVehiculo(rs.getInt("vehiculo_id"));
                vehiculo.setIdPlaca(rs.getString("vehiculo_placa"));
                vehiculo.setTipoVehiculo(rs.getString("vehiculo_tipo"));
                vehiculo.setIdChofer(rs.getInt("chofer_id"));
                vehiculo.setMarcaVehiculo(rs.getString("vehiculo_marca"));
                vehiculo.setModeloVehiculo(rs.getString("vehiculo_modelo"));
                vehiculo.setEstado(rs.getString("estado"));
                vehiculos.add(vehiculo);
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer vehículos", e);
        }
        return vehiculos;
    }

    @Override
    public boolean update(Vehiculo vehiculo) throws Exception {
        String sql = "UPDATE vehiculo SET vehiculo_placa = ?, vehiculo_tipo = ?, chofer_id = ?, vehiculo_marca = ?, vehiculo_modelo = ? WHERE vehiculo_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, vehiculo.getIdPlaca());
            preparedStatement.setString(2, vehiculo.getTipoVehiculo());
            preparedStatement.setInt(3, vehiculo.getIdChofer());
            preparedStatement.setString(4, vehiculo.getMarcaVehiculo());
            preparedStatement.setString(5, vehiculo.getModeloVehiculo());

            preparedStatement.setInt(6, vehiculo.getIdVehiculo());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar vehículo", e);
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String sql = "UPDATE vehiculo SET estado = 'X' WHERE vehiculo_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al eliminar vehículo", e);
        }
    }

    @Override
    public Vehiculo readBy(Integer id) throws Exception {
        Vehiculo vehiculo = null;
        String sql = "SELECT * FROM vehiculo WHERE vehiculo_id = ? AND estado = 'A'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    vehiculo = new Vehiculo();
                    vehiculo.setIdVehiculo(rs.getInt("vehiculo_id"));
                    vehiculo.setIdPlaca(rs.getString("vehiculo_placa"));
                    vehiculo.setTipoVehiculo(rs.getString("vehiculo_tipo"));
                    vehiculo.setIdChofer(rs.getInt("chofer_id"));
                    vehiculo.setMarcaVehiculo(rs.getString("vehiculo_marca"));
                    vehiculo.setModeloVehiculo(rs.getString("vehiculo_modelo"));
                    vehiculo.setEstado(rs.getString("estado"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer vehículo por ID", e);
        }
        return vehiculo;
    }

    public Vehiculo obtenerVehiculoPorChoferId(int idChofer) {
        Vehiculo vehiculo = new Vehiculo();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM vehiculo WHERE chofer_id=? AND estado='A'");
            preparedStatement.setInt(1, idChofer);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                vehiculo.setIdVehiculo(rs.getInt("vehiculo_id"));
                vehiculo.setIdPlaca(rs.getString("vehiculo_placa"));
                vehiculo.setTipoVehiculo(rs.getString("vehiculo_tipo"));
                vehiculo.setIdChofer(rs.getInt("chofer_id"));
                vehiculo.setMarcaVehiculo(rs.getString("vehiculo_marca"));
                vehiculo.setModeloVehiculo(rs.getString("vehiculo_modelo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehiculo;
    }

    public boolean existeVehiculo(int idChofer) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM vehiculo WHERE chofer_id=? AND estado='A'");
            preparedStatement.setInt(1, idChofer);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean existeVehiculoPorPlaca(String id_Placa) throws Exception {
        String sql = "SELECT COUNT(*) FROM vehiculo WHERE vehiculo_placa = ? AND estado='A'";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, id_Placa);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al verificar la placa del vehículo", e);
        }
        return false;
    }
}
