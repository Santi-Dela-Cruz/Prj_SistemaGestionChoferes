package ui.gui;

import businessLogical.GestorIngresoChofer;
import java.awt.*;
import javax.swing.*;
import ui.customerControl.ComponentFactory;
import ui.resources.InterfaceStyle;

public class TestAlcohol extends JPanel {
    private int idChofer;

    public TestAlcohol(int idChofer) {
        this.idChofer = idChofer;
        initComponents();
    }

    private void initComponents() {
        setLayout(null); // Usar Layout nulo para control absoluto sobre los componentes

        // Título principal
        JPanel panelTitulo = ComponentFactory.createRoundedPanel(new Color(17, 76, 95), 20, 20, true);
        panelTitulo.setBounds(200, 40, 400, 40);
        JLabel jLTitle = new JLabel("TEST ALCOHOLEMIA");
        jLTitle.setForeground(Color.WHITE);
        jLTitle.setFont(new Font("Arial", Font.BOLD, 18));
        jLTitle.setHorizontalAlignment(SwingConstants.CENTER);
        panelTitulo.add(jLTitle);
        add(panelTitulo);

        // Imagen central
        JLabel jLAlcoholimetro = new JLabel(new ImageIcon(InterfaceStyle.URL_TEST_ALCOHOL));
        jLAlcoholimetro.setHorizontalAlignment(SwingConstants.CENTER);
        jLAlcoholimetro.setBounds(270, 150, 250, 250);
        add(jLAlcoholimetro);

        // Pregunta
        JLabel jLPregunta = new JLabel("¿Estas Borracho?");
        jLPregunta.setFont(new Font("Arial", Font.PLAIN, 14));
        jLPregunta.setBounds(330, 430, 150, 20);
        add(jLPregunta);

        // Respuesta
        JTextField textRespuesta = ComponentFactory.createTextField();
        textRespuesta.setBounds(330, 460, 150, 30);
        textRespuesta.addActionListener(evt -> {
            String respuesta = textRespuesta.getText().trim().toLowerCase();
            boolean estaBorracho = respuesta.equals("si");

            GestorIngresoChofer registroIngreso = new GestorIngresoChofer();
            registroIngreso.registrarIngreso(idChofer, estaBorracho);

            AutorizacionSalida autorizacionSalida = new AutorizacionSalida(estaBorracho);
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.setContentPane(autorizacionSalida);
            frame.invalidate();
            frame.validate();
        });
        add(textRespuesta);
    }
}
