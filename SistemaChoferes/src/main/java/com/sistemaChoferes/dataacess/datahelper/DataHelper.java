package com.sistemaChoferes.dataacess.datahelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataHelper {
    private static final String URL = "jdbc:mysql://localhost:3306/gestion_choferes";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connection = null;

    public static synchronized Connection conectar() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static synchronized void desconectar() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
