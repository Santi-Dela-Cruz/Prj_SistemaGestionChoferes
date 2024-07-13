import funcionesGestiosChoferes.RegistroChofer;
//import funcionesGestiosChoferes.RegistroIngreso;
//import objetoAccesoDatos.LimpiarTabla;

public class App {
    public static void main(String[] args) throws Exception {
        RegistroChofer registroChofer = new RegistroChofer();
        //RegistroIngreso registroIngreso = new RegistroIngreso();
        //LimpiarTabla limpiarTabla = new LimpiarTabla();

        registroChofer.registrarNuevoChofer();
    }
}
