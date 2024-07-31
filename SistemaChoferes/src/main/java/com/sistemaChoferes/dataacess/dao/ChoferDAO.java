package com.sistemaChoferes.dataacess.dao;

import com.sistemaChoferes.dataacess.entity.Chofer;
import com.sistemaChoferes.dataacess.datahelper.DataHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChoferDAO implements IDAO<Chofer> {
    private Connection connection;

    public ChoferDAO() {
        connection = DataHelper.conectar();
    }

    @Override
    public boolean create(Chofer chofer) throws Exception {
        String sql = "INSERT INTO choferes (id_Cedula, nombre, apellido, telefono, direccion, correo, categoriaLicencia, fechaVenciminetoLicencia, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 'A')";
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
        String sql = "SELECT * FROM choferes WHERE estado = 'A'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                Chofer chofer = new Chofer();
                chofer.setIdChofer(rs.getInt("id_Chofer"));
                chofer.setIdCedula(rs.getString("id_Cedula"));
                chofer.setNombre(rs.getString("nombre"));
                chofer.setApellido(rs.getString("apellido"));
                chofer.setTelefono(rs.getString("telefono"));
                chofer.setDireccion(rs.getString("direccion"));
                chofer.setCorreo(rs.getString("correo"));
                chofer.setCategoriaLicencia(rs.getString("categoriaLicencia"));
                chofer.setFechaVencimientoLicencia(rs.getDate("fechaVenciminetoLicencia"));
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
        String sql = "UPDATE choferes SET id_Cedula = ?, nombre = ?, apellido = ?, telefono = ?, direccion = ?, correo = ?, categoriaLicencia = ?, fechaVenciminetoLicencia = ?, estado = ? WHERE id_Chofer = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, chofer.getIdCedula());
            preparedStatement.setString(2, chofer.getNombre());
            preparedStatement.setString(3, chofer.getApellido());
            preparedStatement.setString(4, chofer.getTelefono());
            preparedStatement.setString(5, chofer.getDireccion());
            preparedStatement.setString(6, chofer.getCorreo());
            preparedStatement.setString(7, chofer.getCategoriaLicencia());
            preparedStatement.setDate(8, new java.sql.Date(chofer.getFechaVencimientoLicencia().getTime()));
            preparedStatement.setString(9, chofer.getEstado());
            preparedStatement.setInt(10, chofer.getIdChofer());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar chofer", e);
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String sql = "UPDATE choferes SET estado = 'X' WHERE id_Chofer = ?";
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
        String sql = "SELECT * FROM choferes WHERE id_Chofer = ? AND estado = 'A'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    chofer = new Chofer();
                    chofer.setIdChofer(rs.getInt("id_Chofer"));
                    chofer.setIdCedula(rs.getString("id_Cedula"));
                    chofer.setNombre(rs.getString("nombre"));
                    chofer.setApellido(rs.getString("apellido"));
                    chofer.setTelefono(rs.getString("telefono"));
                    chofer.setDireccion(rs.getString("direccion"));
                    chofer.setCorreo(rs.getString("correo"));
                    chofer.setCategoriaLicencia(rs.getString("categoriaLicencia"));
                    chofer.setFechaVencimientoLicencia(rs.getDate("fechaVenciminetoLicencia"));
                    chofer.setEstado(rs.getString("estado"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer chofer por ID", e);
        }
        return chofer;
    }
    
    public boolean existeChoferPorCedula(String idCedula) throws Exception {
        String sql = "SELECT COUNT(*) FROM choferes WHERE id_Cedula = ? AND estado = 'A'";
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
        String sql = "SELECT COUNT(*) FROM choferes WHERE telefono = ? AND estado = 'A'";
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
        String sql = "SELECT COUNT(*) FROM choferes WHERE correo = ? AND estado = 'A'";
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
        String sql = "SELECT * FROM choferes WHERE id_Cedula = ? AND estado = 'A'";
        Chofer chofer = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, idCedula);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    chofer = new Chofer();
                    chofer.setIdChofer(rs.getInt("id_Chofer"));
                    chofer.setIdCedula(rs.getString("id_Cedula"));
                    chofer.setNombre(rs.getString("nombre"));
                    chofer.setApellido(rs.getString("apellido"));
                    chofer.setTelefono(rs.getString("telefono"));
                    chofer.setDireccion(rs.getString("direccion"));
                    chofer.setCorreo(rs.getString("correo"));
                    chofer.setCategoriaLicencia(rs.getString("categoriaLicencia"));
                    chofer.setFechaVencimientoLicencia(rs.getDate("fechaVenciminetoLicencia"));
                    chofer.setEstado(rs.getString("estado"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al obtener chofer por cédula", e);
        }

        return chofer;
    }
}
