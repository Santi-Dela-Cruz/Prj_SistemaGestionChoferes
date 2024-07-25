package com.sistemaChoferes.model.src.clasesEntidades;

import java.sql.Date;

public class Chofer {
    private int idChofer;
    private String idCedula;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private String dirreccion;
    private String categoriaLicencia;
    private java.sql.Date fechaVenciminetoLicencia;

    public int getIdChofer() {
        return idChofer;
    }

    public void setIdChofer(int idChofer) {
        this.idChofer = idChofer;
    }

    public String getIdCedula() {
        return idCedula;
    }

    public void setIdCedula(String idCedula) {
        this.idCedula = idCedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDirreccion() {
        return dirreccion;
    }

    public void setDirreccion(String dirreccion) {
        this.dirreccion = dirreccion;
    }

    public String getCategoriaLicencia() {
        return categoriaLicencia;
    }

    public void setCategoriaLicencia(String categoriaLicencia) {
        this.categoriaLicencia = categoriaLicencia;
    }
    
    public java.sql.Date getFechaVenciminetoLicencia() {
        return fechaVenciminetoLicencia;
    }

    public void setFechaVenciminetoLicencia(java.sql.Date fechaVenciminetoLicencia) {
        this.fechaVenciminetoLicencia = fechaVenciminetoLicencia;
    }

}
