package ui.forms;

import businessLogical.GestorChofer;
import dataAccesComponent.dao.ChoferDAO;
import dataAccesComponent.dao.HuellaDAO;
import dataAccesComponent.dao.RutaDAO;
import dataAccesComponent.dao.VehiculoDAO;
import dataAccesComponent.entity.Chofer;
import dataAccesComponent.entity.Huella;
import dataAccesComponent.entity.Ruta;
import dataAccesComponent.entity.Vehiculo;
import framework.Validaciones;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import ui.customerControl.ComponentFactory;

public class RegistroDatosForm extends JPanel {

    private GestorChofer gestorChofer;
    private ChoferDAO choferDAO;
    private HuellaDAO huellaDAO;
    private VehiculoDAO vehiculoDAO;
    private RutaDAO rutasDAO;
    private int idChofer;

    private JTextField txtNombres, txtApellidos, txtCedula, txtTelefono, txtCorreo, txtDireccion, txtHuella,
            txtCategoriaLicencia, txtFechaVencimientoLicencia, txtVehiculo, txtRuta, txtPlaca,
            txtMarcaVehiculo, txtModeloVehiculo;
    private JLabel in_Cedula, in_Nombre, in_Apellido, in_Telefono, in_Correo, in_Direccion, in_Huella,
            in_CatLicencia, in_FechaVencimientoLicencia, in_Placa, in_TipoVehiculo, in_Ruta,
            in_MarcaVehiculo, in_ModeloVehiculo;

    public RegistroDatosForm() {
        setupPanel();
        gestorChofer = new GestorChofer();
    }

    public RegistroDatosForm(int idChofer) throws Exception {
        this.idChofer = idChofer;
        setupPanel();
        gestorChofer = new GestorChofer();
        choferDAO = new ChoferDAO();
        huellaDAO = new HuellaDAO();
        vehiculoDAO = new VehiculoDAO();
        rutasDAO = new RutaDAO();
        mostrarDatos(idChofer);
    }

    private void setupPanel() {
        setLayout(null);

        txtNombres = ComponentFactory.createTextField();
        txtApellidos = ComponentFactory.createTextField();
        txtCedula = ComponentFactory.createTextField();
        txtTelefono = ComponentFactory.createTextField();
        txtCorreo = ComponentFactory.createTextField();
        txtDireccion = ComponentFactory.createTextField();
        txtHuella = ComponentFactory.createTextField();
        txtCategoriaLicencia = ComponentFactory.createTextField();
        txtFechaVencimientoLicencia = ComponentFactory.createTextField();
        txtVehiculo = ComponentFactory.createTextField();
        txtRuta = ComponentFactory.createTextField();
        txtPlaca = ComponentFactory.createTextField();
        txtMarcaVehiculo = ComponentFactory.createTextField();
        txtModeloVehiculo = ComponentFactory.createTextField();

        in_Cedula = ComponentFactory.createLabel("N° Cedula:");
        in_Nombre = ComponentFactory.createLabel("Nombre:");
        in_Apellido = ComponentFactory.createLabel("Apellido:");
        in_Telefono = ComponentFactory.createLabel("Telefono:");
        in_Correo = ComponentFactory.createLabel("Correo:");
        in_Direccion = ComponentFactory.createLabel("Direccion:");
        in_Huella = ComponentFactory.createLabel("ID Huella:");
        in_CatLicencia = ComponentFactory.createLabel("Categoria de licencia:");
        in_FechaVencimientoLicencia = ComponentFactory.createLabel("Fecha vencimiento Licencia:");
        in_Placa = ComponentFactory.createLabel("Placa:");
        in_TipoVehiculo = ComponentFactory.createLabel("Tipo vehiculo:");
        in_Ruta = ComponentFactory.createLabel("Ruta:");
        in_MarcaVehiculo = ComponentFactory.createLabel("Marca:");
        in_ModeloVehiculo = ComponentFactory.createLabel("Modelo:");

        // Posicionamiento de los componentes en el panel
        in_Nombre.setBounds(20, 20, 100, 25);
        txtNombres.setBounds(130, 20, 220, 25);
        in_Apellido.setBounds(20, 60, 100, 25);
        txtApellidos.setBounds(130, 60, 220, 25);
        in_Cedula.setBounds(20, 100, 100, 25);
        txtCedula.setBounds(130, 100, 220, 25);
        in_Telefono.setBounds(20, 140, 100, 25);
        txtTelefono.setBounds(130, 140, 220, 25);
        in_Correo.setBounds(20, 180, 100, 25);
        txtCorreo.setBounds(130, 180, 220, 25);
        in_Direccion.setBounds(20, 220, 100, 25);
        txtDireccion.setBounds(130, 220, 220, 25);
        in_CatLicencia.setBounds(20, 260, 150, 25);
        txtCategoriaLicencia.setBounds(180, 260, 220, 25);
        in_FechaVencimientoLicencia.setBounds(20, 300, 150, 25);
        txtFechaVencimientoLicencia.setBounds(180, 300, 220, 25);
        in_Huella.setBounds(20, 340, 100, 25);
        txtHuella.setBounds(130, 340, 220, 25);
        in_Placa.setBounds(400, 20, 100, 25);
        txtPlaca.setBounds(500, 20, 220, 25);
        in_TipoVehiculo.setBounds(400, 60, 100, 25);
        txtVehiculo.setBounds(500, 60, 220, 25);
        in_MarcaVehiculo.setBounds(400, 100, 100, 25);
        txtMarcaVehiculo.setBounds(500, 100, 220, 25);
        in_ModeloVehiculo.setBounds(400, 140, 100, 25);
        txtModeloVehiculo.setBounds(500, 140, 220, 25);
        in_Ruta.setBounds(400, 180, 100, 25);
        txtRuta.setBounds(500, 180, 220, 25);

        add(in_Nombre);
        add(txtNombres);
        add(in_Apellido);
        add(txtApellidos);
        add(in_Cedula);
        add(txtCedula);
        add(in_Telefono);
        add(txtTelefono);
        add(in_Correo);
        add(txtCorreo);
        add(in_Direccion);
        add(txtDireccion);
        add(in_CatLicencia);
        add(txtCategoriaLicencia);
        add(in_FechaVencimientoLicencia);
        add(txtFechaVencimientoLicencia);
        add(in_Huella);
        add(txtHuella);
        add(in_Placa);
        add(txtPlaca);
        add(in_TipoVehiculo);
        add(txtVehiculo);
        add(in_MarcaVehiculo);
        add(txtMarcaVehiculo);
        add(in_ModeloVehiculo);
        add(txtModeloVehiculo);
        add(in_Ruta);
        add(txtRuta);
    }

