import analisislexico.PalabrasReservadas;

public class Main {
    public static void main(String[] args) {
        PalabrasReservadas keywords = new PalabrasReservadas("res/lexico.txt");

        System.out.println(keywords.getTipo("=="));
    }
}
