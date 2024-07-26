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
public class Administrador {
    
    private int idAdministrador;
    private String nombres;
    private String apellidos;
    private String correoAdmin;
    private String contrasenaAdmin;
    private String estadoAdmin;
    private String cargoAdmin;
    private Date fechaIngreso;
    private Time horaIngreso;
    private String telfono;
    private String direccion;

    public String getTelfono() {
        return telfono;
    }

    public void setTelfono(String telfono) {
        this.telfono = telfono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCargoAdmin() {
        return cargoAdmin;
    }

    public void setCargoAdmin(String cargoAdmin) {
        this.cargoAdmin = cargoAdmin;
    }
    

    public String getContrasenaAdmin() {
        return contrasenaAdmin;
    }

    public void setContrasenaAdmin(String contrasenaAdmin) {
        this.contrasenaAdmin = contrasenaAdmin;
    }
    
    public int getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreoAdmin() {
        return correoAdmin;
    }

    public void setCorreoAdmin(String correoAdmin) {
        this.correoAdmin = correoAdmin;
    }

    public String getEstadoAdmin() {
        return estadoAdmin;
    }

    public void setEstadoAdmin(String estadoAdmin) {
        this.estadoAdmin = estadoAdmin;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Time getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(Time horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public void setRol(String administrador) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}