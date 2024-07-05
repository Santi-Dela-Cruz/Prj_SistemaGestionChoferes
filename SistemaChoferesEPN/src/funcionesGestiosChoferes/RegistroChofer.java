package funcionesGestiosChoferes;

import clasesEntidades.Chofer;
import clasesEntidades.Huella;
import clasesEntidades.Rutas;
import clasesEntidades.Vehiculo;
import java.util.Scanner;
import objetoAccesoDatos.ChoferDAO;
import objetoAccesoDatos.HuellaDAO;
import objetoAccesoDatos.RutasDAO;
import objetoAccesoDatos.VehiculoDAO;

public class RegistroChofer {

    private HuellaDAO huellaDAO = new HuellaDAO();
    private ChoferDAO choferDAO = new ChoferDAO();
    private VehiculoDAO vehiculoDAO = new VehiculoDAO();
    private RutasDAO rutasDAO = new RutasDAO();

    public void registrarNuevoChofer() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el ID del chofer: ");
        String idChofer = scanner.nextLine();

        System.out.print("Ingrese el nombre del chofer: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el apellido del chofer: ");
        String apellido = scanner.nextLine();

        System.out.print("Ingrese el teléfono del chofer: ");
        String telefono = scanner.nextLine();

        System.out.print("Ingrese el ID de la huella: ");
        String idHuella = scanner.nextLine();
        
        System.out.print("Ingrese la placa del vehículo: ");
        String idPlaca = scanner.nextLine();

        System.out.print("Ingrese el tipo de vehículo: ");
        String tipoVehiculo = scanner.nextLine();

        System.out.print("Ingrese la ruta del vehículo: ");
        String nombreRuta = scanner.nextLine();


        if (choferDAO.existeChoferId(idChofer) || vehiculoDAO.existeVehiculoChoferId(idChofer) || huellaDAO.existeHuellaChoferId(idChofer) || rutasDAO.existeRutaNombre(nombreRuta)) {
            System.out.println("Error: Existen registros duplicados para el chofer, vehículo, huella o ruta.");
            return;
        }

        Chofer chofer = new Chofer();
        chofer.setId(idChofer);
        chofer.setNombre(nombre);
        chofer.setApellido(apellido);
        chofer.setTelefono(telefono);

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setIdPlaca(idPlaca);
        vehiculo.setTipoVehiculo(tipoVehiculo);
        vehiculo.setIdChofer(idChofer);

        Huella huella = new Huella();
        huella.setIdHuella(idHuella);
        huella.setIdChofer(idChofer);

        Rutas ruta = new Rutas();
        ruta.setNombreRuta(nombreRuta);
        ruta.setIdChofer(idChofer);

        try {
            boolean choferGuardado = choferDAO.insertarChofer(chofer);
            boolean vehiculoGuardado = vehiculoDAO.insertarVehiculo(vehiculo);
            boolean huellaGuardada = huellaDAO.insertarHuella(huella);
            boolean rutaGuardada = rutasDAO.insertarRuta(ruta);

            if (choferGuardado && vehiculoGuardado && huellaGuardada && rutaGuardada) {
                System.out.println("Chofer, vehículo, huella y ruta registrados exitosamente.");
            } else {
                System.out.println("Error al registrar el chofer, vehículo, huella o ruta.");
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
