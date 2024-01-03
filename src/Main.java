import analisislexico.Lexico;
import analisislexico.Token;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        try {
            String programa = Files.readString(Path.of("res/programa.txt"));

            Lexico analizadorLexico = new Lexico(programa);
            Token token = analizadorLexico.getNextToken();
            while (!token.getEtiqueta().equals("end_program")) {
                System.out.println("<" + token + ">");
                token = analizadorLexico.getNextToken();
            }
            System.out.println("Fin del programa");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
