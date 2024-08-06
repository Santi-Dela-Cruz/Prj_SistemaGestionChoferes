package ui.forms;

import businessLogical.GestorChofer;
import businessLogical.GestorModificaciones;
import dataAccesComponent.dao.ChoferDAO;
import dataAccesComponent.dao.HuellaDAO;
import dataAccesComponent.dao.ModificacionesDAO;
import dataAccesComponent.dao.RutaDAO;
import dataAccesComponent.dao.VehiculoDAO;
import dataAccesComponent.dataHelper.DataHelper;
import dataAccesComponent.entity.Chofer;
import dataAccesComponent.entity.Huella;
import dataAccesComponent.entity.Modificaciones;
import dataAccesComponent.entity.Ruta;
import dataAccesComponent.entity.Vehiculo;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import ui.customerControl.ComponentFactory;
import ui.resources.InterfaceStyle;

public class VisualizarDatos extends JPanel {

    private GestorChofer gestorChofer;
    private ChoferDAO chofeDAO;
    private HuellaDAO huellaDAO;
    private VehiculoDAO vehiculoDAO;
    private RutaDAO rutasDAO;
    private int idChofer;
    private JLabel lbLlenoCedula, lbLlenoEstado, lbLlenoNombres, lbLlenoApellidos,
            lbLlenoTelefono, lbLlenoHuella, lbLlenoPlaca, lbLlenoRuta, lbLlenoFechaIn, lbLlenoFechaMod;
    private JTable tbEstadoChofer;
    private JScrollPane jScrollPane1;
    private Connection connection;
    private Statement st;
    private ResultSet rs;
    private PanelControl panelControl;
    private GestorModificaciones gestorModificaciones;

    public VisualizarDatos(int idChofer, PanelControl panelControl) throws Exception {
        this.idChofer = idChofer;
        this.panelControl = panelControl;
        setupPanel();
        gestorChofer = new GestorChofer();
        chofeDAO = new ChoferDAO();
        huellaDAO = new HuellaDAO();
        vehiculoDAO = new VehiculoDAO();
        rutasDAO = new RutaDAO();
        gestorModificaciones = new GestorModificaciones();
        mostrarDatos(idChofer);
        consultarTabla(idChofer);
    }

    private void setupPanel() {
        setLayout(null);

        // Panel para la tabla
        JPanel tablaPanel = new JPanel();
        tablaPanel.setLayout(null);
        tablaPanel.setBounds(10, 10, 730, 450);
        tablaPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        add(tablaPanel);

        tbEstadoChofer = ComponentFactory.createTable(ComponentFactory.createTableModel(
                new String[] { "Fecha ingreso", "Hora de ingreso", "Estado Chofer", "Autorización Chofer" }));
        jScrollPane1 = ComponentFactory.createScrollPane(tbEstadoChofer);
        jScrollPane1.setBounds(10, 10, 710, 430);
        tablaPanel.add(jScrollPane1);

        // Botón de regresar
        JButton btnRegresar = ComponentFactory.createCircularButton(new ImageIcon(InterfaceStyle.URL_ICON_CANCEL),
                new Color(255, 153, 153),
                new Color(255, 120, 120), new Color(255, 80, 80));
        btnRegresar.setBounds(750, 10, 50, 50);
        btnRegresar.addActionListener(e -> regresar());
        add(btnRegresar);
    }

    private void mostrarDatos(int idc) throws Exception {
        Chofer chofer = chofeDAO.readBy(idc);
        Vehiculo vehiculo = vehiculoDAO.obtenerVehiculoPorChoferId(idc);
        Huella huella = huellaDAO.obtenerHuellaPorChoferId(idc);
        Ruta rutas = rutasDAO.obtenerRutaPorChoferId(idc);
        ModificacionesDAO modificacionesDAO = new ModificacionesDAO();
        Modificaciones primeraModificacion = modificacionesDAO.obtenerPrimeraModificacionPorChoferId(idc);

        if (chofer != null) {
            String cedula = chofer.getIdCedula();
            String nombres = chofer.getNombre();
            String apellidos = chofer.getApellido();
            String telefono = chofer.getTelefono();
            String huellaId = huella != null ? huella.getIdCodigoHuella() : "N/A";
            String placa = vehiculo != null ? vehiculo.getIdPlaca() : "N/A";
            String ruta = rutas != null ? rutas.getNombreRuta() : "N/A";
            String fechaIngreso = primeraModificacion != null
                    ? new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(primeraModificacion.getFechaModificacion())
                    : "N/A";
            String fechaModificacion = fechaIngreso;

            panelControl.setChoferData(cedula, nombres, apellidos, telefono, huellaId, placa, ruta, fechaIngreso,
                    fechaModificacion);
        } else {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos del chofer", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void consultarTabla(int idc) {
        String sql = "SELECT * FROM registro_estado WHERE chofer_id = " + idc;

        try {
            connection = DataHelper.conectar();
            st = connection.createStatement();
            rs = st.executeQuery(sql);

            Object[] datosChoferes = new Object[4];
            DefaultTableModel defTableMod = (DefaultTableModel) tbEstadoChofer.getModel();
            defTableMod.setRowCount(0);

            while (rs.next()) {
                datosChoferes[0] = rs.getString("registro_fecha");
                datosChoferes[1] = rs.getString("registro_hora");
                datosChoferes[2] = rs.getString("estado_chofer");
                datosChoferes[3] = rs.getString("autorizacion");

                defTableMod.addRow(datosChoferes);
            }

            tbEstadoChofer.setModel(defTableMod);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (st != null)
                    st.close();
                if (connection != null)
                    connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void regresar() {
        panelControl.mostrarTabla();
    }

    private void registrarModificacion(String accion, int idChofer) {
        try {
            Modificaciones modificacion = new Modificaciones();
            modificacion.setIdAdministrador(panelControl.obtenerIdAdministrador());
            Date now = new Date();
            modificacion.setFechaModificacion(new java.sql.Date(now.getTime()));
            modificacion.setHoraModificacion(new SimpleDateFormat("HH:mm:ss").format(now));
            modificacion.setAccionAdmin(accion + " - Chofer ID: " + idChofer);
            gestorModificaciones.registrarModificacion(modificacion);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar la modificación: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
