package ui.gui;


import javax.swing.*;

import ui.customerControl.ComponentFactory;

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
        JPanel testHuellaPanel = createTestHuellaPanel();
        JPanel otroPanel = createOtroPanel();  // Puedes agregar otros paneles aquí

        // Añadir los paneles al mainPanel con una clave para identificarlos
        mainPanel.add(testHuellaPanel, "TestHuellaPanel");
        mainPanel.add(otroPanel, "OtroPanel");

        // Configurar el panel principal en el JFrame
        add(mainPanel, BorderLayout.CENTER);

        // Crear un menú o barra de herramientas para cambiar entre paneles
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Navegación");
        JMenuItem miTestHuella = new JMenuItem("Test Huella");
        JMenuItem miOtroPanel = new JMenuItem("Otro Panel");

        // Agregar acciones para cambiar de panel
        miTestHuella.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "TestHuellaPanel");
            }
        });

        miOtroPanel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "OtroPanel");
            }
        });

        menu.add(miTestHuella);
        menu.add(miOtroPanel);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    // Crear el panel de TestHuella
    private JPanel createTestHuellaPanel() {
        return (JPanel) new TestHuella().getContentPane();
    }

    // Crear otro panel (ejemplo)
    private JPanel createOtroPanel() {
        JPanel panel = ComponentFactory.createRoundedPanel(new Color(255, 204, 204), 20, 20, true);
        panel.add(ComponentFactory.createLabel("Este es otro panel"));
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainPanelController().setVisible(true);
            }
        });
    }
}

