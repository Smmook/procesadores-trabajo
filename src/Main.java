import analisislexico.Lexico;
import analisisSintactico.TraductorPostfijo;

public class Main {
    public static void main(String[] args) {
        //Traduccion
        String expresion = "(25 * (2 + 2)) / 2 * 3";

        TraductorPostfijo expr = new TraductorPostfijo(new Lexico(expresion));

        System.out.println("La expresioÌn " + expresion
                + " en notacioÌn postfija es " + expr.postfijo()
                + " y su valor es " + expr.valor());

        /*
        //Analisis Lexico

		Token etiquetaLexica;

		String programa ="(25*(2+2))/2*3";

		Lexico lexico = new Lexico(programa);


		System.out.println("Test lexico basico \t" + programa + "\n");

		do {

			etiquetaLexica = lexico.getNextToken();

			System.out.println(etiquetaLexica.toString());

		} while (!etiquetaLexica.getEtiqueta().equals("end_program"));
		*/
//        try {
//            String programa = Files.readString(Path.of("res/programa.txt"));
//
//            Lexico analizadorLexico = new Lexico(programa);
//            Token token = analizadorLexico.getNextToken();
//            while (!token.getEtiqueta().equals("end_program")) {
//                System.out.println("<" + token + ">");
//                token = analizadorLexico.getNextToken();
//            }
//            System.out.println("Fin del programa");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}
