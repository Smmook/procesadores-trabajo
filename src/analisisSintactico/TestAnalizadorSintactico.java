package analisisSintactico;

import analisislexico.Lexico;
import analisislexico.Token;

public class TestAnalizadorSintactico {
    public static void main(String[] args) {
        boolean mostrarComponentesLexicos = true; //poner a false y no se quieren mostrar los tokens <id, a> ...

        String expresion = "void main {int a, b, c, d; float x; int [10] v,w,y;}";

        Token etiquetaLexica;
        Lexico lexico = new Lexico(expresion);

        if(mostrarComponentesLexicos) {

            do {
                etiquetaLexica = lexico.getNextToken();
                System.out.println("<" + etiquetaLexica.toString() + ">"); //System.out.println(etiquetaLexica.toString());

            }while(!etiquetaLexica.getEtiqueta().equals("end_program"));

            System.out.println("");
        }

        AnalizadorSintactico compilador = new AnalizadorSintactico (new Lexico(expresion));

        System.out.println("CompilaciÃ³n de sentencia de declaraciones de variables");
        System.out.println(expresion + "\n");

        compilador.analisisSintactico();

        System.out.println("Tabla de sÃ­mbolos \n\n" );
        String simbolos = compilador.tablaSimbolos();
        System.out.println(simbolos);
    }
}
