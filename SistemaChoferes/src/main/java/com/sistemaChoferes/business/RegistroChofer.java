package com.sistemaChoferes.business;

import java.sql.Date;

import com.sistemaChoferes.dataacess.entity.Chofer;
import com.sistemaChoferes.dataacess.entity.Huella;
import com.sistemaChoferes.dataacess.entity.Rutas;
import com.sistemaChoferes.dataacess.entity.Vehiculo;
import com.sistemaChoferes.dataacess.dao.ChoferDAO;
import com.sistemaChoferes.dataacess.dao.HuellaDAO;
import com.sistemaChoferes.dataacess.dao.RutasDAO;
import com.sistemaChoferes.dataacess.dao.VehiculoDAO;

public class RegistroChofer {

    private HuellaDAO huellaDAO = new HuellaDAO();
    private ChoferDAO choferDAO = new ChoferDAO();
    private VehiculoDAO vehiculoDAO = new VehiculoDAO();
    private RutasDAO rutasDAO = new RutasDAO();

    public void registrarNuevoChofer(String idCedula, String nombre, String apellido, String telefono, 
                                 String idCodigoHuella, String idPlaca, String tipoVehiculo, 
                                 String nombreRuta, String direccion, String correo, 
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

            if (vehiculoDAO.existeVehiculoPorPlaca(idPlaca)) {
                throw new RuntimeException("Error: La placa ya existe en el sistema.");
            }

            Chofer chofer = new Chofer();
            chofer.setIdCedula(idCedula);
            chofer.setNombre(nombre);
            chofer.setApellido(apellido);
            chofer.setTelefono(telefono);
            chofer.setCorreo(correo);
            chofer.setDireccion(direccion);
            chofer.setCategoriaLicencia(categoriaLicencia);
            chofer.setFechaVencimientoLicencia(fechaVencimientoLicencia);
            choferDAO.create(chofer);

            int idChofer = choferDAO.readAll().stream()
                    .filter(c -> c.getIdCedula().equals(idCedula))
                    .findFirst()
                    .get()
                    .getIdChofer();

            Huella huella = new Huella();
            huella.setIdCodigoHuella(idCodigoHuella);
            huella.setIdChofer(idChofer);
            huellaDAO.create(huella);

            Vehiculo vehiculo = new Vehiculo();
            vehiculo.setIdPlaca(idPlaca);
            vehiculo.setTipoVehiculo(tipoVehiculo);
            vehiculo.setIdChofer(idChofer);
            vehiculo.setMarcaVehiculo(marcaVehiculo);
            vehiculo.setModeloVehiculo(modeloVehiculo);
            vehiculoDAO.create(vehiculo);

            Rutas ruta = new Rutas();
            ruta.setNombreRuta(nombreRuta);
            ruta.setIdChofer(idChofer);
            rutasDAO.create(ruta);

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
            Chofer chofer = choferDAO.readBy(idChofer);
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
            chofer.setDireccion(direccion);
            chofer.setCategoriaLicencia(categoriaLicencia);
            chofer.setFechaVencimientoLicencia(fechaVencimientoLicencia);
            choferDAO.update(chofer);

            Huella huellaExistente = huellaDAO.readBy(idChofer);
            if (huellaExistente != null) {
                huellaExistente.setIdCodigoHuella(idCodigoHuella);
                huellaDAO.update(huellaExistente);
            } else {
                Huella huella = new Huella();
                huella.setIdCodigoHuella(idCodigoHuella);
                huella.setIdChofer(idChofer);
                huellaDAO.create(huella);
            }

            Vehiculo vehiculo = vehiculoDAO.obtenerVehiculoPorChoferId(idChofer);
            if (vehiculo == null) {
                vehiculo = new Vehiculo();
                vehiculo.setIdPlaca(idPlaca);
                vehiculo.setTipoVehiculo(tipoVehiculo);
                vehiculo.setIdChofer(idChofer);
                vehiculo.setMarcaVehiculo(marcaVehiculo);
                vehiculo.setModeloVehiculo(modeloVehiculo);
                vehiculoDAO.create(vehiculo);
            } else {
                vehiculo.setIdPlaca(idPlaca);
                vehiculo.setTipoVehiculo(tipoVehiculo);
                vehiculo.setMarcaVehiculo(marcaVehiculo);
                vehiculo.setModeloVehiculo(modeloVehiculo);
                vehiculoDAO.update(vehiculo);
            }

            Rutas ruta = rutasDAO.readBy(idChofer);
            if (ruta == null) {
                ruta = new Rutas();
                ruta.setNombreRuta(nombreRuta);
                ruta.setIdChofer(idChofer);
                rutasDAO.create(ruta);
            } else {
                ruta.setNombreRuta(nombreRuta);
                rutasDAO.update(ruta);
            }

            System.out.println("Actualización completada con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar el chofer: " + e.getMessage());
        }
    }
}
