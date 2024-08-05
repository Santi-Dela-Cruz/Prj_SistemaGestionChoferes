package dataAccesComponent.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataAccesComponent.IDAO;
import dataAccesComponent.dataHelper.DataHelper;
import dataAccesComponent.entity.Administrador;

public class AdministradorDAO implements IDAO<Administrador> {
    private Connection connection;

    public AdministradorDAO() {
        connection = DataHelper.conectar();
    }

    @Override
    public boolean create(Administrador admin) throws Exception {
        String sql = "INSERT INTO administrador (usuarioAdmin, nombreAdmin, apellidoAdmin, correoAdmin, contrasenaAdmin, fechaIngreso, horaIngreso, estadoAdmin, cargoAdmin, telefono, direccion, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'A')";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, admin.getUsuario());
            preparedStatement.setString(2, admin.getNombres());
            preparedStatement.setString(3, admin.getApellidos());
            preparedStatement.setString(4, admin.getCorreoAdmin());
            preparedStatement.setString(5, admin.getContrasenaAdmin());
            preparedStatement.setDate(6, new java.sql.Date(admin.getFechaIngreso().getTime()));
            preparedStatement.setTime(7, admin.getHoraIngreso());
            preparedStatement.setString(8, admin.getEstadoAdmin());
            preparedStatement.setString(9, admin.getCargoAdmin());
            preparedStatement.setString(10, admin.getTelefono());
            preparedStatement.setString(11, admin.getDireccion());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al crear administrador", e);
        }
    }

    @Override
    public List<Administrador> readAll() throws Exception {
        List<Administrador> admins = new ArrayList<>();
        String sql = "SELECT * FROM administrador WHERE estado = 'A'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                Administrador admin = new Administrador();
                admin.setIdAdministrador(rs.getInt("id"));
                admin.setUsuario(rs.getString("usuarioAdmin"));
                admin.setNombres(rs.getString("nombreAdmin"));
                admin.setApellidos(rs.getString("apellidoAdmin"));
                admin.setCorreoAdmin(rs.getString("correoAdmin"));
                admin.setContrasenaAdmin(rs.getString("contrasenaAdmin"));
                admin.setFechaIngreso(rs.getDate("fechaIngreso"));
                admin.setHoraIngreso(rs.getTime("horaIngreso"));
                admin.setEstadoAdmin(rs.getString("estadoAdmin"));
                admin.setCargoAdmin(rs.getString("cargoAdmin"));
                admin.setTelefono(rs.getString("telefono"));
                admin.setDireccion(rs.getString("direccion"));
                admins.add(admin);
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer administradores", e);
        }
        return admins;
    }

    @Override
    public boolean update(Administrador admin) throws Exception {
        String sql = "UPDATE administrador SET usuarioAdmin = ?, nombreAdmin = ?, apellidoAdmin = ?, correoAdmin = ?, contrasenaAdmin = ?, fechaIngreso = ?, horaIngreso = ?, estadoAdmin = ?, cargoAdmin = ?, telefono = ?, direccion = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, admin.getUsuario());
            preparedStatement.setString(2, admin.getNombres());
            preparedStatement.setString(3, admin.getApellidos());
            preparedStatement.setString(4, admin.getCorreoAdmin());
            preparedStatement.setString(5, admin.getContrasenaAdmin());
            preparedStatement.setDate(6, new java.sql.Date(admin.getFechaIngreso().getTime()));
            preparedStatement.setTime(7, admin.getHoraIngreso());
            preparedStatement.setString(8, admin.getEstadoAdmin());
            preparedStatement.setString(9, admin.getCargoAdmin());
            preparedStatement.setString(10, admin.getTelefono());
            preparedStatement.setString(11, admin.getDireccion());
            preparedStatement.setInt(12, admin.getIdAdministrador());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar administrador", e);
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String sql = "UPDATE administrador SET estado = 'X' WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al eliminar administrador", e);
        }
    }

    @Override
    public Administrador readBy(Integer id) throws Exception {
        Administrador admin = null;
        String sql = "SELECT * FROM administrador WHERE id = ? AND estado = 'A'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    admin = new Administrador();
                    admin.setIdAdministrador(rs.getInt("id"));
                    admin.setUsuario(rs.getString("usuarioAdmin"));
                    admin.setNombres(rs.getString("nombreAdmin"));
                    admin.setApellidos(rs.getString("apellidoAdmin"));
                    admin.setCorreoAdmin(rs.getString("correoAdmin"));
                    admin.setContrasenaAdmin(rs.getString("contrasenaAdmin"));
                    admin.setFechaIngreso(rs.getDate("fechaIngreso"));
                    admin.setHoraIngreso(rs.getTime("horaIngreso"));
                    admin.setEstadoAdmin(rs.getString("estadoAdmin"));
                    admin.setCargoAdmin(rs.getString("cargoAdmin"));
                    admin.setTelefono(rs.getString("telefono"));
                    admin.setDireccion(rs.getString("direccion"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer administrador por ID", e);
        }
        return admin;
    }

    public Administrador getAdministrador(String usuarioCorreo, String contrasena) throws Exception {
        Administrador admin = null;
        String sql = "SELECT * FROM administrador WHERE (usuarioAdmin = ? OR correoAdmin = ?) AND contrasenaAdmin = ? AND estado = 'A'";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, usuarioCorreo);
            preparedStatement.setString(2, usuarioCorreo);
            preparedStatement.setString(3, contrasena);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    admin = new Administrador();
                    admin.setIdAdministrador(rs.getInt("id"));
                    admin.setUsuario(rs.getString("usuarioAdmin"));
                    admin.setCorreoAdmin(rs.getString("correoAdmin"));
                    admin.setContrasenaAdmin(rs.getString("contrasenaAdmin"));
                    admin.setEstadoAdmin(rs.getString("estadoAdmin"));
                    admin.setCargoAdmin(rs.getString("cargoAdmin"));
                    admin.setNombres(rs.getString("nombreAdmin"));
                    admin.setApellidos(rs.getString("apellidoAdmin"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al obtener el administrador: " + e.getMessage(), e);
        }

        return admin;
    }

}
