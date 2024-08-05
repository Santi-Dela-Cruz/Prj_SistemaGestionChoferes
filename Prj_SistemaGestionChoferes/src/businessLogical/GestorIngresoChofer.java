package businessLogical;

import dataAccesComponent.dao.RegistroEstadosDAO;
import dataAccesComponent.dao.RegistroPenalizacionesDAO;
import dataAccesComponent.entity.RegistroEstados;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class GestorIngresoChofer {

    private RegistroEstadosDAO registroEstadosDAO = new RegistroEstadosDAO();
    private RegistroPenalizacionesDAO registroPenalizacionesDAO = new RegistroPenalizacionesDAO();

    public void registrarIngreso(int idChofer, boolean estaBorracho) {
        try {
            LocalDate fechaIngreso = LocalDate.now();
            LocalTime horaIngreso = LocalTime.now();

            RegistroEstados registroEstado = new RegistroEstados();
            registroEstado.setIdChofer(idChofer);
            registroEstado.setFechaIngreso(Date.valueOf(fechaIngreso));
            registroEstado.setHoraIngreso(Time.valueOf(horaIngreso));
            registroEstado.setEstadoChofer(estaBorracho ? "Borracho" : "Sobrio");
            registroEstado.setAutorizacionChofer(!estaBorracho);
            registroEstadosDAO.create(registroEstado);

            if (estaBorracho) {
                boolean despedido = registroPenalizacionesDAO.actualizarPenalizaciones(idChofer);
                if (despedido) {
                    System.out.println("El chofer ha sido despedido.");
                } else {
                    System.out.println("Penalización registrada.");
                }
            }

            System.out.println("Ingreso registrado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al registrar el ingreso: " + e.getMessage());
        }
    }
}
