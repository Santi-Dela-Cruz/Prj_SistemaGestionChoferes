package objetoAccesoDatos;
import clasesEntidades.Rutas;
import conexionBaseDatos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RutasDAO {
    public boolean existsRutaById(String idRuta) {
        String sql = "SELECT COUNT(*) FROM rutas WHERE id_Ruta = ?";
        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idRuta);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertRuta(Rutas ruta) {
        String sql = "INSERT INTO rutas (id_Ruta, nombre_Ruta, id_Chofer) VALUES (?, ?, ?)";
        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ruta.getIdRuta());
            stmt.setString(2, ruta.getNombreRuta());
            stmt.setString(3, ruta.getIdChofer());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Rutas> getAllRutas() {
        List<Rutas> rutas = new ArrayList<>();
        String sql = "SELECT * FROM rutas";
        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Rutas ruta = new Rutas();
                ruta.setIdRuta(rs.getString("id_Ruta"));
                ruta.setNombreRuta(rs.getString("nombre_Ruta"));
                ruta.setIdChofer(rs.getString("id_Chofer"));
                rutas.add(ruta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rutas;
    }

    public Rutas getRutaByChoferId(String idChofer) {
        String sql = "SELECT * FROM rutas WHERE id_Chofer = ?";
        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idChofer);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Rutas ruta = new Rutas();
                    ruta.setNombreRuta(rs.getString("nombre_Ruta"));
                    ruta.setIdChofer(rs.getString("id_Chofer"));
                    return ruta;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
