import analisislexico.Lexico;
import analisislexico.PalabrasReservadas;
import analisislexico.Token;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Texto: ");

        String textoDePrueba = entrada.nextLine();

        Lexico analizadorLexico = new Lexico(textoDePrueba);
        Token token = analizadorLexico.getNextToken();
        while (!token.getEtiqueta().equals("end_program")) {
            System.out.println("<" + token + ">");
            token = analizadorLexico.getNextToken();
        }
        System.out.println("Fin del programa");
    }
}
