package com.sistemaChoferes.model.src.funcionesGestiosChoferes;

import java.sql.Date;

import com.sistemaChoferes.model.src.clasesEntidades.Chofer;
import com.sistemaChoferes.model.src.clasesEntidades.Huella;
import com.sistemaChoferes.model.src.clasesEntidades.Rutas;
import com.sistemaChoferes.model.src.clasesEntidades.Vehiculo;
import com.sistemaChoferes.model.src.objetoAccesoDatos.ChoferDAO;
import com.sistemaChoferes.model.src.objetoAccesoDatos.HuellaDAO;
import com.sistemaChoferes.model.src.objetoAccesoDatos.RutasDAO;
import com.sistemaChoferes.model.src.objetoAccesoDatos.VehiculoDAO;

public class RegistroChofer {

    private HuellaDAO huellaDAO = new HuellaDAO();
    private ChoferDAO choferDAO = new ChoferDAO();
    private VehiculoDAO vehiculoDAO = new VehiculoDAO();
    private RutasDAO rutasDAO = new RutasDAO();

    public void registrarNuevoChofer(String idCedula, String nombre, String apellido, String telefono, 
                                 String idCodigoHuella, String idPlaca, String tipoVehiculo, 
                                 String nombreRuta, String dirreccion, String correo, 
                                 String categoriaLicencia, java.sql.Date fechaVencimientoLicencia, 
                                 String marcaVehiculo, String modeloVehiculo) {
    try {
        if (choferDAO.existeChoferPorCedula(idCedula)) {
            throw new RuntimeException("Error: La cédula ya existe en el sistema.");
        }

        if (choferDAO.existeChoferPorTelefono(telefono)) {
            throw new RuntimeException("Error: El teléfono ya existe en el sistema.");
        }
        
        if (choferDAO.existeChoferPorCorreo(correo)) {
            throw new RuntimeException("Error: El correo ya existe en el sistema.");
        }

        if (vehiculoDAO.existeVehiculoPorPlaca(idPlaca)){
            throw new RuntimeException("Error: La placa ya existe en el sistema.");
        }

        Chofer chofer = new Chofer();
        chofer.setIdCedula(idCedula);
        chofer.setNombre(nombre);
        chofer.setApellido(apellido);
        chofer.setTelefono(telefono);
        chofer.setCorreo(correo);
        chofer.setDirreccion(dirreccion);
        chofer.setCategoriaLicencia(categoriaLicencia);
        chofer.setFechaVenciminetoLicencia(fechaVencimientoLicencia);
        choferDAO.agregarChofer(chofer);

        int idChofer = choferDAO.obtenerTodoChoferes().stream()
                .filter(c -> c.getIdCedula().equals(idCedula))
                .findFirst()
                .get()
                .getIdChofer();

        if (huellaDAO.existeHuella(idChofer) || 
            vehiculoDAO.existeVehiculo(idChofer) || 
            rutasDAO.existeRuta(idChofer)) {
            throw new RuntimeException("Error: Existen registros duplicados para el chofer, vehículo, huella o ruta.");
        }

        Huella huella = new Huella();
        huella.setIdCodigoHuella(idCodigoHuella);
        huella.setIdChofer(idChofer);
        huellaDAO.agregarHuella(huella);

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setIdPlaca(idPlaca);
        vehiculo.setTipoVehiculo(tipoVehiculo);
        vehiculo.setIdChofer(idChofer);
        vehiculo.setMarcaVehiculo(marcaVehiculo);
        vehiculo.setModeloVehiculo(modeloVehiculo);
        vehiculoDAO.agregarVehiculo(vehiculo);

        Rutas ruta = new Rutas();
        ruta.setNombreRuta(nombreRuta);
        ruta.setIdChofer(idChofer);
        rutasDAO.agregarRuta(ruta);

        System.out.println("Registro completado con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al registrar el chofer: " + e.getMessage());
        }
    }

    
   public void actualizarChofer(int idChofer, String idCedula, String nombre, String apellido, String telefono, 
                             String idCodigoHuella, String idPlaca, String tipoVehiculo, String nombreRuta, 
                             String direccion, String correo, String categoriaLicencia, 
                             java.sql.Date fechaVencimientoLicencia, String marcaVehiculo, String modeloVehiculo) {
    try {
        Chofer chofer = choferDAO.obtenerChoferPorId(idChofer);
        if (chofer == null) {
            throw new RuntimeException("Error: El chofer no existe.");
        }

        if (!chofer.getIdCedula().equals(idCedula) && choferDAO.existeChoferPorCedula(idCedula)) {
            throw new RuntimeException("Error: La cédula ya existe en el sistema.");
        }

        chofer.setIdCedula(idCedula);
        chofer.setNombre(nombre);
        chofer.setApellido(apellido);
        chofer.setTelefono(telefono);
        chofer.setCorreo(correo);
        chofer.setDirreccion(direccion);
        chofer.setCategoriaLicencia(categoriaLicencia);
        chofer.setFechaVenciminetoLicencia(fechaVencimientoLicencia);
        choferDAO.actualizarChofer(chofer);

        Huella huellaExistente = huellaDAO.obtenerHuellaPorChoferId(idChofer);
        if (huellaExistente != null) {
            huellaExistente.setIdCodigoHuella(idCodigoHuella);
            huellaDAO.actualizarHuella(huellaExistente);
        } else {
            Huella huella = new Huella();
            huella.setIdCodigoHuella(idCodigoHuella);
            huella.setIdChofer(idChofer);
            huellaDAO.agregarHuella(huella);
        }

        Vehiculo vehiculo = vehiculoDAO.obtenerVehiculoPorChoferId(idChofer);
        if (vehiculo == null) {
            vehiculo = new Vehiculo();
            vehiculo.setIdPlaca(idPlaca);
            vehiculo.setTipoVehiculo(tipoVehiculo);
            vehiculo.setIdChofer(idChofer);
            vehiculo.setMarcaVehiculo(marcaVehiculo);
            vehiculo.setModeloVehiculo(modeloVehiculo);
            vehiculoDAO.agregarVehiculo(vehiculo);
        } else {
            vehiculo.setIdPlaca(idPlaca);
            vehiculo.setTipoVehiculo(tipoVehiculo);
            vehiculo.setMarcaVehiculo(marcaVehiculo);
            vehiculo.setModeloVehiculo(modeloVehiculo);
            vehiculoDAO.actualizarVehiculo(vehiculo);
        }

        Rutas ruta = rutasDAO.obtenerRutaPorChoferId(idChofer);
        if (ruta == null) {
            ruta = new Rutas();
            ruta.setNombreRuta(nombreRuta);
            ruta.setIdChofer(idChofer);
            rutasDAO.agregarRuta(ruta);
        } else {
            ruta.setNombreRuta(nombreRuta);
            rutasDAO.actualizarRuta(ruta);
        }

        System.out.println("Actualización completada con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar el chofer: " + e.getMessage());
        }
    }

}

