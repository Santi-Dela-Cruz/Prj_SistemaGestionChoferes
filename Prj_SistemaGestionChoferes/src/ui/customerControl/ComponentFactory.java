package ui.customerControl;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ComponentFactory {

    public static JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        return button;
    }

    public static JLabel createLabel(String text) {
        return new JLabel(text);
    }

    public static JTable createTable(DefaultTableModel model) {
        JTable table = new JTable(model);
        table.setSelectionBackground(new Color(184, 207, 229));
        table.setFillsViewportHeight(true);
        return table;
    }

    public static JScrollPane createScrollPane(JTable table) {
        return new JScrollPane(table);
    }

    public static DefaultTableModel createTableModel(String[] columnNames) {
        return new DefaultTableModel(new Object[][] {}, columnNames) {
            boolean[] canEdit = new boolean[] {
                    false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
    }

    public static JTextField createTextField() {
        JTextField textField = new JTextField();
        Border roundedBorder = BorderFactory.createLineBorder(Color.BLACK);
        textField.setBorder(roundedBorder);
        return textField;
    }

    // Método para crear un botón circular
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
}
