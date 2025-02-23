package dataAccesComponent.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataAccesComponent.IDAO;
import dataAccesComponent.dataHelper.DataHelper;
import dataAccesComponent.entity.Chofer;

public class ChoferDAO implements IDAO<Chofer> {
    private Connection connection;

    public ChoferDAO() {
        connection = DataHelper.conectar();
    }

    @Override
    public boolean create(Chofer chofer) throws Exception {
        String sql = "INSERT INTO chofer (chofer_cedula, chofer_nombre, chofer_apellido, chofer_telefono, chofer_direccion, chofer_correo, chofer_categoria_licencia, chofer_fecha_vencimiento_licencia, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 'A')";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, chofer.getIdCedula());
            preparedStatement.setString(2, chofer.getNombre());
            preparedStatement.setString(3, chofer.getApellido());
            preparedStatement.setString(4, chofer.getTelefono());
            preparedStatement.setString(5, chofer.getDireccion());
            preparedStatement.setString(6, chofer.getCorreo());
            preparedStatement.setString(7, chofer.getCategoriaLicencia());
            preparedStatement.setDate(8, new java.sql.Date(chofer.getFechaVencimientoLicencia().getTime()));

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al crear chofer", e);
        }
    }

    @Override
    public List<Chofer> readAll() throws Exception {
        List<Chofer> choferes = new ArrayList<>();
        String sql = "SELECT * FROM chofer WHERE estado = 'A'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                Chofer chofer = new Chofer();
                chofer.setIdChofer(rs.getInt("chofer_id"));
                chofer.setIdCedula(rs.getString("chofer_cedula"));
                chofer.setNombre(rs.getString("chofer_nombre"));
                chofer.setApellido(rs.getString("chofer_apellido"));
                chofer.setTelefono(rs.getString("chofer_telefono"));
                chofer.setDireccion(rs.getString("chofer_direccion"));
                chofer.setCorreo(rs.getString("chofer_correo"));
                chofer.setCategoriaLicencia(rs.getString("chofer_categoria_licencia"));
                chofer.setFechaVencimientoLicencia(rs.getDate("chofer_fecha_vencimiento_licencia"));
                chofer.setEstado(rs.getString("estado"));
                choferes.add(chofer);
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer choferes", e);
        }
        return choferes;
    }

    @Override
    public boolean update(Chofer chofer) throws Exception {
        String sql = "UPDATE chofer SET chofer_cedula = ?, chofer_nombre = ?, chofer_apellido = ?, chofer_telefono = ?, chofer_direccion = ?, chofer_correo = ?, chofer_categoria_licencia = ?, chofer_fecha_vencimiento_licencia = ? WHERE chofer_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, chofer.getIdCedula());
            preparedStatement.setString(2, chofer.getNombre());
            preparedStatement.setString(3, chofer.getApellido());
            preparedStatement.setString(4, chofer.getTelefono());
            preparedStatement.setString(5, chofer.getDireccion());
            preparedStatement.setString(6, chofer.getCorreo());
            preparedStatement.setString(7, chofer.getCategoriaLicencia());
            preparedStatement.setDate(8, new java.sql.Date(chofer.getFechaVencimientoLicencia().getTime()));
            preparedStatement.setInt(9, chofer.getIdChofer());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar chofer", e);
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String sql = "UPDATE chofer SET estado = 'X' WHERE chofer_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al eliminar chofer", e);
        }
    }

    @Override
    public Chofer readBy(Integer id) throws Exception {
        Chofer chofer = null;
        String sql = "SELECT * FROM chofer WHERE chofer_id = ? AND estado = 'A'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    chofer = new Chofer();
                    chofer.setIdChofer(rs.getInt("chofer_id"));
                    chofer.setIdCedula(rs.getString("chofer_cedula"));
                    chofer.setNombre(rs.getString("chofer_nombre"));
                    chofer.setApellido(rs.getString("chofer_apellido"));
                    chofer.setTelefono(rs.getString("chofer_telefono"));
                    chofer.setDireccion(rs.getString("chofer_direccion"));
                    chofer.setCorreo(rs.getString("chofer_correo"));
                    chofer.setCategoriaLicencia(rs.getString("chofer_categoria_licencia"));
                    chofer.setFechaVencimientoLicencia(rs.getDate("chofer_fecha_vencimiento_licencia"));
                    chofer.setEstado(rs.getString("estado"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer chofer por ID", e);
        }
        return chofer;
    }

    public boolean existeChoferPorCedula(String idCedula) throws Exception {
        String sql = "SELECT COUNT(*) FROM chofer WHERE chofer_cedula = ? AND estado = 'A'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, idCedula);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al verificar la cédula del chofer", e);
        }
        return false;
    }

    public boolean existeChoferPorTelefono(String telefono) throws Exception {
        String sql = "SELECT COUNT(*) FROM chofer WHERE chofer_telefono = ? AND estado = 'A'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, telefono);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al verificar el teléfono del chofer", e);
        }
        return false;
    }

    public boolean existeChoferPorCorreo(String correo) throws Exception {
        String sql = "SELECT COUNT(*) FROM chofer WHERE chofer_correo = ? AND estado = 'A'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, correo);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al verificar el correo del chofer", e);
        }
        return false;
    }

    public Chofer obtenerChoferPorCedula(String idCedula) throws Exception {
        String sql = "SELECT * FROM chofer WHERE chofer_cedula = ? AND estado = 'A'";
        Chofer chofer = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, idCedula);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    chofer = new Chofer();
                    chofer.setIdChofer(rs.getInt("chofer_id"));
                    chofer.setIdCedula(rs.getString("chofer_cedula"));
                    chofer.setNombre(rs.getString("chofer_nombre"));
                    chofer.setApellido(rs.getString("chofer_apellido"));
                    chofer.setTelefono(rs.getString("chofer_telefono"));
                    chofer.setDireccion(rs.getString("chofer_direccion"));
                    chofer.setCorreo(rs.getString("chofer_correo"));
                    chofer.setCategoriaLicencia(rs.getString("chofer_categoria_licencia"));
                    chofer.setFechaVencimientoLicencia(rs.getDate("chofer_fecha_vencimiento_licencia"));
                    chofer.setEstado(rs.getString("estado"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al obtener chofer por cédula", e);
        }

        return chofer;
    }
}
