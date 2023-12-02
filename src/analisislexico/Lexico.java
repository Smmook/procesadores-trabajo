package analisislexico;

// Clase escaner
public class Lexico {
    private final String texto;
    private final PalabrasReservadas keywords;
    private int posicion;
    private int lineas;
    private char caracter;

    public Lexico(String texto) {
        this.texto = texto + '\0';
        this.keywords = new PalabrasReservadas("res/lexico.txt");
        this.posicion = 0;
        this.lineas = 1;
    }

    public int getLineas() {
        return lineas;
    }

    private boolean esElFinal() {
        return posicion >= texto.length();
    }

    private void nextChar() {
        this.caracter = this.texto.charAt(posicion);
        posicion++;
    }

    private boolean esNoSignificativo(char c) {
        return (c == ' ' || c == '\t' || c == '\r' || c == '\n');
    }

    private void pasarAlSiguienteCaracterSignificativo() {
        while (esNoSignificativo(caracter)) {
            if (caracter == '\n') {
                lineas++;
            }
            nextChar();
        }
    }
}
