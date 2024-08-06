package ui.forms;

import businessLogical.GestorModificaciones;
import dataAccesComponent.entity.Modificaciones;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import ui.customerControl.ComponentFactory;
import ui.resources.InterfaceStyle;

public class PanelControl extends JFrame {

    private JPanel panelCambiable;
    private ListadoChoferesForm listadoChoferesForm;
    private JPanel datosChoferPanel;
    private JLabel lblNombre, lblCargo, lblEstado;
    private JLabel lbLlenoCedula, lbLlenoEstado, lbLlenoNombres, lbLlenoApellidos, lbLlenoTelefono, lbLlenoHuella,
            lbLlenoPlaca, lbLlenoRuta, lbLlenoFechaIn, lbLlenoFechaMod;
    private JButton btnAdd, btnEdit, btnView, btnExit, btnGuardar, btnActualizar, btnCancelar, btnRegresar;

    private GestorModificaciones gestorModificaciones;
    private int idAdministrador;

    public PanelControl(int idAdministrador) {
        this.idAdministrador = idAdministrador;
        setupComponents();
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        gestorModificaciones = new GestorModificaciones();
    }

    private void setupComponents() {
        setLayout(null);

        // Configuración del panel del administrador
        JPanel adminPanel = new JPanel();
        adminPanel.setLayout(null);
        adminPanel.setBounds(10, 10, 200, 200);
        adminPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        JLabel lblAdmin = ComponentFactory.createLabel("ADMINISTRADOR");
        lblAdmin.setBounds(10, 10, 180, 30);
        adminPanel.add(lblAdmin);

        lblNombre = ComponentFactory.createLabel("Nombre: ");
        lblNombre.setBounds(10, 50, 180, 30);
        adminPanel.add(lblNombre);

        lblCargo = ComponentFactory.createLabel("Cargo: ");
        lblCargo.setBounds(10, 90, 180, 30);
        adminPanel.add(lblCargo);

        lblEstado = ComponentFactory.createLabel("Estado: ");
        lblEstado.setBounds(10, 130, 180, 30);
        adminPanel.add(lblEstado);

        add(adminPanel);

        // Panel para los datos del chofer
        datosChoferPanel = new JPanel();
        datosChoferPanel.setLayout(null);
        datosChoferPanel.setBounds(10, 220, 200, 300);
        datosChoferPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        datosChoferPanel.setVisible(false);
        add(datosChoferPanel);

        lbLlenoCedula = ComponentFactory.createLabel("Cedula: ");
        lbLlenoCedula.setBounds(10, 10, 180, 30);
        datosChoferPanel.add(lbLlenoCedula);

        lbLlenoNombres = ComponentFactory.createLabel("Nombres: ");
        lbLlenoNombres.setBounds(10, 50, 180, 30);
        datosChoferPanel.add(lbLlenoNombres);

        lbLlenoApellidos = ComponentFactory.createLabel("Apellidos: ");
        lbLlenoApellidos.setBounds(10, 90, 180, 30);
        datosChoferPanel.add(lbLlenoApellidos);

        lbLlenoTelefono = ComponentFactory.createLabel("Teléfono: ");
        lbLlenoTelefono.setBounds(10, 130, 180, 30);
        datosChoferPanel.add(lbLlenoTelefono);

        lbLlenoHuella = ComponentFactory.createLabel("ID Huella: ");
        lbLlenoHuella.setBounds(10, 170, 180, 30);
        datosChoferPanel.add(lbLlenoHuella);

        lbLlenoPlaca = ComponentFactory.createLabel("Placa: ");
        lbLlenoPlaca.setBounds(10, 210, 180, 30);
        datosChoferPanel.add(lbLlenoPlaca);

        lbLlenoRuta = ComponentFactory.createLabel("Ruta: ");
        lbLlenoRuta.setBounds(10, 250, 180, 30);
        datosChoferPanel.add(lbLlenoRuta);

        lbLlenoFechaIn = ComponentFactory.createLabel("Fecha de ingreso: ");
        lbLlenoFechaIn.setBounds(10, 290, 180, 30);
        datosChoferPanel.add(lbLlenoFechaIn);

        lbLlenoFechaMod = ComponentFactory.createLabel("Fecha última modificación: ");
        lbLlenoFechaMod.setBounds(10, 330, 180, 30);
        datosChoferPanel.add(lbLlenoFechaMod);

        // Posiciones de los botones
        int baseX = 750;
        int spacing = 60;

        btnAdd = ComponentFactory.createCircularButton(new ImageIcon(InterfaceStyle.URL_ICON_ADD),
                new Color(100, 100, 255),
                new Color(120, 120, 255), new Color(80, 80, 255));
        btnAdd.setBounds(baseX, 10, 50, 50);
        btnAdd.addActionListener(e -> handleAddAction());
        add(btnAdd);

        btnEdit = ComponentFactory.createCircularButton(new ImageIcon(InterfaceStyle.URL_ICON_EDIT),
                new Color(100, 100, 255),
                new Color(120, 120, 255), new Color(80, 80, 255));
        btnEdit.setBounds(baseX + spacing, 10, 50, 50);
        btnEdit.addActionListener(e -> handleEditAction());
        add(btnEdit);

        btnView = ComponentFactory.createCircularButton(new ImageIcon(InterfaceStyle.URL_ICON_VIEW),
                new Color(100, 100, 255),
                new Color(120, 120, 255), new Color(80, 80, 255));
        btnView.setBounds(baseX + 2 * spacing, 10, 50, 50);
        btnView.addActionListener(e -> handleViewAction());
        add(btnView);

        btnExit = ComponentFactory.createCircularButton(new ImageIcon(InterfaceStyle.URL_ICON_EXIT),
                new Color(100, 100, 255),
                new Color(120, 120, 255), new Color(80, 80, 255));
        btnExit.setBounds(baseX + 3 * spacing, 10, 50, 50);
        btnExit.addActionListener(e -> handleExitAction());
        add(btnExit);

        btnGuardar = ComponentFactory.createCircularButton(new ImageIcon(InterfaceStyle.URL_ICON_SAVE),
                new Color(100, 255, 100),
                new Color(120, 255, 120), new Color(80, 255, 80));
        btnGuardar.setBounds(baseX + 2 * spacing, 10, 50, 50);
        btnGuardar.setVisible(false);
        btnGuardar.addActionListener(e -> handleGuardarAction());
        add(btnGuardar);

        btnActualizar = ComponentFactory.createCircularButton(new ImageIcon(InterfaceStyle.URL_ICON_UPDATE),
                new Color(204, 255, 255),
                new Color(120, 255, 255), new Color(80, 255, 255));
        btnActualizar.setBounds(baseX + 2 * spacing, 10, 50, 50);
        btnActualizar.setVisible(false);
        btnActualizar.addActionListener(e -> handleActualizarAction());
        add(btnActualizar);

        btnCancelar = ComponentFactory.createCircularButton(new ImageIcon(InterfaceStyle.URL_ICON_CANCEL),
                new Color(255, 153, 153),
                new Color(255, 120, 120), new Color(255, 80, 80));
        btnCancelar.setBounds(baseX + 3 * spacing, 10, 50, 50);
        btnCancelar.setVisible(false);
        btnCancelar.addActionListener(e -> handleCancelarAction());
        add(btnCancelar);

        btnRegresar = ComponentFactory.createCircularButton(new ImageIcon(InterfaceStyle.URL_ICON_DELETE),
                new Color(100, 100, 255),
                new Color(120, 120, 255), new Color(80, 80, 255));
        btnRegresar.setBounds(baseX + 4 * spacing, 10, 50, 50);
        btnRegresar.setVisible(false);
        btnRegresar.addActionListener(e -> handleRegresarAction());
        add(btnRegresar);

        // Panel cambiable
        panelCambiable = new JPanel();
        panelCambiable.setLayout(null);
        panelCambiable.setBounds(250, 70, 750, 500);
        panelCambiable.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        listadoChoferesForm = new ListadoChoferesForm();
        listadoChoferesForm.setBounds(0, 0, 730, 480);
        panelCambiable.add(listadoChoferesForm);
        add(panelCambiable);
    }

