package ui.customerControl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class ComponentFactory {

    // Método para crear un botón con texto y color de fondo personalizado
    public static JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        return button;
    }

    // Método para crear una etiqueta con texto personalizado
    public static JLabel createLabel(String text) {
        return new JLabel(text);
    }

    // Método para crear una tabla con un modelo de tabla personalizado
    public static JTable createTable(DefaultTableModel model) {
        JTable table = new JTable(model);
        table.setSelectionBackground(new Color(184, 207, 229));
        table.setFillsViewportHeight(true);
        return table;
    }

    // Método para crear un JScrollPane para una tabla
    public static JScrollPane createScrollPane(JTable table) {
        return new JScrollPane(table);
    }

    // Método para crear un modelo de tabla con nombres de columnas personalizados
    public static DefaultTableModel createTableModel(String[] columnNames) {
        return new DefaultTableModel(new Object[][]{}, columnNames) {
            boolean[] canEdit = new boolean[]{
                    false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
    }

    // Método para crear un JTextField con un borde personalizado
    public static JTextField createTextField() {
        JTextField textField = new JTextField();
        Border roundedBorder = BorderFactory.createLineBorder(Color.BLACK);
        textField.setBorder(roundedBorder);
        return textField;
    }

    // Método para crear un botón circular con icono y colores personalizados
    public static JButton createCircularButton(Icon icon, Color normalColor, Color hoverColor, Color pressedColor) {
        JButton button = new JButton(icon) {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isPressed()) {
                    g.setColor(pressedColor);
                } else if (getModel().isRollover()) {
                    g.setColor(hoverColor);
                } else {
                    g.setColor(normalColor);
                }
                g.fillOval(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(getBackground().darker());
                g.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(50, 50); // Tamaño del botón
            }
        };

        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setOpaque(false);

        // Cambiar color al interactuar con el botón
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(normalColor);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                button.setBackground(pressedColor);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setBackground(hoverColor);
            }
        });

        return button;
    }

    // Método para crear un panel con bordes redondeados
    public static JPanel createRoundedPanel(Color bgColor, int arcWidth, int arcHeight) {
        return new RoundedPanel(bgColor, arcWidth, arcHeight);
    }

    // Método para crear un panel con bordes redondeados y cambiar color/transparencia
    public static JPanel createRoundedPanel(Color bgColor, int arcWidth, int arcHeight, boolean isTransparent) {
        RoundedPanel panel = new RoundedPanel(bgColor, arcWidth, arcHeight);
        if (isTransparent) {
            panel.setBackground(new Color(bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue(), 150));
        }
        return panel;
    }

    // Clase interna para definir el panel redondeado
    static class RoundedPanel extends JPanel {
        private Color backgroundColor;
        private int cornerRadius;

        public RoundedPanel(Color bgColor, int arcWidth, int arcHeight) {
            this.backgroundColor = bgColor;
            this.cornerRadius = arcWidth;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Dimension arcs = new Dimension(cornerRadius, cornerRadius);
            int width = getWidth();
            int height = getHeight();
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Dibujar el panel redondeado con los bordes
            if (backgroundColor != null) {
                graphics.setColor(backgroundColor);
            } else {
                graphics.setColor(getBackground());
            }
            graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); // Fondo
            graphics.setColor(getForeground());
            graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); // Bordes
        }
    }
}
