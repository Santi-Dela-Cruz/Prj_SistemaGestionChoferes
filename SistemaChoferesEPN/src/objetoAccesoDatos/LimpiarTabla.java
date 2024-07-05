package objetoAccesoDatos;

import conexionBaseDatos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LimpiarTabla {
    
    private void limpiarTabla(String nombreTabla) {
        String sql = "DELETE FROM " + nombreTabla;
        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
            System.out.println("Tabla " + nombreTabla + " limpiada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void limpiarTodasTablas() {
        limpiarTabla("registro_penalizaciones");
        limpiarTabla("registro_estados");
        limpiarTabla("rutas");
        limpiarTabla("vehiculo");
        limpiarTabla("huella");
        limpiarTabla("choferes");
    }
}
