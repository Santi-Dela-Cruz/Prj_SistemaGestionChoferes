package clasesEntidades;
import java.sql.Date;
import java.sql.Time;

public class RegistroEstados {
    private String estadoChofer;
    private boolean autorizacionChofer;
    private String idChofer;
    private Time horaIngreso;
    private Date fechaIngreso;

    public String getEstadoChofer() {
        return estadoChofer;
    }

    public void setEstadoChofer(String estadoChofer) {
        this.estadoChofer = estadoChofer;
    }

    public boolean isAutorizacionChofer() {
        return autorizacionChofer;
    }

    public void setAutorizacionChofer(boolean autorizacionChofer) {
        this.autorizacionChofer = autorizacionChofer;
    }

    public String getIdChofer() {
        return idChofer;
    }

    public void setIdChofer(String idChofer) {
        this.idChofer = idChofer;
    }

    public Time getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(Time horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
}

