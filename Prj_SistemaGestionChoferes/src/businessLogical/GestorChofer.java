package businessLogical;

import dataAccesComponent.dao.ChoferDAO;
import dataAccesComponent.dao.HuellaDAO;
import dataAccesComponent.dao.RutaDAO;
import dataAccesComponent.dao.VehiculoDAO;
import dataAccesComponent.entity.Chofer;
import dataAccesComponent.entity.Huella;
import dataAccesComponent.entity.Ruta;
import dataAccesComponent.entity.Vehiculo;

public class GestorChofer {

    private HuellaDAO huellaDAO = new HuellaDAO();
    private ChoferDAO choferDAO = new ChoferDAO();
    private VehiculoDAO vehiculoDAO = new VehiculoDAO();
    private RutaDAO rutasDAO = new RutaDAO();

    public int registrarNuevoChofer(String idCedula, String nombre, String apellido, String telefono,
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

            Ruta ruta = new Ruta();
            ruta.setNombreRuta(nombreRuta);
            ruta.setIdChofer(idChofer);
            rutasDAO.create(ruta);

            System.out.println("Registro completado con éxito.");
            System.out.println("ID del chofer registrado: " + idChofer);
            return idChofer;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al registrar el chofer: " + e.getMessage());
        }
    }

    public boolean actualizarChofer(int idChofer, String idCedula, String nombre, String apellido, String telefono,
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

        Ruta ruta = rutasDAO.readBy(idChofer);
        if (ruta == null) {
            ruta = new Ruta();
            ruta.setNombreRuta(nombreRuta);
            ruta.setIdChofer(idChofer);
            rutasDAO.create(ruta);
        } else {
            ruta.setNombreRuta(nombreRuta);
            rutasDAO.update(ruta);
        }

        System.out.println("Actualización completada con éxito.");
        return true; // Indica que la actualización fue exitosa
    } catch (Exception e) {
        e.printStackTrace();
        return false; // Indica que la actualización falló
    }
}


}
