package ui.gui;

import java.awt.*;
import javax.swing.*;
import ui.customerControl.ComponentFactory;

public class AutorizacionSalida extends JPanel {

    private JLabel jLResEst, jLResApr;

    public AutorizacionSalida(boolean estaBorracho) {
        initComponents();
        mostrarResultado(estaBorracho);

        // Ejecutar el cambio de panel en un hilo separado
        new Thread(() -> {
            try {
                for (int i = 10; i > 0; i--) {
                    System.out.println("Cambio de panel en: " + i + " segundos");
                    Thread.sleep(1000); // Espera 1 segundo
                }
        
                // Cambiar de panel después de 10 segundos
                SwingUtilities.invokeLater(() -> {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(AutorizacionSalida.this);
                    if (frame instanceof MainPanelController) {
                        // Llamar a reiniciarCiclo para cambiar al panel TestHuella
                        ((MainPanelController) frame).reiniciarCiclo();
        
                        // Asegurarse de que el panel de AutorizacionSalida ya no esté en el mainPanel
                        Container parent = AutorizacionSalida.this.getParent();
                        if (parent != null) {
                            parent.remove(AutorizacionSalida.this);
                        }
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void initComponents() {
        setLayout(null); // Usar Layout nulo para control absoluto sobre los componentes

        // Título principal
        JLabel jLTitle = new JLabel("APROBACION DE SALIDA");
        jLTitle.setFont(new Font("Arial", Font.BOLD, 18));
        jLTitle.setBounds(180, 20, 400, 30);
        add(jLTitle);

        // Panel para "Validación"
        JPanel panelValidacion = ComponentFactory.createRoundedPanel(new Color(255, 182, 193), 20, 20, true);
        panelValidacion.setBounds(260, 60, 180, 50);
        JLabel jLValidacion = new JLabel("Validacion");
        jLValidacion.setFont(new Font("Arial", Font.BOLD, 16));
        jLValidacion.setHorizontalAlignment(SwingConstants.CENTER);
        panelValidacion.add(jLValidacion);
        add(panelValidacion);

        // Estado del chofer
        JLabel jLEstado = new JLabel("Estado");
        jLEstado.setFont(new Font("Arial", Font.PLAIN, 14));
        jLEstado.setBounds(50, 140, 250, 30);
        add(jLEstado);

        jLResEst = new JLabel("Estado etílico");
        jLResEst.setFont(new Font("Arial", Font.PLAIN, 14));
        jLResEst.setBounds(50, 170, 300, 30);
        add(jLResEst);

        // Aprobación de la salida
        JLabel jLAprobacion = new JLabel("Aprobación");
        jLAprobacion.setFont(new Font("Arial", Font.PLAIN, 14));
        jLAprobacion.setBounds(50, 230, 250, 30);
        add(jLAprobacion);

        jLResApr = new JLabel("Salida denegada");
        jLResApr.setFont(new Font("Arial", Font.PLAIN, 14));
        jLResApr.setBounds(50, 260, 300, 30);
        jLResApr.setForeground(new Color(139, 0, 0)); // Color rojo para denegación
        add(jLResApr);
    }

    private void mostrarResultado(boolean estaBorracho) {
        jLResEst.setText(estaBorracho ? "Borracho" : "Sobrio");
        jLResApr.setText(estaBorracho ? "Salida Denegada" : "Salida Autorizada");
        jLResApr.setForeground(estaBorracho ? new Color(139, 0, 0) : new Color(0, 128, 0)); // Rojo si está borracho, verde si no
    }
}
