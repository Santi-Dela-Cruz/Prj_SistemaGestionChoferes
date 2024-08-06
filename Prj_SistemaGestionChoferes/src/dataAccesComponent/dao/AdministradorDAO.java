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
        String sql = "INSERT INTO administrador (admin_usuario, admin_nombre, admin_apellido, admin_correo, admin_contrasena, admin_fecha_ingreso, admin_hora_ingreso, admin_cargo, admin_telefono, admin_direccion, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, admin.getUsuario());
            preparedStatement.setString(2, admin.getNombres());
            preparedStatement.setString(3, admin.getApellidos());
            preparedStatement.setString(4, admin.getCorreoAdmin());
            preparedStatement.setString(5, admin.getContrasenaAdmin());
            preparedStatement.setDate(6, new java.sql.Date(admin.getFechaIngreso().getTime()));
            preparedStatement.setTime(7, admin.getHoraIngreso());
            preparedStatement.setString(8, admin.getCargoAdmin());
            preparedStatement.setString(9, admin.getTelefono());
            preparedStatement.setString(10, admin.getDireccion());
            preparedStatement.setString(11, admin.getEstado());

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
                admin.setIdAdministrador(rs.getInt("admin_id"));
                admin.setUsuario(rs.getString("admin_usuario"));
                admin.setNombres(rs.getString("admin_nombre"));
                admin.setApellidos(rs.getString("admin_apellido"));
                admin.setCorreoAdmin(rs.getString("admin_correo"));
                admin.setContrasenaAdmin(rs.getString("admin_contrasena"));
                admin.setFechaIngreso(rs.getDate("admin_fecha_ingreso"));
                admin.setHoraIngreso(rs.getTime("admin_hora_ingreso"));
                admin.setCargoAdmin(rs.getString("admin_cargo"));
                admin.setTelefono(rs.getString("admin_telefono"));
                admin.setDireccion(rs.getString("admin_direccion"));
                admin.setEstado(rs.getString("estado"));
                admins.add(admin);
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer administradores", e);
        }
        return admins;
    }

    @Override
    public boolean update(Administrador admin) throws Exception {
        String sql = "UPDATE administrador SET admin_usuario = ?, admin_nombre = ?, admin_apellido = ?, admin_correo = ?, admin_contrasena = ?, admin_fecha_ingreso = ?, admin_hora_ingreso = ?, admin_cargo = ?, admin_telefono = ?, admin_direccion = ?, estado = ? WHERE admin_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, admin.getUsuario());
            preparedStatement.setString(2, admin.getNombres());
            preparedStatement.setString(3, admin.getApellidos());
            preparedStatement.setString(4, admin.getCorreoAdmin());
            preparedStatement.setString(5, admin.getContrasenaAdmin());
            preparedStatement.setDate(6, new java.sql.Date(admin.getFechaIngreso().getTime()));
            preparedStatement.setTime(7, admin.getHoraIngreso());
            preparedStatement.setString(8, admin.getCargoAdmin());
            preparedStatement.setString(9, admin.getTelefono());
            preparedStatement.setString(10, admin.getDireccion());
            preparedStatement.setString(11, admin.getEstado());
            preparedStatement.setInt(12, admin.getIdAdministrador());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar administrador", e);
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String sql = "UPDATE administrador SET estado = 'X' WHERE admin_id = ?";
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
        String sql = "SELECT * FROM administrador WHERE admin_id = ? AND estado = 'A'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    admin = new Administrador();
                    admin.setIdAdministrador(rs.getInt("admin_id"));
                    admin.setUsuario(rs.getString("admin_usuario"));
                    admin.setNombres(rs.getString("admin_nombre"));
                    admin.setApellidos(rs.getString("admin_apellido"));
                    admin.setCorreoAdmin(rs.getString("admin_correo"));
                    admin.setContrasenaAdmin(rs.getString("admin_contrasena"));
                    admin.setFechaIngreso(rs.getDate("admin_fecha_ingreso"));
                    admin.setHoraIngreso(rs.getTime("admin_hora_ingreso"));
                    admin.setCargoAdmin(rs.getString("admin_cargo"));
                    admin.setTelefono(rs.getString("admin_telefono"));
                    admin.setDireccion(rs.getString("admin_direccion"));
                    admin.setEstado(rs.getString("estado"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer administrador por ID", e);
        }
        return admin;
    }

    public Administrador getAdministrador(String usuarioCorreo, String contrasena) throws Exception {
        Administrador admin = null;
        String sql = "SELECT * FROM administrador WHERE (admin_usuario = ? OR admin_correo = ?) AND admin_contrasena = ? AND estado = 'A'";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, usuarioCorreo);
            preparedStatement.setString(2, usuarioCorreo);
            preparedStatement.setString(3, contrasena);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    admin = new Administrador();
                    admin.setIdAdministrador(rs.getInt("admin_id"));
                    admin.setUsuario(rs.getString("admin_usuario"));
                    admin.setCorreoAdmin(rs.getString("admin_correo"));
                    admin.setContrasenaAdmin(rs.getString("admin_contrasena"));
                    admin.setCargoAdmin(rs.getString("admin_cargo"));
                    admin.setNombres(rs.getString("admin_nombre"));
                    admin.setApellidos(rs.getString("admin_apellido"));
                    admin.setEstado(rs.getString("estado"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al obtener el administrador: " + e.getMessage(), e);
        }

        return admin;
    }
}
