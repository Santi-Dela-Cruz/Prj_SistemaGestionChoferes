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
        String sql = "SELECT c.chofer_id, c.chofer_cedula, c.chofer_nombre, c.chofer_apellido, v.vehiculo_placa FROM chofer c LEFT JOIN vehiculo v ON c.chofer_id = v.chofer_id AND v.estado = 'A' LEFT JOIN ruta r ON c.chofer_id = r.chofer_id AND r.estado = 'A' WHERE c.estado = 'A';";
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            connection = DataHelper.conectar();
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                defTableMod.addRow(new Object[] {
                        rs.getInt("chofer_id"),
                        rs.getString("chofer_cedula"),
                        rs.getString("chofer_nombre"),
                        rs.getString("chofer_apellido"),
                        rs.getString("vehiculo_placa")
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
