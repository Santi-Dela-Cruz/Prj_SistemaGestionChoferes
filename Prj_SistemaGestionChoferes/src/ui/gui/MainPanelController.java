package ui.gui;

import javax.swing.*;
import java.awt.*;

public class MainPanelController extends JFrame {

    private JPanel mainPanel;
    private CardLayout cardLayout;

    public MainPanelController() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Sistema de Choferes - Control de Paneles");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Crear los diferentes paneles que se manejarán
        JPanel testHuellaPanel = new TestHuella();
        JPanel datosChoferPanel = new JPanel(); // Este es un placeholder para datos chofer

        // Añadir los paneles al mainPanel con una clave para identificarlos
        mainPanel.add(testHuellaPanel, "TestHuellaPanel");
        mainPanel.add(datosChoferPanel, "DatosChoferPanel");

        // Configurar el panel principal en el JFrame
        add(mainPanel, BorderLayout.CENTER);

        // Mostrar el panel inicial
        cardLayout.show(mainPanel, "TestHuellaPanel");
    }

    public void mostrarDatosChoferPanel(int idHuella) {
        try {
            // Remover cualquier panel previo con la clave "DatosChoferPanel"
            mainPanel.remove(mainPanel.getComponent(1)); // Asumiendo que "DatosChoferPanel" es el segundo panel

            DatosChofer datosChoferPanel = new DatosChofer(idHuella);
            mainPanel.add(datosChoferPanel, "DatosChoferPanel");
            cardLayout.show(mainPanel, "DatosChoferPanel");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al abrir la ventana de datos del chofer: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrarTestHuellaPanel() {
        cardLayout.show(mainPanel, "TestHuellaPanel");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainPanelController().setVisible(true);
        });
    }
}
