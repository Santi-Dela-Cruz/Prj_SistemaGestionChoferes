package ui.forms;

import dataAccesComponent.dao.AdministradorDAO;
import dataAccesComponent.entity.Administrador;
import ui.resources.InterfaceStyle;

import javax.swing.*;
import java.awt.*;

public class LoginAdministradorForm extends JFrame {
    private AdministradorDAO administradorDAO;
    private int xMouse, yMouse;

    private JPanel pnlMinimizar;
    private JLabel lblExit1;
    private JPanel pnlExit;
    private JLabel lblExit;
    private JPanel pnlBarra;
    private JPanel jPanel1;
    private JLabel lbTitle1;
    private JLabel lbTitle2;
    private JLabel lbUserCorreo;
    private JTextField txtUserCorreo;
    private JLabel lbContrasena;
    private JPasswordField pswContrasena;
    private JLabel lbOlvidaste;
    private JButton btnIniciar;
    private JLabel lbAyuda;
    private JSeparator jSeparator1;
    private JSeparator jSeparator2;
    private JLabel jLabel1;

    public LoginAdministradorForm() {
        setupFrame();
        setLocationRelativeTo(null);
        administradorDAO = new AdministradorDAO();
    }

    private void setupFrame() {
        setTitle("Login Administrador");
        setSize(700, 460); // Tamaño ajustado del JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setLayout(null);

        pnlMinimizar = new JPanel();
        pnlMinimizar.setBackground(Color.WHITE);
        pnlMinimizar.setBounds(630, 0, 30, 30); // Posición ajustada del botón minimizar
        pnlMinimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setExtendedState(JFrame.ICONIFIED);
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlMinimizar.setBackground(InterfaceStyle.COLOR_BORDER);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlMinimizar.setBackground(Color.white);
            }
        });

        lblExit1 = new JLabel("--");
        lblExit1.setFont(InterfaceStyle.FONT);
        lblExit1.setForeground(Color.BLACK);
        pnlMinimizar.add(lblExit1);
        add(pnlMinimizar);

        pnlExit = new JPanel();
        pnlExit.setBackground(Color.WHITE);
        pnlExit.setBounds(660, 0, 40, 30); // Posición ajustada del botón cerrar
        pnlExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.exit(0);
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlExit.setBackground(Color.RED);
                lblExit.setForeground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlExit.setBackground(Color.WHITE);
                lblExit.setForeground(Color.BLACK);
            }
        });

        lblExit = new JLabel("X");
        lblExit.setFont(InterfaceStyle.FONT);
        lblExit.setForeground(Color.BLACK);
        pnlExit.add(lblExit);
        add(pnlExit);

        pnlBarra = new JPanel();
        pnlBarra.setBackground(Color.WHITE);
        pnlBarra.setBounds(0, 0, 630, 30); // Ancho ajustado del panel superior
        pnlBarra.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                int x = evt.getXOnScreen();
                int y = evt.getYOnScreen();
                setLocation(x - xMouse, y - yMouse);
            }
        });
        pnlBarra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                xMouse = evt.getX();
                yMouse = evt.getY();
            }
        });
        add(pnlBarra);

        jPanel1 = new JPanel();
        jPanel1.setBackground(Color.WHITE);
        jPanel1.setLayout(null);
        jPanel1.setBounds(0, 30, 500, 430); // Tamaño y posición del panel izquierdo

        lbTitle1 = new JLabel("SISTEMA DE GESTIÓN DE CHOFERES");
        lbTitle1.setFont(InterfaceStyle.FONT_BOLD);
        lbTitle1.setBounds(100, 14, 400, 20); // Posición ajustada del título
        jPanel1.add(lbTitle1);

        lbTitle2 = new JLabel("Administración Escuela Politécnica Nacional");
        lbTitle2.setFont(InterfaceStyle.FONT);
        lbTitle2.setForeground(Color.BLACK);
        lbTitle2.setBounds(90, 30, 400, 20); // Posición ajustada del subtítulo
        jPanel1.add(lbTitle2);

        lbUserCorreo = new JLabel("Usuario o correo electrónico:");
        lbUserCorreo.setFont(InterfaceStyle.FONT);
        lbUserCorreo.setForeground(Color.BLACK);
        lbUserCorreo.setBounds(50, 80, 300, 20); // Posición ajustada del label
        jPanel1.add(lbUserCorreo);

        txtUserCorreo = new JTextField();
        txtUserCorreo.setFont(InterfaceStyle.FONT);
        txtUserCorreo.setForeground(new Color(102, 102, 102));
        txtUserCorreo.setBorder(null);
        txtUserCorreo.setBounds(50, 100, 400, 25); // Posición ajustada del campo de texto
        jPanel1.add(txtUserCorreo);

        lbContrasena = new JLabel("Contraseña:");
        lbContrasena.setFont(InterfaceStyle.FONT);
        lbContrasena.setForeground(Color.BLACK);
        lbContrasena.setBounds(50, 150, 300, 20); // Posición ajustada del label contraseña
        jPanel1.add(lbContrasena);

        pswContrasena = new JPasswordField();
        pswContrasena.setFont(InterfaceStyle.FONT);
        pswContrasena.setForeground(new Color(102, 102, 102));
        pswContrasena.setBorder(null);
        pswContrasena.setBounds(50, 170, 400, 25); // Posición ajustada del campo contraseña
        jPanel1.add(pswContrasena);

        lbOlvidaste = new JLabel("¿Olvidaste tu contraseña?");
        lbOlvidaste.setFont(InterfaceStyle.FONT_SMALL);
        lbOlvidaste.setForeground(new Color(153, 153, 153));
        lbOlvidaste.setBounds(50, 220, 250, 20); // Posición ajustada del link de contraseña
        jPanel1.add(lbOlvidaste);

        btnIniciar = new JButton("Iniciar Sesión");
        btnIniciar.setFont(InterfaceStyle.FONT_BOLD);
        btnIniciar.setForeground(Color.WHITE);
        btnIniciar.setBackground(new Color(51, 153, 255));
        btnIniciar.setBorder(InterfaceStyle.createBorderRect());
        btnIniciar.setBorderPainted(false);
        btnIniciar.setBounds(180, 270, 140, 40); // Posición ajustada del botón
        btnIniciar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnIniciar.setBackground(new java.awt.Color(0, 154, 250));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnIniciar.setBackground(new java.awt.Color(51, 153, 255));
            }
        });
        btnIniciar.addActionListener(evt -> btnIniciarActionPerformed());
        jPanel1.add(btnIniciar);

        lbAyuda = new JLabel("¿Necesitas ayuda?");
        lbAyuda.setFont(InterfaceStyle.FONT);
        lbAyuda.setForeground(new Color(153, 153, 153));
        lbAyuda.setBounds(190, 320, 200, 20); // Posición ajustada del texto de ayuda
        jPanel1.add(lbAyuda);

        jSeparator1 = new JSeparator();
        jSeparator1.setBackground(Color.BLACK);
        jSeparator1.setBounds(50, 130, 400, 5); // Ancho ajustado de la línea separadora
        jPanel1.add(jSeparator1);

        jSeparator2 = new JSeparator();
        jSeparator2.setBackground(Color.BLACK);
        jSeparator2.setBounds(50, 200, 400, 5); // Ancho ajustado de la línea separadora
        jPanel1.add(jSeparator2);

        add(jPanel1);

        jLabel1 = new JLabel(new ImageIcon(InterfaceStyle.URL_EPN_CAMPUS));
        jLabel1.setText("");
        jLabel1.setBorder(null);
        jLabel1.setBounds(350, 30, 470, 470); // Posición ajustada de la imagen
        add(jLabel1);
    }

    private void btnIniciarActionPerformed() {
        String usuarioCorreo = txtUserCorreo.getText().trim();
        String contrasena = new String(pswContrasena.getPassword());

        if (usuarioCorreo.isEmpty() || contrasena.isEmpty()) {
            InterfaceStyle.showMsgError("Por favor ingrese usuario y contraseña.");
            return;
        }

        Administrador admin;
        try {
            admin = administradorDAO.getAdministrador(usuarioCorreo, contrasena);
        } catch (Exception e) {
            InterfaceStyle.showMsgError("Error al conectar con la base de datos: " + e.getMessage());
            return;
        }

        if (admin != null) {
            InterfaceStyle.showMsg("Bienvenido.");
            abrirListadoChoferesCRUD(admin);
            dispose();
        } else {
            InterfaceStyle.showMsgError("Credenciales incorrectas.");
        }
    }

    private void abrirListadoChoferesCRUD(Administrador admin) {
        PanelControl pControl = new PanelControl(admin.getIdAdministrador());
        pControl.setAdministradorData(admin.getNombres() + " " + admin.getApellidos(), admin.getCargoAdmin(),
                admin.getEstado());
        pControl.setVisible(true);
        dispose();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new LoginAdministradorForm().setVisible(true));
    }
}
