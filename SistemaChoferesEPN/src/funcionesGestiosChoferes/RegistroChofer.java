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

        System.out.print("Ingrese el ID de la cédula del chofer: ");
        String idCedula = scanner.nextLine();

        System.out.print("Ingrese el nombre del chofer: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el apellido del chofer: ");
        String apellido = scanner.nextLine();

        System.out.print("Ingrese el teléfono del chofer: ");
        String telefono = scanner.nextLine();

        System.out.print("Ingrese el ID de la huella: ");
        String idCodigoHuella = scanner.nextLine();
        
        System.out.print("Ingrese la placa del vehículo: ");
        String idPlaca = scanner.nextLine();

        System.out.print("Ingrese el tipo de vehículo: ");
        String tipoVehiculo = scanner.nextLine();

        System.out.print("Ingrese la ruta del vehículo: ");
        String nombreRuta = scanner.nextLine();

        Chofer chofer = new Chofer();
        chofer.setIdCedula(idCedula);
        chofer.setNombre(nombre);
        chofer.setApellido(apellido);
        chofer.setTelefono(telefono);

        choferDAO.agregarChofer(chofer);

        int idChofer = choferDAO.obtenerTodoChoferes().stream()
                .filter(c -> c.getIdCedula().equals(idCedula))
                .findFirst()
                .get()
                .getIdChofer();

        if (huellaDAO.existeHuella(idChofer) || 
            vehiculoDAO.existeVehiculo(idChofer) || 
            rutasDAO.existeRuta(idChofer)) {
            System.out.println("Error: Existen registros duplicados para el chofer, vehículo, huella o ruta.");
            return;
        }

        Huella huella = new Huella();
        huella.setIdCodigoHuella(idCodigoHuella);
        huella.setIdChofer(idChofer);
        huellaDAO.agregarHuella(huella);

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setIdPlaca(idPlaca);
        vehiculo.setTipoVehiculo(tipoVehiculo);
        vehiculo.setIdChofer(idChofer);
        vehiculoDAO.agregarVehiculo(vehiculo);

        Rutas ruta = new Rutas();
        ruta.setNombreRuta(nombreRuta);
        ruta.setIdChofer(idChofer);
        rutasDAO.agregarRuta(ruta);

        System.out.println("Registro completado con éxito.");
    }
}
