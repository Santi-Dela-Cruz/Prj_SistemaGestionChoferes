package dataAccesComponent;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import dataAccesComponent.dataHelper.DataHelper;

public class LimpiaTablas {

    private void truncarTabla(String nombreTabla) {
        String sql = "TRUNCATE TABLE " + nombreTabla;
        try (Connection conn = DataHelper.conectar();
                Statement stmt = conn.createStatement()) {
            stmt.execute("SET FOREIGN_KEY_CHECKS = 0");
            stmt.execute(sql);
            stmt.execute("SET FOREIGN_KEY_CHECKS = 1");
            System.out.println("Tabla " + nombreTabla + " truncada");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void limpiarTodasTablas() {
        truncarTabla("administrador");
        truncarTabla("chofer");
        truncarTabla("huella_digital");
        truncarTabla("modificacion");
        truncarTabla("penalizacion");
        truncarTabla("registro_estado");
        truncarTabla("ruta");
        truncarTabla("vehiculo");
    }
}
