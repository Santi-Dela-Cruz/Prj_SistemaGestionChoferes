package objetoAccesoDatos;
import clasesEntidades.Vehiculo;
import conexionBaseDatos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDAO {

    public List<Vehiculo> getAllVehiculos() throws SQLException {
        List<Vehiculo> vehiculos = new ArrayList<>();
        String sql = "SELECT * FROM vehiculo";

        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Vehiculo vehiculo = new Vehiculo();
                vehiculo.setIdPlaca(rs.getString("id_Placa"));
                vehiculo.setTipoVehiculo(rs.getString("tipo_Vehiculo"));
                vehiculo.setIdChofer(rs.getString("id_Chofer"));
                vehiculos.add(vehiculo);
            }
        } finally {
            Conexion.desconectar();
        }

        return vehiculos;
    }

    public boolean existeVehiculoChoferId(String choferId) {
        String sql = "SELECT COUNT(*) FROM vehiculo WHERE id_Chofer = ?";
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

    public boolean insertarVehiculo(Vehiculo vehiculo) {
        if (existeVehiculoChoferId(vehiculo.getIdChofer())) {
            System.out.println("El chofer ya tiene un vehículo registrado.");
            return false;
        }

        String sql = "INSERT INTO vehiculo (id_Placa, tipo_Vehiculo, id_Chofer) VALUES (?, ?, ?)";
        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vehiculo.getIdPlaca());
            stmt.setString(2, vehiculo.getTipoVehiculo());
            stmt.setString(3, vehiculo.getIdChofer());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Vehiculo getVehiculoChoferId(String idChofer) {
        String sql = "SELECT * FROM vehiculo WHERE id_Chofer = ?";
        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idChofer);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Vehiculo vehiculo = new Vehiculo();
                    vehiculo.setIdPlaca(rs.getString("id_Placa"));
                    vehiculo.setTipoVehiculo(rs.getString("tipo_Vehiculo"));
                    vehiculo.setIdChofer(rs.getString("id_Chofer"));
                    return vehiculo;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

