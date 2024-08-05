package dataAccesComponent.entity;

import java.util.Date;

public class Modificaciones {
    private int id;
    private int idAdministrador;
    private int idChoferModificacion;
    private Date fechaModificacion;
    private String horaModificacion;
    private String accionAdmin;

    public int getIdChoferModificacion() {
        return idChoferModificacion;
    }

    public void setIdChoferModificacion(int idChoferModificacion) {
        this.idChoferModificacion = idChoferModificacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getHoraModificacion() {
        return horaModificacion;
    }

    public void setHoraModificacion(String horaModificacion) {
        this.horaModificacion = horaModificacion;
    }

    public String getAccionAdmin() {
        return accionAdmin;
    }

    public void setAccionAdmin(String accionAdmin) {
        this.accionAdmin = accionAdmin;
    }
}
