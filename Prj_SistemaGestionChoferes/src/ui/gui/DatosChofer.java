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
import java.time.format.DateTimeFormatter;

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

        // Panel para "Datos"
        JPanel panelDatos = ComponentFactory.createRoundedPanel(new Color(17, 76, 95), 20, 20, true);
        panelDatos.setBounds(200, 40, 400, 40);
        JLabel jLabel2 = new JLabel("DATOS GENERALES DEL TRANSPORTISTA");
        jLabel2.setForeground(Color.WHITE);
        jLabel2.setFont(new Font("Arial", Font.BOLD, 18));
        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        panelDatos.add(jLabel2);
        add(panelDatos);

        // Etiquetas y campos de texto
        jLNombreCompleto = addLabelAndField("Nombre transportista", 50, 140, 250, 30, 50, 170, 300, 30);
        jLCedula = addLabelAndField("CD transportista", 50, 220, 250, 30, 50, 250, 300, 30);
        jLRuta = addLabelAndField("Ruta", 50, 300, 250, 30, 50, 330, 300, 30);
        jLPlaca = addLabelAndField("Placa del transporte", 50, 380, 250, 30, 50, 410, 300, 30);

        // Hora de llegada y salida
        jLHoraIngreso = addLabelAndField("Hora de llegada", 450, 140, 250, 30, 450, 170, 300, 30);
        jLFechaIngreso = addLabelAndField("Fecha de llegada", 450, 220, 250, 30, 450, 250, 300, 30);

        // Botón "Test Alcohol"
        JButton jButton1 = ComponentFactory.createChamferedButton(
                "TEST ALCOHOLEMIA",
                new Color(70, 130, 180), // Color normal
                new Color(100, 149, 237), // Color al hacer hover
                new Color(0, 105, 180), // Color al presionar
                40 // Radio de las esquinas
        );
        jButton1.setBounds(520, 405, 150, 40);
        jButton1.setContentAreaFilled(false); // Desactiva el área de contenido para evitar problemas con el fondo
        jButton1.setOpaque(false); // Desactiva la opacidad para que el fondo no se superponga
        jButton1.setHorizontalAlignment(SwingConstants.CENTER); // Asegura que el texto esté centrado
        jButton1.addActionListener(evt -> jButton1ActionPerformed(evt));
        add(jButton1);
    }

    private JLabel addLabelAndField(String labelText, int labelX, int labelY, int labelW, int labelH, int fieldX,
            int fieldY, int fieldW, int fieldH) {
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

            // Formatear la hora para que se muestre como "HH:mm:ss"
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String horaFormateada = horaIngreso.format(formatter);

            jLFechaIngreso.setText(fechaIngreso.toString());
            jLHoraIngreso.setText(horaFormateada);

        } else {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos del chofer, vehículo o ruta", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
