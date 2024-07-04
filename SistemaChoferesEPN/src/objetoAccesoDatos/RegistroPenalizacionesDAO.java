package objetoAccesoDatos;
import conexionBaseDatos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistroPenalizacionesDAO {
    public boolean actualizarPenalizaciones(String idChofer, String penalizacionChofer) {
        String sqlSelect = "SELECT n_Infracciones FROM registro_penalizaciones WHERE id_Chofer = ?";
        String sqlInsert = "INSERT INTO registro_penalizaciones (id_Chofer, n_Infracciones, penalizacion_Chofer) VALUES (?, ?, ?)";
        String sqlUpdate = "UPDATE registro_penalizaciones SET n_Infracciones = n_Infracciones + 1, penalizacion_Chofer = ? WHERE id_Chofer = ?";
        try (Connection conn = Conexion.conectar();
             PreparedStatement stmtSelect = conn.prepareStatement(sqlSelect)) {
            stmtSelect.setString(1, idChofer);
            ResultSet rs = stmtSelect.executeQuery();
            if (rs.next()) {
                int nInfracciones = rs.getInt("n_Infracciones");
                if (nInfracciones >= 3) {
                    return true;
                }
                try (PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate)) {
                    stmtUpdate.setString(1, penalizacionChofer);
                    stmtUpdate.setString(2, idChofer);
                    stmtUpdate.executeUpdate();
                    return false;
                }
            } else {
                try (PreparedStatement stmtInsert = conn.prepareStatement(sqlInsert)) {
                    stmtInsert.setString(1, idChofer);
                    stmtInsert.setInt(2, 1);
                    stmtInsert.setString(3, penalizacionChofer);
                    stmtInsert.executeUpdate();
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean actualizarPenalizaciones(String idChofer) {
        String sqlSelect = "SELECT n_Infracciones FROM registro_penalizaciones WHERE id_Chofer = ?";
        String sqlInsert = "INSERT INTO registro_penalizaciones (id_Chofer, n_Infracciones, penalizacion_Chofer) VALUES (?, ?, ?)";
        String sqlUpdate = "UPDATE registro_penalizaciones SET n_Infracciones = ?, penalizacion_Chofer = ? WHERE id_Chofer = ?";
        try (Connection conn = Conexion.conectar();
             PreparedStatement stmtSelect = conn.prepareStatement(sqlSelect)) {
            stmtSelect.setString(1, idChofer);
            ResultSet rs = stmtSelect.executeQuery();
            if (rs.next()) {
                int nInfracciones = rs.getInt("n_Infracciones");
                nInfracciones++;
                String penalizacion = nInfracciones >= 3 ? "Despedido" : "Infracción";
                try (PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate)) {
                    stmtUpdate.setInt(1, nInfracciones);
                    stmtUpdate.setString(2, penalizacion);
                    stmtUpdate.setString(3, idChofer);
                    stmtUpdate.executeUpdate();
                    return nInfracciones >= 3; // Retornar true si el chofer está despedido
                }
            } else {
                try (PreparedStatement stmtInsert = conn.prepareStatement(sqlInsert)) {
                    stmtInsert.setString(1, idChofer);
                    stmtInsert.setInt(2, 1);
                    stmtInsert.setString(3, "Infracción");
                    stmtInsert.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
