package analisisSintactico;

import analisislexico.Lexico;
import analisislexico.Token;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class AnalizadorSintacticoP4 {
    private Lexico lexico;
    private Token componenteLexico;
    private Hashtable<String,String> simbolos;
    private String tipo;
    private int tamano;

    public AnalizadorSintacticoP4(Lexico lexico) {
        this.lexico = lexico;
        this.componenteLexico = this.lexico.getNextToken();
        this.simbolos = new Hashtable<String,String>();
    }

    public void analisisSintactico() {
        declaraciones();
    }


    public void declaraciones() {
        if (this.componenteLexico.getEtiqueta().equals("int") || this.componenteLexico.getEtiqueta().equals("float")) {
            declaracion();
            declaraciones();
        }
    }

    public void declaracion() {
        if (this.componenteLexico.getEtiqueta().equals("int") || this.componenteLexico.getEtiqueta().equals("float")) {
            tipo();
            identificadores();
            compara("semicolon");
        }
    }


    public void tipo() {
        if (this.componenteLexico.getEtiqueta().equals("int") || this.componenteLexico.getEtiqueta().equals("float")) {
            this.tipo = this.componenteLexico.getEtiqueta();
            this.componenteLexico = this.lexico.getNextToken();
        } else {
            System.out.println("Error en el tipo: " + this.componenteLexico.toString());
        }
    }


    public void identificadores() {
        if (this.componenteLexico.getEtiqueta().equals("id")) {
            simbolos.put(this.componenteLexico.getValor(), this.tipo);
            this.componenteLexico = this.lexico.getNextToken();
            masIdentificadores();
        } else {
            System.out.println("Error en el ident: " + this.componenteLexico.toString());
        }
    }

    public void masIdentificadores() {
        if (this.componenteLexico.getEtiqueta().equals("comma")) {
            compara("comma");
            simbolos.put(this.componenteLexico.getValor(), this.tipo);
            this.componenteLexico = this.lexico.getNextToken();
            masIdentificadores();
        }
    }


    public void compara(String token) {
        if(this.componenteLexico.getEtiqueta().equals(token)) {
            this.componenteLexico = this.lexico.getNextToken();
        }else {
            System.out.println("Expected: " + token);
        }
    }

    public String tablaSimbolos() {
        String simbolos = "";

        Set<Map.Entry<String, String>> s = this.simbolos.entrySet();
        if(s.isEmpty()) System.out.println("La tabla de simbolos esta vacia\n");
        for(Map.Entry<String, String> m : s) {
            simbolos = simbolos + "<'" + m.getKey() + "', " +
                    m.getValue() + "> \n";
        }

        return simbolos;
    }
}
