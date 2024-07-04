
import funcionesGestiosChoferes.RegistroChofer;
import funcionesGestiosChoferes.RegistroIngreso;

public class App {
    public static void main(String[] args) throws Exception {
        RegistroChofer registroChofer = new RegistroChofer();
        RegistroIngreso registroIngreso = new RegistroIngreso();


        registroIngreso.validarHuellaYRegistrarIngreso();


    }
}