    public void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        String cedula = txtCedula.getText();
        String nombres = txtNombres.getText();
        String apellidos = txtApellidos.getText();
        String telefono = txtTelefono.getText();
        String correo = txtCorreo.getText();
        String direccion = txtDireccion.getText();
        String huella = txtHuella.getText();
        String placa = txtPlaca.getText();
        String vehiculo = txtVehiculo.getText();
        String ruta = txtRuta.getText();
        String modeloVehiculo = txtModeloVehiculo.getText();
        String marcaVehiculo = txtMarcaVehiculo.getText();
        String categoriaLicencia = txtCategoriaLicencia.getText();
        String fechaVencimientoLicenciaStr = txtFechaVencimientoLicencia.getText();

        if (cedula.isEmpty() || nombres.isEmpty() || apellidos.isEmpty() || telefono.isEmpty() ||
                placa.isEmpty() || vehiculo.isEmpty() || ruta.isEmpty() || huella.isEmpty() || correo.isEmpty() ||
                direccion.isEmpty() || modeloVehiculo.isEmpty() || marcaVehiculo.isEmpty()
                || categoriaLicencia.isEmpty() || fechaVencimientoLicenciaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben ser llenados.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Cambios Ruben Inicio
        Validaciones validaciones = new Validaciones();

        boolean banderaID = false;
        boolean banderaNombre = false;
        boolean banderaApellido = false;
        boolean banderaTelefono = false;
        boolean banderaPlaca = false;
        boolean banderaIDHuella = false;
        boolean banderaMarca = false;
        boolean banderaModelo = false;

        while (banderaID == false) {
            if (cedula.length() == 10) {
                banderaID = true;
            } else {
                JOptionPane.showMessageDialog(this,
                        "El ID que ha ingresado no contiene exactamente 10 numeros, vuelvalo a ingresar", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (validaciones.validarBanderas(cedula.length(), cedula) == false) {
                JOptionPane.showMessageDialog(this, "El ID ingresado contiene un espacio, vuelvalo a ingresar", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                banderaID = true;
            }
            if (validaciones.validarNumeros(cedula.length(), cedula) == false) {
                JOptionPane.showMessageDialog(this, "El ID ingresado solo debe contener números, vuelvalo a ingresar",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                banderaID = true;
            }

        }

        while (banderaNombre == false) {
            if (validaciones.validarBanderas(nombres.length(), nombres) == false) {
                JOptionPane.showMessageDialog(this, "El nombre ingresado contiene un espacio, vuelvalo a ingresar",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                banderaNombre = true;
            }
        }

        while (banderaApellido == false) {
            if (validaciones.validarBanderas(apellidos.length(), apellidos) == false) {
                JOptionPane.showMessageDialog(this, "El apellido ingresado contiene un espacio, vuelvalo a ingresar",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                banderaApellido = true;
            }
        }

        while (banderaTelefono == false) {
            if (telefono.length() == 10) {
                banderaTelefono = true;
            } else {
                JOptionPane.showMessageDialog(this,
                        "El numero de telefono que ha ingresado no contiene exactamente 10 numeros, vuelvalo a ingresar",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (validaciones.validarBanderas(telefono.length(), telefono) == false) {
                JOptionPane.showMessageDialog(this, "El telefono ingresado contiene un espacio, vuelvalo a ingresar",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                banderaTelefono = true;
            }
            if (validaciones.validarNumeros(telefono.length(), telefono) == false) {
                JOptionPane.showMessageDialog(this,
                        "El telefono ingresado solo debe contener números, vuelvalo a ingresar", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                banderaTelefono = true;
            }
        }

        while (banderaIDHuella == false) {
            if (validaciones.validarBanderas(huella.length(), huella) == false) {
                JOptionPane.showMessageDialog(this,
                        "La ID de la huella ingresada contiene un espacio, vuelvalo a ingresar", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                banderaIDHuella = true;
            }
        }

        while (banderaPlaca == false) {
            if ((placa.length() == 8) && (placa.charAt(3) == '-')) {
                banderaPlaca = true;
            } else {
                JOptionPane.showMessageDialog(this,
                        "La placa que ha ingresado no contiene el formato correcto 000-XXXX, vuelvala a ingresar",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (validaciones.validarBanderas(placa.length(), placa) == false) {
                JOptionPane.showMessageDialog(this, "La placa ingresada contiene un espacio, vuelvalo a ingresar",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                banderaPlaca = true;
            }
        }

        while (banderaModelo == false) {

            if (validaciones.validarBanderas(modeloVehiculo.length(), modeloVehiculo) == false) {
                JOptionPane.showMessageDialog(this,
                        "El modelo de vehiculo ingresado contiene un espacio, vuelvalo a ingresar", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                banderaModelo = true;
            }
        }

        while (banderaMarca == false) {

            if (validaciones.validarBanderas(marcaVehiculo.length(), marcaVehiculo) == false) {
                JOptionPane.showMessageDialog(this,
                        "La marca del vehiculo ingresado contiene un espacio, vuelvalo a ingresar", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                banderaMarca = true;
            }
        }
        // Cambios Ruben Final

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date fechaVencimientoLicencia;
        try {
            Date parsedDate = dateFormat.parse(fechaVencimientoLicenciaStr);
            fechaVencimientoLicencia = new java.sql.Date(parsedDate.getTime());
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de fecha no válido. Use el formato 'yyyy-MM-dd'.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            idChofer = gestorChofer.registrarNuevoChofer(cedula, nombres, apellidos, telefono, huella, placa, vehiculo,
                    ruta, direccion, correo, categoriaLicencia, fechaVencimientoLicencia, marcaVehiculo,
                    modeloVehiculo);
            JOptionPane.showMessageDialog(this, "Registro guardado exitosamente.", "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();

            // Notificar a PanelControl para mostrar la tabla nuevamente
            PanelControl panelControl = (PanelControl) SwingUtilities.getWindowAncestor(this);
            panelControl.mostrarTabla();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el registro: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {
        int result = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres actualizar los datos?",
                "Confirmación", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String cedula = txtCedula.getText();
            String nombres = txtNombres.getText();
            String apellidos = txtApellidos.getText();
            String telefono = txtTelefono.getText();
            String correo = txtCorreo.getText();
            String direccion = txtDireccion.getText();
            String huella = txtHuella.getText();
            String placa = txtPlaca.getText();
            String vehiculo = txtVehiculo.getText();
            String ruta = txtRuta.getText();
            String modeloVehiculo = txtModeloVehiculo.getText();
            String marcaVehiculo = txtMarcaVehiculo.getText();
            String categoriaLicencia = txtCategoriaLicencia.getText();
            String fechaVencimientoLicenciaStr = txtFechaVencimientoLicencia.getText();

            if (cedula.isEmpty() || nombres.isEmpty() || apellidos.isEmpty() || telefono.isEmpty() ||
                    placa.isEmpty() || vehiculo.isEmpty() || ruta.isEmpty() || huella.isEmpty() || correo.isEmpty() ||
                    direccion.isEmpty() || modeloVehiculo.isEmpty() || marcaVehiculo.isEmpty()
                    || categoriaLicencia.isEmpty() || fechaVencimientoLicenciaStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos deben ser llenados.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // Cambios edición
            Validaciones validaciones = new Validaciones();
            boolean banderaID = false;
            boolean banderaNombre = false;
            boolean banderaApellido = false;
            boolean banderaTelefono = false;
            boolean banderaPlaca = false;
            boolean banderaIDHuella = false;
            boolean banderaMarca = false;
            boolean banderaModelo = false;

            while (banderaID == false) {
                if (cedula.length() == 10) {
                    banderaID = true;
                } else {
                    JOptionPane.showMessageDialog(this,
                            "El ID que ha ingresado no contiene exactamente 10 numeros, vuelvalo a ingresar", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                if (validaciones.validarBanderas(cedula.length(), cedula) == false) {
                    JOptionPane.showMessageDialog(this, "El ID ingresado contiene un espacio, vuelvalo a ingresar",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                } else {
                    banderaID = true;
                }
                if (validaciones.validarNumeros(cedula.length(), cedula) == false) {
                    JOptionPane.showMessageDialog(this,
                            "El ID ingresado solo debe contener números, vuelvalo a ingresar", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return false;
                } else {
                    banderaTelefono = true;
                }

            }

            while (banderaNombre == false) {
                if (validaciones.validarBanderas(nombres.length(), nombres) == false) {
                    JOptionPane.showMessageDialog(this, "El nombre ingresado contiene un espacio, vuelvalo a ingresar",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                } else {
                    banderaNombre = true;
                }
            }

            while (banderaApellido == false) {
                if (validaciones.validarBanderas(apellidos.length(), apellidos) == false) {
                    JOptionPane.showMessageDialog(this,
                            "El apellido ingresado contiene un espacio, vuelvalo a ingresar", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return false;
                } else {
                    banderaApellido = true;
                }
            }

            while (banderaTelefono == false) {
                if (telefono.length() == 10) {
                    banderaTelefono = true;
                } else {
                    JOptionPane.showMessageDialog(this,
                            "El numero de telefono que ha ingresado no contiene exactamente 10 numeros, vuelvalo a ingresar",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                if (validaciones.validarBanderas(telefono.length(), telefono) == false) {
                    JOptionPane.showMessageDialog(this,
                            "El telefono ingresado contiene un espacio, vuelvalo a ingresar", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return false;
                } else {
                    banderaTelefono = true;
                }
                if (validaciones.validarNumeros(telefono.length(), telefono) == false) {
                    JOptionPane.showMessageDialog(this,
                            "El telefono ingresado solo debe contener números, vuelvalo a ingresar", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return false;
                } else {
                    banderaTelefono = true;
                }
            }

            while (banderaIDHuella == false) {
                if (validaciones.validarBanderas(huella.length(), huella) == false) {
                    JOptionPane.showMessageDialog(this,
                            "La ID de la huella ingresada contiene un espacio, vuelvalo a ingresar", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return false;
                } else {
                    banderaIDHuella = true;
                }
            }

            while (banderaPlaca == false) {
                if ((placa.length() == 8) && (placa.charAt(3) == '-')) {
                    banderaPlaca = true;
                } else {
                    JOptionPane.showMessageDialog(this,
                            "La placa que ha ingresado no contiene el formato correcto 000-XXXX, vuelvala a ingresar",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                if (validaciones.validarBanderas(placa.length(), placa) == false) {
                    JOptionPane.showMessageDialog(this, "La placa ingresada contiene un espacio, vuelvalo a ingresar",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                } else {
                    banderaPlaca = true;
                }
            }

            while (banderaModelo == false) {

                if (validaciones.validarBanderas(modeloVehiculo.length(), modeloVehiculo) == false) {
                    JOptionPane.showMessageDialog(this,
                            "El modelo de vehiculo ingresado contiene un espacio, vuelvalo a ingresar", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return false;
                } else {
                    banderaModelo = true;
                }
            }

            while (banderaMarca == false) {

                if (validaciones.validarBanderas(marcaVehiculo.length(), marcaVehiculo) == false) {
                    JOptionPane.showMessageDialog(this,
                            "La marca del vehiculo ingresado contiene un espacio, vuelvalo a ingresar", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return false;
                } else {
                    banderaMarca = true;
                }
            }

            // Fin cambios edicion

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.sql.Date fechaVencimientoLicencia;
            try {
                Date parsedDate = dateFormat.parse(fechaVencimientoLicenciaStr);
                fechaVencimientoLicencia = new java.sql.Date(parsedDate.getTime());
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Formato de fecha no válido. Use el formato 'yyyy-MM-dd'.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }

            try {
                boolean actualizado = gestorChofer.actualizarChofer(idChofer, cedula, nombres, apellidos, telefono,
                        huella, placa, vehiculo,
                        ruta, direccion, correo, categoriaLicencia, fechaVencimientoLicencia, marcaVehiculo,
                        modeloVehiculo);

                if (actualizado) {
                    JOptionPane.showMessageDialog(this, "Registro actualizado exitosamente.", "Éxito",
                            JOptionPane.INFORMATION_MESSAGE);

                    // Notificar a PanelControl para mostrar la tabla nuevamente
                    PanelControl panelControl = (PanelControl) SwingUtilities.getWindowAncestor(this);
                    panelControl.mostrarTabla();

                    return true; // Solo retornar true si la actualización fue exitosa
                } else {
                    return false; // Si la actualización falló, retornar false
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al actualizar el registro: " + e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
                return false; // Indica que la actualización falló
            }
        }

        return false; // En caso de que se cancele la actualización
    }

    public void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        int result = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres cancelar el ingreso?",
                "Confirmación", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            // Notificar a PanelControl para mostrar la tabla nuevamente
            PanelControl panelControl = (PanelControl) SwingUtilities.getWindowAncestor(this);
            panelControl.mostrarTabla();
        }
    }

    private void mostrarDatos(int idc) throws Exception {
        Chofer chofer = choferDAO.readBy(idc);
        Vehiculo vehiculo = vehiculoDAO.obtenerVehiculoPorChoferId(idc);
        Huella huella = huellaDAO.obtenerHuellaPorChoferId(idc);
        Ruta rutas = rutasDAO.obtenerRutaPorChoferId(idc);

        if (chofer != null) {
            txtNombres.setText(chofer.getNombre());
            txtApellidos.setText(chofer.getApellido());
            txtCedula.setText(chofer.getIdCedula());
            txtTelefono.setText(chofer.getTelefono());
            txtCorreo.setText(chofer.getCorreo());
            txtDireccion.setText(chofer.getDireccion());
            txtCategoriaLicencia.setText(chofer.getCategoriaLicencia());

            if (chofer.getFechaVencimientoLicencia() != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String fechaFormateada = dateFormat.format(chofer.getFechaVencimientoLicencia());
                txtFechaVencimientoLicencia.setText(fechaFormateada);
            } else {
                txtFechaVencimientoLicencia.setText("");
            }

            if (vehiculo != null) {
                txtPlaca.setText(vehiculo.getIdPlaca());
                txtVehiculo.setText(vehiculo.getTipoVehiculo());
                txtMarcaVehiculo.setText(vehiculo.getMarcaVehiculo());
                txtModeloVehiculo.setText(vehiculo.getModeloVehiculo());
            }
            if (rutas != null) {
                txtRuta.setText(rutas.getNombreRuta());
            }
            if (huella != null) {
                txtHuella.setText(huella.getIdCodigoHuella());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos del chofer", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtCedula.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtDireccion.setText("");
        txtHuella.setText("");
        txtPlaca.setText("");
        txtVehiculo.setText("");
        txtRuta.setText("");
        txtModeloVehiculo.setText("");
        txtMarcaVehiculo.setText("");
        txtCategoriaLicencia.setText("");
        txtFechaVencimientoLicencia.setText("");
    }

    public int getIdChofer() {
        return idChofer;
    }

    public String getChoferInfo() {
        return txtNombres.getText() + " " + txtApellidos.getText();
    }
}
