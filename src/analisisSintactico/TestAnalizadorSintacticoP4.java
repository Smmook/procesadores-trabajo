package analisisSintactico;

import analisislexico.Lexico;
import analisislexico.Token;

public class TestAnalizadorSintacticoP4 {
    public static void main(String[] args) {
        boolean mostrarComponentesLexicos = false; //poner a false y no se quieren mostrar los tokens <id, a> ...

        String expresion = "int a, b, c, d; float x, y, z;";

        Token etiquetaLexica;
        Lexico lexico = new Lexico(expresion);

        if(mostrarComponentesLexicos) {

            do {
                etiquetaLexica = lexico.getNextToken();
                System.out.println("<" + etiquetaLexica.toString() + ">"); //System.out.println(etiquetaLexica.toString());

            }while(!etiquetaLexica.getEtiqueta().equals("end_program"));

            System.out.println("");
        }

        AnalizadorSintacticoP4 compilador = new AnalizadorSintacticoP4(new Lexico(expresion));

        System.out.println("CompilaciÃ³n de sentencia de declaraciones de variables");
        System.out.println(expresion + "\n");

        compilador.analisisSintactico();

        System.out.println("Tabla de sÃ­mbolos \n\n" );
        String simbolos = compilador.tablaSimbolos();
        System.out.println(simbolos);
    }
}
