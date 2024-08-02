package com.sistemaChoferes.dataacess.dao;

import com.sistemaChoferes.dataacess.entity.Vehiculo;
import com.sistemaChoferes.dataacess.datahelper.DataHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDAO implements IDAO<Vehiculo> {
    private Connection connection;

    public VehiculoDAO() {
        connection = DataHelper.conectar();
    }

    @Override
    public boolean create(Vehiculo vehiculo) throws Exception {
        String sql = "INSERT INTO vehiculo (id_Placa, tipo_Vehiculo, id_Chofer, marcaVehiculo, modeloVehiculo, estado) VALUES (?, ?, ?, ?, ?, 'A')";
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
                vehiculo.setIdVehiculo(rs.getInt("id_Vehiculo"));
                vehiculo.setIdPlaca(rs.getString("id_Placa"));
                vehiculo.setTipoVehiculo(rs.getString("tipo_Vehiculo"));
                vehiculo.setIdChofer(rs.getInt("id_Chofer"));
                vehiculo.setMarcaVehiculo(rs.getString("marcaVehiculo"));
                vehiculo.setModeloVehiculo(rs.getString("modeloVehiculo"));
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
        String sql = "UPDATE vehiculo SET id_Placa = ?, tipo_Vehiculo = ?, id_Chofer = ?, marcaVehiculo = ?, modeloVehiculo = ? WHERE id_Vehiculo = ?";
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
        String sql = "UPDATE vehiculo SET estado = 'X' WHERE id_Vehiculo = ?";
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
        String sql = "SELECT * FROM vehiculo WHERE id_Vehiculo = ? AND estado = 'A'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    vehiculo = new Vehiculo();
                    vehiculo.setIdVehiculo(rs.getInt("id_Vehiculo"));
                    vehiculo.setIdPlaca(rs.getString("id_Placa"));
                    vehiculo.setTipoVehiculo(rs.getString("tipo_Vehiculo"));
                    vehiculo.setIdChofer(rs.getInt("id_Chofer"));
                    vehiculo.setMarcaVehiculo(rs.getString("marcaVehiculo"));
                    vehiculo.setModeloVehiculo(rs.getString("modeloVehiculo"));
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
                    .prepareStatement("SELECT * FROM vehiculo WHERE id_Chofer=? AND estado='A'");
            preparedStatement.setInt(1, idChofer);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                vehiculo.setIdVehiculo(rs.getInt("id_Vehiculo"));
                vehiculo.setIdPlaca(rs.getString("id_Placa"));
                vehiculo.setTipoVehiculo(rs.getString("tipo_Vehiculo"));
                vehiculo.setIdChofer(rs.getInt("id_Chofer"));
                vehiculo.setMarcaVehiculo(rs.getString("marcaVehiculo"));
                vehiculo.setModeloVehiculo(rs.getString("modeloVehiculo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehiculo;
    }

    public boolean existeVehiculo(int idChofer) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM vehiculo WHERE id_Chofer=? AND estado='A'");
            preparedStatement.setInt(1, idChofer);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean existeVehiculoPorPlaca(String id_Placa) throws Exception {
        String sql = "SELECT COUNT(*) FROM vehiculo WHERE id_Placa = ? AND estado='A'";

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
