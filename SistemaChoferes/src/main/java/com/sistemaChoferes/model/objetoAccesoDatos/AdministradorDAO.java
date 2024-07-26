package com.sistemaChoferes.model.objetoAccesoDatos;

import com.sistemaChoferes.model.clasesEntidades.Administrador;
import com.sistemaChoferes.model.conexionBaseDatos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministradorDAO {
    
    private Connection connection;
    
    public AdministradorDAO(){
        connection = Conexion.conectar();
    }
    
    public Administrador obtenerAdminUsuario(String usuarioCorreo, String contrasena) {
        Administrador admin = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM administrador WHERE (usuarioAdmin=? OR correoAdmin=?) AND contrasenaAdmin=?");
            preparedStatement.setString(1, usuarioCorreo);
            preparedStatement.setString(2, usuarioCorreo);
            preparedStatement.setString(3, contrasena);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                admin = new Administrador();
                admin.setIdAdministrador(rs.getInt("id"));
                admin.setNombres(rs.getString("nombreAdmin"));
                admin.setApellidos(rs.getString("apellidoAdmin"));
                admin.setCorreoAdmin(rs.getString("correoAdmin"));
                admin.setCargoAdmin(rs.getString("cargoAdmin"));
                admin.setEstadoAdmin(rs.getString("estadoAdmin"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }  
}