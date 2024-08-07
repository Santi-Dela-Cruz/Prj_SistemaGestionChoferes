package ui.resources;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public abstract class InterfaceStyle {

    public static final Color COLOR_FONT = new Color(100, 100, 50); // (218, 8, 40)
    public static final Color COLOR_FONT_LIGHT = new Color(100, 100, 100);
    public static final Color COLOR_CURSOR = Color.black;
    public static final Color COLOR_BORDER = Color.lightGray;

    public static final Font FONT = new Font("JetBrains Mono", Font.PLAIN, 14);
    public static final Font FONT_BOLD = new Font("JetBrains Mono", Font.BOLD | Font.PLAIN, 14);
    public static final Font FONT_SMALL = new Font("JetBrains Mono", Font.PLAIN | Font.PLAIN, 10);

    public static final int ALIGNMENT_LEFT = SwingConstants.LEFT;
    public static final int ALIGNMENT_RIGHT = SwingConstants.RIGHT;
    public static final int ALIGNMENT_CENTER = SwingConstants.CENTER;

    public static final Cursor CURSOR_HAND = new Cursor(Cursor.HAND_CURSOR);
    public static final Cursor CURSOR_DEFAULT = new Cursor(Cursor.DEFAULT_CURSOR);

    public static final URL URL_EPN_CAMPUS = InterfaceStyle.class.getResource("/ui/resources/Images/epn.jpg");
    public static final URL URL_ICON_CANCEL = InterfaceStyle.class.getResource("/ui/resources/Icons/cancelIcon.png");
    public static final URL URL_ICON_SAVE = InterfaceStyle.class.getResource("/ui/resources/Icons/saveIcon.png");
    public static final URL URL_ICON_UPDATE = InterfaceStyle.class.getResource("/ui/resources/Icons/updateIcon.png");
    public static final URL URL_ICON_ADD = InterfaceStyle.class.getResource("/ui/resources/Icons/addIcon.png");
    public static final URL URL_ICON_EDIT = InterfaceStyle.class.getResource("/ui/resources/Icons/editIcon.png");
    public static final URL URL_ICON_VIEW = InterfaceStyle.class.getResource("/ui/resources/Icons/viewIcon.png");
    public static final URL URL_ICON_DELETE = InterfaceStyle.class.getResource("/ui/resources/Icons/deleteIcon.png");
    public static final URL URL_ICON_EXIT = InterfaceStyle.class.getResource("/ui/resources/Icons/exitIcon.png");
    public static final URL URL_HUELLA = InterfaceStyle.class.getResource("/ui/resources/Images/Huella2.png");

    public static final CompoundBorder createBorderRect() {
        return BorderFactory.createCompoundBorder(
                new LineBorder(Color.lightGray),
                new EmptyBorder(5, 5, 5, 5));
    }

    public static void showMsg(String msg) {
        JOptionPane.showMessageDialog(null, msg, "🚗 Registro de Choferes", JOptionPane.INFORMATION_MESSAGE);
    }

    // Mensaje de error
    public static void showMsgError(String msg) {
        JOptionPane.showMessageDialog(null, msg, "💀 Error: Por eso el shampoo tiene instrucciones🤨🤨",
                JOptionPane.ERROR_MESSAGE);
    }

    // Confirmación Sí/No
    public static boolean showConfirmYesNo(String msg) {
        return (JOptionPane.showConfirmDialog(null, msg, "⚠️ Confirmación",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
    }

    // Mensaje de advertencia
    public static void showWarningMsg(String msg) {
        JOptionPane.showMessageDialog(null, msg, "🤡 Advertencia", JOptionPane.WARNING_MESSAGE);
    }

    // Confirmación con más opciones (Sí, No, Cancelar)
    public static int showConfirmYesNoCancel(String msg) {
        return JOptionPane.showConfirmDialog(null, msg, "❓ ¿Qué deseas hacer?", JOptionPane.YES_NO_CANCEL_OPTION);
    }
}
