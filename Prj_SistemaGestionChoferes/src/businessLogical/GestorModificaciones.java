package businessLogical;

import dataAccesComponent.dataHelper.DataHelper;
import dataAccesComponent.entity.Modificaciones;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GestorModificaciones {

    public void registrarModificacion(Modificaciones modificacion) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataHelper.conectar();
            if (connection == null || connection.isClosed()) {
                throw new SQLException("No se pudo establecer una conexión con la base de datos.");
            }

            String sql = "INSERT INTO modificacion (admin_id, modificacion_fecha, modificacion_hora, modificacion_accion, chofer_id_modificado) "
                    + "VALUES (?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, modificacion.getIdAdministrador());
            statement.setDate(2, (Date) modificacion.getFechaModificacion());
            statement.setString(3, modificacion.getHoraModificacion());
            statement.setString(4, modificacion.getAccionAdmin());
            statement.setInt(5, modificacion.getIdChoferModificacion());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al registrar la modificación: " + e.getMessage());
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null && !connection.isClosed())
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
