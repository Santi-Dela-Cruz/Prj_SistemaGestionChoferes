package dataAccesComponent.entity;

import java.util.Date;

public class Huella {
    private int idHuella;
    private String idCodigoHuella;
    private Date fechaRegHuella;
    private int idChofer;
    private String estado;

    public int getIdHuella() {
        return idHuella;
    }

    public void setIdHuella(int idHuella) {
        this.idHuella = idHuella;
    }

    public String getIdCodigoHuella() {
        return idCodigoHuella;
    }

    public void setIdCodigoHuella(String idCodigoHuella) {
        this.idCodigoHuella = idCodigoHuella;
    }

    public Date getFechaRegHuella() {
        return fechaRegHuella;
    }

    public void setFechaRegHuella(Date fechaRegHuella) {
        this.fechaRegHuella = fechaRegHuella;
    }

    public int getIdChofer() {
        return idChofer;
    }

    public void setIdChofer(int idChofer) {
        this.idChofer = idChofer;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
