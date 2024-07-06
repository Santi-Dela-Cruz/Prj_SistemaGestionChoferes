package conexionBaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:mysql://monorail.proxy.rlwy.net:54924/Gestion_Choferes_RDB";
    private static final String USER = "root";
    private static final String PASSWORD = "jXZnZxfAqSlbFjeZjFiwljbFdkcAYTlQ";
    private static Connection connection = null;

    public static Connection conectar() {
        if (connection == null || estaCerrada()) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void desconectar() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean estaCerrada() {
        try {
            return connection == null || connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }
}


