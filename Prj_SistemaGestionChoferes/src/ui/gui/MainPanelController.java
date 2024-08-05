package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        JPanel datosChoferPanel = new JPanel();  // Este es un placeholder para datos chofer

        // Añadir los paneles al mainPanel con una clave para identificarlos
        mainPanel.add(testHuellaPanel, "TestHuellaPanel");
        mainPanel.add(datosChoferPanel, "DatosChoferPanel");

        // Configurar el panel principal en el JFrame
        add(mainPanel, BorderLayout.CENTER);

        // Crear un menú o barra de herramientas para cambiar entre paneles
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Navegación");
        JMenuItem miTestHuella = new JMenuItem("Test Huella");

        // Agregar acciones para cambiar de panel
        miTestHuella.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "TestHuellaPanel");
            }
        });

        menu.add(miTestHuella);
        menuBar.add(menu);
        setJMenuBar(menuBar);

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
            JOptionPane.showMessageDialog(this, "Error al abrir la ventana de datos del chofer: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrarTestHuellaPanel() {
        cardLayout.show(mainPanel, "TestHuellaPanel");
    }

    public void reiniciarCiclo() {
        SwingUtilities.invokeLater(() -> {
            // Remover todos los paneles
            mainPanel.removeAll();
    
            // Crear de nuevo el panel inicial TestHuella
            JPanel testHuellaPanel = new TestHuella();
            mainPanel.add(testHuellaPanel, "TestHuellaPanel");
    
            // Asegurar que se muestra el panel TestHuella
            cardLayout.show(mainPanel, "TestHuellaPanel");
    
            // Revalidar y repintar el panel principal para asegurar que se actualiza correctamente
            mainPanel.revalidate();
            mainPanel.repaint();
    
            // Forzar un redibujado del JFrame
            setVisible(false);
            setVisible(true);
    
            System.out.println("Panel reiniciado a TestHuella.");
        });
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainPanelController().setVisible(true);
        });
    }
}
