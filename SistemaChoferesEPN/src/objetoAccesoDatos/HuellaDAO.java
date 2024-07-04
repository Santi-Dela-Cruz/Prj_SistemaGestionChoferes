package objetoAccesoDatos;
import clasesEntidades.Huella;
import conexionBaseDatos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HuellaDAO {
    public List<Huella> getAllHuellas() throws SQLException {
        List<Huella> huellas = new ArrayList<>();
        String sql = "SELECT * FROM huella";

        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Huella huella = new Huella();
                huella.setIdHuella(rs.getString("id_Huella"));
                huella.setIdChofer(rs.getString("id_Chofer"));
                huellas.add(huella);
            }
        } finally {
            Conexion.desconectar();
        }

        return huellas;
    }

    public boolean existsHuellaByChoferId(String choferId) {
        String sql = "SELECT COUNT(*) FROM huella WHERE id_Chofer = ?";
        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, choferId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertHuella(Huella huella) {
        if (existsHuellaByChoferId(huella.getIdChofer())) {
            System.out.println("El chofer ya tiene una huella registrada.");
            return false;
        }

        String sql = "INSERT INTO huella (id_Huella, id_Chofer) VALUES (?, ?)";
        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, huella.getIdHuella());
            stmt.setString(2, huella.getIdChofer());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Huella getHuellaById(String idHuella) {
        String sql = "SELECT * FROM huella WHERE id_Huella = ?";
        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idHuella);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Huella huella = new Huella();
                    huella.setIdHuella(rs.getString("id_Huella"));
                    huella.setIdChofer(rs.getString("id_Chofer"));
                    return huella;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

