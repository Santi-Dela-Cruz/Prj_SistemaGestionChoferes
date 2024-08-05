package ui.gui;

import dataAccesComponent.dao.ChoferDAO;
import dataAccesComponent.dao.RutaDAO;
import dataAccesComponent.dao.VehiculoDAO;
import dataAccesComponent.entity.Chofer;
import dataAccesComponent.entity.Ruta;
import dataAccesComponent.entity.Vehiculo;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.*;
import ui.customerControl.ComponentFactory;

public class DatosChofer extends JPanel {
    private int idChofer;
    private ChoferDAO chofeDAO;
    private VehiculoDAO vehiculoDAO;
    private RutaDAO rutasDAO;

    // Añadir referencias directas a los componentes
    private JLabel jLNombreCompleto, jLCedula, jLRuta, jLPlaca, jLHoraIngreso, jLFechaIngreso;

    public DatosChofer(int idc) throws Exception {
        this.idChofer = idc;
        initComponents();
        chofeDAO = new ChoferDAO();
        vehiculoDAO = new VehiculoDAO();
        rutasDAO = new RutaDAO();
        mostrarDatos(idChofer);
    }

    private void initComponents() {
        setLayout(null); // Usar Layout nulo para control absoluto sobre los componentes

        // Título principal
        JLabel jLabel1 = new JLabel("DATOS GENERALES DEL TRANSPORTISTA");
        jLabel1.setFont(new Font("Arial", Font.BOLD, 18));
        jLabel1.setBounds(180, 20, 400, 30);
        add(jLabel1);

        // Panel para "Datos"
        JPanel panelDatos = ComponentFactory.createRoundedPanel(new Color(255, 182, 193), 20, 20, true);
        panelDatos.setBounds(260, 60, 180, 50);
        JLabel jLabel2 = new JLabel("Datos");
        jLabel2.setFont(new Font("Arial", Font.BOLD, 16));
        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        panelDatos.add(jLabel2);
        add(panelDatos);

        // Etiquetas y campos de texto
        jLNombreCompleto = addLabelAndField("Nombre transportista", 50, 140, 250, 30, 50, 170, 300, 30);
        jLCedula = addLabelAndField("CD transportista", 50, 200, 250, 30, 50, 230, 300, 30);
        jLRuta = addLabelAndField("Ruta", 50, 260, 250, 30, 50, 290, 300, 30);
        jLPlaca = addLabelAndField("Placa del transporte", 50, 320, 250, 30, 50, 350, 300, 30);

        // Hora de llegada y salida
        jLHoraIngreso = addLabelAndField("Hora de llegada", 370, 140, 200, 30, 370, 170, 200, 30);
        jLFechaIngreso = addLabelAndField("Hora de salida", 370, 200, 200, 30, 370, 230, 200, 30);

        // Botón "Test Alcohol"
        JButton jButton1 = ComponentFactory.createButton("TEST ALCOHOLEMIA", new Color(70, 130, 180));
        jButton1.setBounds(420, 300, 200, 50);
        jButton1.addActionListener(evt -> jButton1ActionPerformed(evt));
        add(jButton1);
    }

    private JLabel addLabelAndField(String labelText, int labelX, int labelY, int labelW, int labelH, int fieldX, int fieldY, int fieldW, int fieldH) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setBounds(labelX, labelY, labelW, labelH);
        add(label);

        JPanel roundedPanel = ComponentFactory.createRoundedPanel(new Color(192, 192, 192), 20, 20, true);
        roundedPanel.setBounds(fieldX, fieldY, fieldW, fieldH);
        JLabel fieldLabel = new JLabel("Texto aquí");
        fieldLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        roundedPanel.add(fieldLabel);
        add(roundedPanel);

        return fieldLabel; // Devolver la referencia del JLabel para actualizar los datos luego
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        TestAlcohol testAlcohol = new TestAlcohol(idChofer);
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.setContentPane(testAlcohol);
        frame.invalidate();
        frame.validate();
    }

    private void mostrarDatos(int idChofer) throws Exception {
        Chofer chofer = chofeDAO.readBy(idChofer);
        Vehiculo vehiculo = vehiculoDAO.obtenerVehiculoPorChoferId(idChofer);
        Ruta rutas = rutasDAO.obtenerRutaPorChoferId(idChofer);

        if (chofer != null && vehiculo != null && rutas != null) {
            // Asignar valores reales a los campos
            jLNombreCompleto.setText(chofer.getNombre() + " " + chofer.getApellido());
            jLCedula.setText(chofer.getIdCedula());
            jLRuta.setText(rutas.getNombreRuta());
            jLPlaca.setText(vehiculo.getIdPlaca());

            LocalDate fechaIngreso = LocalDate.now();
            LocalTime horaIngreso = LocalTime.now();

            jLFechaIngreso.setText(fechaIngreso.toString());
            jLHoraIngreso.setText(horaIngreso.toString());

        } else {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos del chofer, vehículo o ruta", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
