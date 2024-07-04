package objetoAccesoDatos;
import clasesEntidades.Chofer;
import conexionBaseDatos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChoferDAO {
    public List<Chofer> getAllChoferes() throws SQLException {
        List<Chofer> choferes = new ArrayList<>();
        String sql = "SELECT * FROM choferes";

        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Chofer chofer = new Chofer();
                chofer.setId(rs.getString("id"));
                chofer.setNombre(rs.getString("nombre"));
                chofer.setApellido(rs.getString("apellido"));
                chofer.setTelefono(rs.getString("telefono"));
                choferes.add(chofer);
            }
        } finally {
            Conexion.desconectar();
        }

        return choferes;
    }

    public boolean insertChofer(Chofer chofer) {
        if (existeChoferId(chofer.getId())) {
            System.out.println("El ID del chofer ya existe.");
            return false;
        }

        String sql = "INSERT INTO choferes (id, nombre, apellido, telefono) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, chofer.getId());
            stmt.setString(2, chofer.getNombre());
            stmt.setString(3, chofer.getApellido());
            stmt.setString(4, chofer.getTelefono());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean existeChoferId(String id) {
        String sql = "SELECT COUNT(*) FROM choferes WHERE id = ?";
        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Chofer getChoferById(String idChofer) {
        String sql = "SELECT * FROM choferes WHERE id = ?";
        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idChofer);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Chofer chofer = new Chofer();
                    chofer.setId(rs.getString("id"));
                    chofer.setNombre(rs.getString("nombre"));
                    chofer.setApellido(rs.getString("apellido"));
                    chofer.setTelefono(rs.getString("telefono"));
                    return chofer;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