    private void handleAddAction() {
        toggleButtons(false);

        btnGuardar.setBounds(btnActualizar.getBounds());
        btnGuardar.setVisible(true);
        btnActualizar.setVisible(false);
        btnCancelar.setBounds(btnExit.getBounds());
        btnCancelar.setVisible(true);

        panelCambiable.removeAll();
        RegistroDatosForm registroDatosForm = new RegistroDatosForm();
        registroDatosForm.setBounds(0, 0, panelCambiable.getWidth(), panelCambiable.getHeight());
        panelCambiable.add(registroDatosForm);
        panelCambiable.revalidate();
        panelCambiable.repaint();
    }

    private void handleEditAction() {
        int selectedRow = listadoChoferesForm.getTable().getSelectedRow();
        if (selectedRow >= 0) {
            toggleButtons(false);

            btnActualizar.setBounds(btnActualizar.getBounds());
            btnActualizar.setVisible(true);
            btnGuardar.setVisible(false);
            btnCancelar.setBounds(btnExit.getBounds());
            btnCancelar.setVisible(true);

            String id = listadoChoferesForm.getTable().getValueAt(selectedRow, 0).toString();
            try {
                panelCambiable.removeAll();
                RegistroDatosForm registroDatosForm = new RegistroDatosForm(Integer.parseInt(id));
                registroDatosForm.setBounds(0, 0, panelCambiable.getWidth(), panelCambiable.getHeight());
                panelCambiable.add(registroDatosForm);
                panelCambiable.revalidate();
                panelCambiable.repaint();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al cargar los datos del chofer: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un chofer para editar.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleExitAction() {
        int result = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas salir?", "Confirmar salida",
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            // Cerrar el PanelControl y mostrar la ventana de login
            this.dispose();
            LoginAdministradorForm loginForm = new LoginAdministradorForm();
            loginForm.setVisible(true);
        }
    }

    private void handleViewAction() {
        int selectedRow = listadoChoferesForm.getTable().getSelectedRow();
        if (selectedRow >= 0) {
            String id = listadoChoferesForm.getTable().getValueAt(selectedRow, 0).toString();
            try {
                panelCambiable.removeAll();
                VisualizarDatos visualizarDatos = new VisualizarDatos(Integer.parseInt(id), this);
                visualizarDatos.setBounds(0, 0, panelCambiable.getWidth(), panelCambiable.getHeight());
                panelCambiable.add(visualizarDatos);
                panelCambiable.revalidate();
                panelCambiable.repaint();

                toggleButtons(false);
                btnRegresar.setVisible(true); // Usar el botón de regresar en lugar de eliminar

                // Registrar la visualización en el log
                registrarModificacion("Chofer visualizado", Integer.parseInt(id));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al cargar los datos del chofer: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un chofer para visualizar.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleGuardarAction() {
        JPanel currentPanel = (JPanel) panelCambiable.getComponent(0);
        if (currentPanel instanceof RegistroDatosForm) {
            try {
                ((RegistroDatosForm) currentPanel).btnGuardarActionPerformed(null);

                // Obtener el ID del chofer que se acaba de guardar
                int idChoferModificado = ((RegistroDatosForm) currentPanel).getIdChofer();

                // Solo registrar la modificación si el idChofer es válido (mayor a 0)
                if (idChoferModificado > 0) {
                    registrarModificacion("Chofer agregado", idChoferModificado);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al guardar el chofer: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleActualizarAction() {
        JPanel currentPanel = (JPanel) panelCambiable.getComponent(0);
        if (currentPanel instanceof RegistroDatosForm) {
            try {
                System.out.println("Iniciando la actualización del chofer...");

                boolean actualizado = ((RegistroDatosForm) currentPanel).btnActualizarActionPerformed(null);

                if (actualizado) {
                    int idChoferModificado = ((RegistroDatosForm) currentPanel).getIdChofer();
                    if (idChoferModificado > 0) {
                        System.out.println("Actualización exitosa, registrando la modificación...");
                        registrarModificacion("Chofer actualizado", idChoferModificado);
                    }
                } else {
                    System.out.println("Actualización fallida, no se registrará la modificación.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al actualizar el chofer: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println("Error durante la actualización: " + e.getMessage());
            }
        }
    }

    private void handleRegresarAction() {
        int result = JOptionPane.showConfirmDialog(this, "¿Desea regresar al panel anterior?", "Confirmar regreso",
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            mostrarTabla();
        }
    }

    private void handleCancelarAction() {
        int result = JOptionPane.showConfirmDialog(this, "¿Estás seguro que deseas cancelar la acción?",
                "Confirmar regreso",
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            mostrarTabla();
        }
    }

    public void mostrarTabla() {
        panelCambiable.removeAll();
        listadoChoferesForm.actualizarTabla();
        panelCambiable.add(listadoChoferesForm);
        panelCambiable.revalidate();
        panelCambiable.repaint();
        toggleButtons(true);
        datosChoferPanel.setVisible(false);
    }

    private void toggleButtons(boolean showControlButtons) {
        btnAdd.setVisible(showControlButtons);
        btnEdit.setVisible(showControlButtons);
        btnView.setVisible(showControlButtons);
        btnExit.setVisible(showControlButtons);
        btnGuardar.setVisible(!showControlButtons && btnGuardar.isVisible());
        btnActualizar.setVisible(!showControlButtons && btnActualizar.isVisible());
        btnCancelar.setVisible(!showControlButtons);
        btnRegresar.setVisible(!showControlButtons);
    }

    private void registrarModificacion(String accion, int idChofer) {
        try {
            // Crear una instancia de la clase Modificaciones
            Modificaciones modificacion = new Modificaciones();

            // Configurar los valores
            modificacion.setIdAdministrador(idAdministrador);
            modificacion.setFechaModificacion(new java.sql.Date(System.currentTimeMillis())); // O la fecha que
                                                                                              // corresponda
            modificacion.setHoraModificacion(new SimpleDateFormat("HH:mm:ss").format(new Date())); // Hora actual
            modificacion.setAccionAdmin(accion);
            modificacion.setIdChoferModificacion(idChofer);

            // Registrar la modificación usando GestorModificaciones
            gestorModificaciones.registrarModificacion(modificacion);
        } catch (Exception e) {
            e.printStackTrace();
            // Manejo del error
        }

    }

    int obtenerIdAdministrador() {
        return idAdministrador;
    }

    public static void main(String[] args) {
        PanelControl panelControl = new PanelControl(1);
        panelControl.setVisible(true);
    }

    public void setAdministradorData(String nombre, String cargo, String estado) {
        lblNombre.setText("Nombre: " + nombre);
        lblCargo.setText("Cargo: " + cargo);
        lblEstado.setText("Estado: " + estado);
    }

    public void setChoferData(String cedula, String nombres, String apellidos, String telefono, String huella,
            String placa, String ruta, String fechaIngreso, String fechaModificacion) {
        lbLlenoCedula.setText("Cedula: " + cedula);
        lbLlenoNombres.setText("Nombres: " + nombres);
        lbLlenoApellidos.setText("Apellidos: " + apellidos);
        lbLlenoTelefono.setText("Teléfono: " + telefono);
        lbLlenoHuella.setText("ID Huella: " + huella);
        lbLlenoPlaca.setText("Placa: " + placa);
        lbLlenoRuta.setText("Ruta: " + ruta);
        lbLlenoFechaIn.setText("Fecha de ingreso: " + fechaIngreso);
        lbLlenoFechaMod.setText("Fecha última modificación: " + fechaModificacion);
        datosChoferPanel.setVisible(true);
    }
}
