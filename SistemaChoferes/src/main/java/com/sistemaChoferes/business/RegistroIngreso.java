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
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class RegistroIngreso {

    private ChoferDAO choferDAO = new ChoferDAO();
    private RegistroEstadosDAO registroEstadosDAO = new RegistroEstadosDAO();
    private RegistroPenalizacionesDAO registroPenalizacionesDAO = new RegistroPenalizacionesDAO();
    private VehiculoDAO vehiculoDAO = new VehiculoDAO();
    private RutasDAO rutasDAO = new RutasDAO();

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
