package ui.gui;
import ui.customerControl.ComponentFactory;
import ui.resources.InterfaceStyle;

import javax.swing.*;

import dataAccesComponent.dao.HuellaDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestHuella extends JFrame {

    private JTextField txtIDHuella;
    private JLabel jLabel1, jLabel2, jLIngresarHuella;
    private JPanel jPanel1;
    private JButton btnIngresar;

    public TestHuella() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Test de Huella");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(500, 500);

        // Crear un panel con bordes redondeados usando ComponentFactory
        jPanel1 = ComponentFactory.createRoundedPanel(new Color(102, 153, 255), 20, 20, true);
        jPanel1.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        jLabel2 = ComponentFactory.createLabel("Esperando escaneo");
        jLabel2.setForeground(Color.WHITE);
        jPanel1.add(jLabel2);

        add(jPanel1, BorderLayout.NORTH);

        jLabel1 = new JLabel(new ImageIcon(InterfaceStyle.URL_ICON_SAVE));
        jLabel1.setHorizontalAlignment(JLabel.CENTER);
        add(jLabel1, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        bottomPanel.setOpaque(false);

        jLIngresarHuella = ComponentFactory.createLabel("INGRESE EL CÓDIGO DE HUELLA");
        bottomPanel.add(jLIngresarHuella);

        txtIDHuella = ComponentFactory.createTextField();
        bottomPanel.add(txtIDHuella);

        btnIngresar = ComponentFactory.createButton("INGRESAR", InterfaceStyle.COLOR_FONT_LIGHT);
        btnIngresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });
        bottomPanel.add(btnIngresar);

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
            this.dispose();

            try {
                InterfaceStyle.showMsgError("Abriendo Datos");
            } catch (Exception e) {
                InterfaceStyle.showMsgError("Error al abrir la ventana de datos del chofer: " + e.getMessage());
            }

        } else {
            InterfaceStyle.showMsgError("Huella no encontrada. Verifique el código e intente nuevamente.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TestHuella().setVisible(true);
            }
        });
    }
}

