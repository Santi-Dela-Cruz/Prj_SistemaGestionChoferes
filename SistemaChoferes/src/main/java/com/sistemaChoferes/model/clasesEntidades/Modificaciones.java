/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistemaChoferes.model.clasesEntidades;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author icruz
 */
public class Modificaciones {
    private int idModificaciones;
    private int idAdmin;
    private Date fechaModificacion;
    private Time horaModificacion;
    private String accionAdmin;

    public int getIdModificaciones() {
        return idModificaciones;
    }

    public void setIdModificaciones(int idModificaciones) {
        this.idModificaciones = idModificaciones;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Time getHoraModificacion() {
        return horaModificacion;
    }

    public void setHoraModificacion(Time horaModificacion) {
        this.horaModificacion = horaModificacion;
    }

    public String getAccionAdmin() {
        return accionAdmin;
    }

    public void setAccionAdmin(String accionAdmin) {
        this.accionAdmin = accionAdmin;
    }
    
}