package clasesEntidades;

public class RegistroPenalizaciones {
    private String idChofer;
    private int nInfracciones;
    private String penalizacionChofer;

    public String getIdChofer() {
        return idChofer;
    }

    public void setIdChofer(String idChofer) {
        this.idChofer = idChofer;
    }

    public int getNInfracciones() {
        return nInfracciones;
    }

    public void setNInfracciones(int nInfracciones) {
        this.nInfracciones = nInfracciones;
    }

    public String getPenalizacionChofer() {
        return penalizacionChofer;
    }

    public void setPenalizacionChofer(String penalizacionChofer) {
        this.penalizacionChofer = penalizacionChofer;
    }
}

