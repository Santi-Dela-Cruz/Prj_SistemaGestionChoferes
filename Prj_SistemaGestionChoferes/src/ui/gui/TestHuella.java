package ui.gui;

import ui.customerControl.ComponentFactory;
import ui.resources.InterfaceStyle;

import javax.swing.*;
import dataAccesComponent.dao.HuellaDAO;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TestHuella extends JPanel {

    private JTextField txtIDHuella;
    private JLabel jLabel1, jLabel2, jLIngresarHuella;
    private JPanel jPanel1;

    public TestHuella() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Panel superior: Crear un panel con bordes redondeados usando ComponentFactory
        jPanel1 = ComponentFactory.createRoundedPanel(new Color(102, 153, 255), 20, 20, true);
        jPanel1.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        jPanel1.setPreferredSize(new Dimension(300, 60)); // Cambiar el ancho del panel

        jLabel2 = ComponentFactory.createLabel("Esperando escaneo");
        jLabel2.setForeground(Color.WHITE);
        jPanel1.add(jLabel2);

        // Contenedor para centrar el panel en la parte superior
        JPanel topPanelContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        topPanelContainer.add(jPanel1);
        topPanelContainer.setOpaque(false); // Fondo transparente
        add(topPanelContainer, BorderLayout.NORTH);

        // Imagen central: Crear un panel central con la imagen de la huella
        jLabel1 = new JLabel(new ImageIcon(InterfaceStyle.URL_ICON_SAVE));
        jLabel1.setHorizontalAlignment(JLabel.CENTER);
        jLabel1.setPreferredSize(new Dimension(100, 100)); // Cambiar tamaño de la imagen
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(jLabel1);
        centerPanel.setOpaque(false); // Fondo transparente
        add(centerPanel, BorderLayout.CENTER);

        // Panel inferior: Campo de texto
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        bottomPanel.setOpaque(false);

        jLIngresarHuella = ComponentFactory.createLabel("INGRESE EL CÓDIGO DE HUELLA");
        bottomPanel.add(jLIngresarHuella);

        txtIDHuella = ComponentFactory.createTextField();
        txtIDHuella.setPreferredSize(new Dimension(200, 30)); // Cambiar tamaño del campo de texto
        txtIDHuella.addActionListener(evt -> btnIngresarActionPerformed(evt)); // Asignar la acción al presionar Enter
        bottomPanel.add(txtIDHuella);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void btnIngresarActionPerformed(ActionEvent evt) {
        String codigoHuella = txtIDHuella.getText();

        if (codigoHuella.isEmpty()) {
            InterfaceStyle.showMsgError("Por favor, ingrese un código de huella.");
            return;
        }

        HuellaDAO huellaDAO = new HuellaDAO();
        Integer idHuella = null;

        try {
            idHuella = huellaDAO.getHuellaIdByCode(codigoHuella);
        } catch (Exception e) {
            InterfaceStyle.showMsgError("Ocurrió un error al verificar la huella: " + e.getMessage());
            return;
        }

        if (idHuella != null) {
            InterfaceStyle.showMsg("Huella encontrada en la base de datos.");

            // Acceder al frame principal para cambiar el panel
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (frame instanceof MainPanelController) {
                ((MainPanelController) frame).mostrarDatosChoferPanel(idHuella);
            }
        } else {
            InterfaceStyle.showMsgError("Huella no encontrada. Verifique el código e intente nuevamente.");
        }
    }
}
