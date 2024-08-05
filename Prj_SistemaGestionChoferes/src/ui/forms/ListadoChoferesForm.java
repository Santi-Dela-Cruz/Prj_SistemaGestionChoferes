package ui.forms;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import ui.customerControl.ComponentFactory;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import dataAccesComponent.dataHelper.DataHelper;

public class ListadoChoferesForm extends JPanel {

    private JTable tbChoferes;
    private DefaultTableModel defTableMod;

    public ListadoChoferesForm() {
        setupPanel();
        consultarTabla();
    }

    private void setupPanel() {
        setLayout(new BorderLayout());
        String[] columnNames = { "ID", "Cedula", "Nombre", "Apellido", "Placa" };
        defTableMod = ComponentFactory.createTableModel(columnNames);
        tbChoferes = ComponentFactory.createTable(defTableMod);
        add(ComponentFactory.createScrollPane(tbChoferes), BorderLayout.CENTER);
    }

    public JTable getTable() {
        return tbChoferes;
    }

    private void consultarTabla() {
        String sql = "SELECT c.id_Chofer, c.id_Cedula, c.nombre, c.apellido, v.id_Placa FROM choferes c LEFT JOIN vehiculo v ON c.id_Chofer = v.id_Chofer AND v.estado = 'A' LEFT JOIN rutas r ON c.id_Chofer = r.id_Chofer AND r.estado = 'A' WHERE c.estado = 'A';";
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            connection = DataHelper.conectar();
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                defTableMod.addRow(new Object[] {
                        rs.getInt("id_Chofer"),
                        rs.getString("id_Cedula"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("id_Placa")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (st != null)
                    st.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void actualizarTabla() {
        defTableMod.setRowCount(0);
        consultarTabla();
    }
}
