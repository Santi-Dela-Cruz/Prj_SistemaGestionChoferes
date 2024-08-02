package com.sistemaChoferes.dataacess.dao;

import com.sistemaChoferes.dataacess.entity.Rutas;
import com.sistemaChoferes.dataacess.datahelper.DataHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RutasDAO implements IDAO<Rutas> {
    private Connection connection;

    public RutasDAO() {
        connection = DataHelper.conectar();
    }

    @Override
    public boolean create(Rutas ruta) throws Exception {
        String sql = "INSERT INTO rutas (nombre_Ruta, id_Chofer, estado) VALUES (?, ?, 'A')";
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
    public List<Rutas> readAll() throws Exception {
        List<Rutas> rutas = new ArrayList<>();
        String sql = "SELECT * FROM rutas WHERE estado = 'A'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                Rutas ruta = new Rutas();
                ruta.setIdRuta(rs.getInt("id_Ruta"));
                ruta.setNombreRuta(rs.getString("nombre_Ruta"));
                ruta.setIdChofer(rs.getInt("id_Chofer"));
                ruta.setEstado(rs.getString("estado"));
                rutas.add(ruta);
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer rutas", e);
        }
        return rutas;
    }

    @Override
    public boolean update(Rutas ruta) throws Exception {
        String sql = "UPDATE rutas SET nombre_Ruta = ?, id_Chofer = ? WHERE id_Ruta = ?";
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
        String sql = "UPDATE rutas SET estado = 'X' WHERE id_Ruta = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al eliminar ruta", e);
        }
    }

    @Override
    public Rutas readBy(Integer id) throws Exception {
        Rutas ruta = null;
        String sql = "SELECT * FROM rutas WHERE id_Ruta = ? AND estado = 'A'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    ruta = new Rutas();
                    ruta.setIdRuta(rs.getInt("id_Ruta"));
                    ruta.setNombreRuta(rs.getString("nombre_Ruta"));
                    ruta.setIdChofer(rs.getInt("id_Chofer"));
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
                    .prepareStatement("SELECT * FROM rutas WHERE id_Chofer=? AND estado='A'");
            preparedStatement.setInt(1, idChofer);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public Rutas obtenerRutaPorChoferId(int idChofer) throws Exception {
        String sql = "SELECT * FROM rutas WHERE id_Chofer = ? AND estado = 'A'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idChofer);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    Rutas ruta = new Rutas();
                    ruta.setIdRuta(rs.getInt("id_Ruta"));
                    ruta.setNombreRuta(rs.getString("nombre_Ruta"));
                    ruta.setIdChofer(rs.getInt("id_Chofer"));
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
