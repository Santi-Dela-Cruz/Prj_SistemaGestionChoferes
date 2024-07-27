package com.sistemaChoferes.model.objetoAccesoDatos;

import com.sistemaChoferes.model.clasesEntidades.RegistroPenalizaciones;
import com.sistemaChoferes.model.conexionBaseDatos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistroPenalizacionesDAO {
    private Connection connection;

    public RegistroPenalizacionesDAO() {
        connection = Conexion.conectar();
    }

    public boolean actualizarPenalizaciones(int idChofer) {
        try {
            PreparedStatement checkPenalizaciones = connection
                    .prepareStatement("SELECT n_Infracciones FROM registro_penalizaciones WHERE id_Chofer=?");
            checkPenalizaciones.setInt(1, idChofer);
            ResultSet rs = checkPenalizaciones.executeQuery();
            
            if (rs.next()) {
                int infracciones = rs.getInt("n_Infracciones") + 1;
                PreparedStatement updatePenalizaciones = connection
                        .prepareStatement("UPDATE registro_penalizaciones SET n_Infracciones=?, penalizacion_Chofer=?, estado=? WHERE id_Chofer=?");
                updatePenalizaciones.setInt(1, infracciones);
                updatePenalizaciones.setString(2, infracciones >= 3 ? "Despedido" : "Advertencia");
                updatePenalizaciones.setString(3, infracciones >= 3 ? "X" : "A");
                updatePenalizaciones.setInt(4, idChofer);
                updatePenalizaciones.executeUpdate();
                
                if (infracciones >= 3) {
                PreparedStatement updateChofer = connection.prepareStatement("UPDATE chofer SET estado='X' WHERE id_Chofer=?");
                updateChofer.setInt(1, idChofer);
                updateChofer.executeUpdate();
                
                PreparedStatement updateHuella = connection.prepareStatement("UPDATE huella SET estado='X' WHERE id_Chofer=?");
                updateHuella.setInt(1, idChofer);
                updateHuella.executeUpdate();

                PreparedStatement updateVehiculo = connection.prepareStatement("UPDATE vehiculo SET estado='X' WHERE id_Chofer=?");
                updateVehiculo.setInt(1, idChofer);
                updateVehiculo.executeUpdate();

                PreparedStatement updateRuta = connection.prepareStatement("UPDATE rutas SET estado='X' WHERE id_Chofer=?");
                updateRuta.setInt(1, idChofer);
                updateRuta.executeUpdate();
            }
                
                return infracciones >= 3;
            } else {
                PreparedStatement insertPenalizacion = connection
                        .prepareStatement("INSERT INTO registro_penalizaciones (n_Infracciones, penalizacion_Chofer, id_Chofer, estado) VALUES (?, ?, ?)");
                insertPenalizacion.setInt(1, 1);
                insertPenalizacion.setString(2, "Advertencia");
                insertPenalizacion.setInt(3, idChofer);
                insertPenalizacion.setString(4, "A");
                insertPenalizacion.executeUpdate();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void eliminarPenalizacionesPorChofer(int idChofer) {
        try {
        PreparedStatement preparedStatement = connection
                .prepareStatement("UPDATE registro_penalizaciones SET estado='X' WHERE id_Chofer=?");
        preparedStatement.setInt(1, idChofer);
        preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void agregarRegistroPenalizacion(RegistroPenalizaciones registroPenalizacion) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO registro_penalizaciones (n_Infracciones, penalizacion_Chofer, id_Chofer) VALUES (?, ?, ?)");
            preparedStatement.setInt(1, registroPenalizacion.getNInfracciones());
            preparedStatement.setString(2, registroPenalizacion.getPenalizacionChofer());
            preparedStatement.setInt(3, registroPenalizacion.getIdChofer());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarRegistroPenalizacion(int idRegPen) {
        if (!existeRegistroPenalizacion(idRegPen)) {
        System.out.println("Error: El registro de penalización con el ID " + idRegPen + " no existe.");
        return;
        }
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE registro_penalizaciones SET estado='X' WHERE id_RegPen=?");
            preparedStatement.setInt(1, idRegPen);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarRegistroPenalizacion(RegistroPenalizaciones registroPenalizacion) {
        if (!existeRegistroPenalizacion(registroPenalizacion.getIdRegPen())) {
            System.out.println("Error: El registro de penalización con el ID " + registroPenalizacion.getIdRegPen() + " no existe.");
            return;
        }
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE registro_penalizaciones SET n_Infracciones=?, penalizacion_Chofer=?, id_Chofer=? WHERE id_RegPen=?");
            preparedStatement.setInt(1, registroPenalizacion.getNInfracciones());
            preparedStatement.setString(2, registroPenalizacion.getPenalizacionChofer());
            preparedStatement.setInt(3, registroPenalizacion.getIdChofer());
            preparedStatement.setInt(4, registroPenalizacion.getIdRegPen());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<RegistroPenalizaciones> obtenerTodoRegistroPenalizaciones() {
        List<RegistroPenalizaciones> registros = new ArrayList<RegistroPenalizaciones>();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM registro_penalizaciones WHERE estado='A'");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                RegistroPenalizaciones registroPenalizacion = new RegistroPenalizaciones();
                registroPenalizacion.setIdRegPen(rs.getInt("id_RegPen"));
                registroPenalizacion.setNInfracciones(rs.getInt("n_Infracciones"));
                registroPenalizacion.setPenalizacionChofer(rs.getString("penalizacion_Chofer"));
                registroPenalizacion.setIdChofer(rs.getInt("id_Chofer"));
                registros.add(registroPenalizacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registros;
    }

    public RegistroPenalizaciones obtenerRegistroPenalizacionPorId(int idRegPen) {
        RegistroPenalizaciones registroPenalizacion = new RegistroPenalizaciones();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM registro_penalizaciones WHERE id_RegPen=? AND estado='A'");
            preparedStatement.setInt(1, idRegPen);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                registroPenalizacion.setIdRegPen(rs.getInt("id_RegPen"));
                registroPenalizacion.setNInfracciones(rs.getInt("n_Infracciones"));
                registroPenalizacion.setPenalizacionChofer(rs.getString("penalizacion_Chofer"));
                registroPenalizacion.setIdChofer(rs.getInt("id_Chofer"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registroPenalizacion;
    }

    public boolean existeRegistroPenalizacion(int idRegPen) {
        return obtenerRegistroPenalizacionPorId(idRegPen) != null;
    }
}
