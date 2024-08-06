package framework;

public class Validaciones {

    public boolean validarBanderas(int longitud, String palabra) {
        for (int i = 0; i < longitud; i++) {
            if (palabra.charAt(i) == ' ') {
                return false;
            }
        }
        return true;
    }



    public boolean validarNumeros(int longitud, String palabra) {
        for (int i = 0; i < longitud; i++) {
            if (!Character.isDigit(palabra.charAt(i))) {
                return false;
            }
        }
        return true;
    }





}

