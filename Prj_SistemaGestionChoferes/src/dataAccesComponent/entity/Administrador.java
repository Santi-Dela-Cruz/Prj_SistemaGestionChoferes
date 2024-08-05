package dataAccesComponent.entity;

import java.sql.Time;
import java.util.Date;

public class Administrador {

    private int idAdministrador;
    private String usuario;
    private String nombres;
    private String apellidos;
    private String correoAdmin;
    private String contrasenaAdmin;
    private String estadoAdmin;
    private String cargoAdmin;
    private Date fechaIngreso;
    private Time horaIngreso;
    private String telefono;
    private String direccion;
    private String estado;

    public int getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

    public String getContrasenaAdmin() {
        return contrasenaAdmin;
    }

    public void setContrasenaAdmin(String contrasenaAdmin) {
        this.contrasenaAdmin = contrasenaAdmin;
    }

    public String getEstadoAdmin() {
        return estadoAdmin;
    }

    public void setEstadoAdmin(String estadoAdmin) {
        this.estadoAdmin = estadoAdmin;
    }

    public String getCargoAdmin() {
        return cargoAdmin;
    }

    public void setCargoAdmin(String cargoAdmin) {
        this.cargoAdmin = cargoAdmin;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
