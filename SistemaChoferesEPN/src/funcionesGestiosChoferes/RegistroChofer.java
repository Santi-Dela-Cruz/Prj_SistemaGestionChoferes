package funcionesGestiosChoferes;

import clasesEntidades.Chofer;
import clasesEntidades.Huella;
import clasesEntidades.Vehiculo;
import java.util.Scanner;
import objetoAccesoDatos.ChoferDAO;
import objetoAccesoDatos.HuellaDAO;
import objetoAccesoDatos.RegistroEstadosDAO;
import objetoAccesoDatos.RegistroPenalizacionesDAO;
import objetoAccesoDatos.VehiculoDAO;

public class RegistroChofer {

    private HuellaDAO huellaDAO;
    private ChoferDAO choferDAO;
    private VehiculoDAO vehiculoDAO;
    private RegistroEstadosDAO registroEstadosDAO;
    private RegistroPenalizacionesDAO registroPenalizacionesDAO;

    public RegistroChofer() {
        huellaDAO = new HuellaDAO();
        choferDAO = new ChoferDAO();
        vehiculoDAO = new VehiculoDAO();
        registroEstadosDAO = new RegistroEstadosDAO();
        registroPenalizacionesDAO = new RegistroPenalizacionesDAO();
    }

    public void validarHuellaYRegistrarIngreso() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el ID de la huella: ");
        String idHuella = scanner.nextLine();

        Huella huella = huellaDAO.getHuellaById(idHuella);
        if (huella == null) {
            System.out.println("Huella no encontrada.");
            return;
        }

        String idChofer = huella.getIdChofer();
        Chofer chofer = choferDAO.getChoferById(idChofer);

        if (chofer == null) {
            System.out.println("Chofer no encontrado.");
            return;
        }

        Vehiculo vehiculo = vehiculoDAO.getVehiculoByChoferId(idChofer);

        if (vehiculo == null) {
            System.out.println("Vehículo no encontrado.");
            return;
        }

        
        System.out.println("Detalles del chofer:");
        System.out.println("ID: " + chofer.getId());
        System.out.println("Nombre: " + chofer.getNombre());
        System.out.println("Apellido: " + chofer.getApellido());
        System.out.println("Teléfono: " + chofer.getTelefono());
        System.out.println("Placa del Vehículo: " + vehiculo.getIdPlaca());
        System.out.println("Tipo de Vehículo: " + vehiculo.getTipoVehiculo());

        
        boolean successEstado = registroEstadosDAO.registrarIngreso(idChofer, "Sobrio", true);
        if (successEstado) {
            System.out.println("Fecha y hora de ingreso registradas: " + java.time.LocalDate.now() + " " + java.time.LocalTime.now());
        } else {
            System.out.println("Error al registrar fecha y hora de ingreso.");
            return;
        }

        
        System.out.print("¿Desea realizar el test de alcohol? (si/no): ");
        String realizarTest = scanner.nextLine();

        if (!realizarTest.equalsIgnoreCase("si")) {
            return;
        }

        System.out.print("¿Está borracho? (si/no): ");
        String respuesta = scanner.nextLine();
        boolean autorizacion = !respuesta.equalsIgnoreCase("si");

        successEstado = registroEstadosDAO.registrarIngreso(idChofer, respuesta.equalsIgnoreCase("si") ? "Borracho" : "Sobrio", autorizacion);
        if (successEstado) {
            System.out.println("Estado registrado correctamente.");
        } else {
            System.out.println("Error al registrar el estado.");
            return;
        }

        if (!autorizacion) {
            boolean despedido = registroPenalizacionesDAO.actualizarPenalizaciones(idChofer, "Infracción");
            if (despedido) {
                System.out.println("El chofer ha sido despedido.");
            } else {
                System.out.println("Penalización registrada.");
            }
        }

        System.out.println("Resultado del test de alcohol:");
        System.out.println("Nombre: " + chofer.getNombre());
        System.out.println("Autorización: " + (autorizacion ? "Autorizado" : "No autorizado"));
    }
}
