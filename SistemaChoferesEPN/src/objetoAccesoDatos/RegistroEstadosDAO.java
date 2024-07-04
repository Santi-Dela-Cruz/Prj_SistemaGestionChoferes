package objetoAccesoDatos;
import conexionBaseDatos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistroEstadosDAO {
    public boolean registrarIngreso(String idChofer, String estadoChofer, boolean autorizacionChofer) {
        String sql = "INSERT INTO registro_estados (id_Chofer, fecha_Ingreso, hora_Ingreso, estado_Chofer, autorizacion_Chofer) VALUES (?, CURDATE(), CURTIME(), ?, ?)";
        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idChofer);
            stmt.setString(2, estadoChofer);
            stmt.setBoolean(3, autorizacionChofer);
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}


