package com.sistemaChoferes.business;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.sistemaChoferes.dataacess.entity.Chofer;
import com.sistemaChoferes.dataacess.entity.Huella;
import com.sistemaChoferes.dataacess.entity.Rutas;
import com.sistemaChoferes.dataacess.entity.Vehiculo;
import com.sistemaChoferes.dataacess.dao.ChoferDAO;
import com.sistemaChoferes.dataacess.dao.HuellaDAO;
import com.sistemaChoferes.dataacess.dao.RegistroEstadosDAO;
import com.sistemaChoferes.dataacess.dao.RegistroPenalizacionesDAO;
import com.sistemaChoferes.dataacess.dao.RutasDAO;
import com.sistemaChoferes.dataacess.dao.VehiculoDAO;
import com.sistemaChoferes.dataacess.entity.RegistroEstados;

public class RegistroIngreso {

    private ChoferDAO choferDAO = new ChoferDAO();
    private RegistroEstadosDAO registroEstadosDAO = new RegistroEstadosDAO();
    private RegistroPenalizacionesDAO registroPenalizacionesDAO = new RegistroPenalizacionesDAO();
    private VehiculoDAO vehiculoDAO = new VehiculoDAO();
    private RutasDAO rutasDAO = new RutasDAO();

    public void registrarIngreso(String idCedula) {
        try {
            Chofer chofer = choferDAO.obtenerChoferPorCedula(idCedula);
            if (chofer == null) {
                throw new RuntimeException("Error: El chofer no existe.");
            }

            int idChofer = chofer.getIdChofer();
            Vehiculo vehiculo = vehiculoDAO.obtenerVehiculoPorChoferId(idChofer);
            Rutas ruta = rutasDAO.readBy(idChofer);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String fechaHoraIngreso = LocalDateTime.now().format(formatter);

            System.out.println("Detalles del chofer:");
            System.out.println("ID Cédula: " + chofer.getIdCedula());
            System.out.println("Nombre: " + chofer.getNombre());
            System.out.println("Apellido: " + chofer.getApellido());
            System.out.println("Teléfono: " + chofer.getTelefono());
            System.out.println("Placa del Vehículo: " + vehiculo.getIdPlaca());
            System.out.println("Tipo de Vehículo: " + vehiculo.getTipoVehiculo());
            System.out.println("Ruta: " + ruta.getNombreRuta());
            System.out.println("Fecha y Hora de Ingreso: " + fechaHoraIngreso);

            Scanner scanner = new Scanner(System.in);
            System.out.print("¿Desea realizar el test de alcohol? (si/no): ");
            String realizarTest = scanner.nextLine();

            if (!realizarTest.equalsIgnoreCase("si")) {
                return;
            }

            System.out.print("¿Está borracho? (si/no): ");
            String respuesta = scanner.nextLine();
            boolean autorizacion = !respuesta.equalsIgnoreCase("si");

            RegistroEstados registroEstado = new RegistroEstados();
            registroEstado.setIdChofer(idChofer);
            registroEstado.setEstadoChofer(respuesta.equalsIgnoreCase("si") ? "Borracho" : "Sobrio");
            registroEstado.setAutorizacionChofer(autorizacion);
            registroEstadosDAO.create(registroEstado);

            if (!autorizacion) {
                boolean despedido = registroPenalizacionesDAO.actualizarPenalizaciones(idChofer);
                if (despedido) {
                    System.out.println("El chofer ha sido despedido.");
                } else {
                    System.out.println("Penalización registrada.");
                }
            }

            System.out.println("Resultado del test de alcohol:");
            System.out.println("Nombre: " + chofer.getNombre());
            System.out.println("Autorización: " + (autorizacion ? "Autorizado" : "No autorizado"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al registrar el ingreso: " + e.getMessage());
        }
    }
}
