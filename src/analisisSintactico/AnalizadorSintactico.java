package analisisSintactico;

import analisislexico.Lexico;
import analisislexico.Token;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class AnalizadorSintactico {
    private Lexico lexico;
    private Token componenteLexico;
    private Hashtable<String,String> simbolos;
    private String tipo;
    private int tamano;

    public AnalizadorSintactico(Lexico lexico) {
        this.lexico = lexico;
        this.componenteLexico = this.lexico.getNextToken();
        this.simbolos = new Hashtable<String,String>();
    }

    public void analisisSintactico() {
        programa();
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

    public void programa() {
        compara("void");
        compara("main");
        compara("open_bracket");
        declaraciones();
        compara("closed_bracket");
    }

    public void declaraciones() {
        if (this.componenteLexico.getEtiqueta().equals("int") || this.componenteLexico.getEtiqueta().equals("float")) {
            declaracion();
            declaraciones();
        }
    }

    public void declaracion() {
        tipo();
        identificadores();
        compara("semicolon");
    }


    public void tipo() {
        if(this.componenteLexico.getEtiqueta().equals("int")) {
            this.tipo = "int";
            compara("int");
        }else if(this.componenteLexico.getEtiqueta().equals("float")){
            this.tipo = "float";
            compara("float");
        }

        if (this.componenteLexico.getEtiqueta().equals("open_square_bracket")) {
            vector();
        }
    }

    public void vector() {
        compara("open_square_bracket");
        this.tamano = Integer.parseInt(this.componenteLexico.getValor());
        this.tipo = "array (" + this.tipo + ", " + this.tamano + ")";
        this.componenteLexico = this.lexico.getNextToken();
        compara("closed_square_bracket");
    }


    public void identificadores() {
        simbolos.put(this.componenteLexico.getValor(), this.tipo);
        this.componenteLexico = this.lexico.getNextToken();
        masIdentificadores();
    }

    public void masIdentificadores() {
        if (this.componenteLexico.getEtiqueta().equals("comma")) {
            compara("comma");
            identificadores();
        }
    }


    public void compara(String token) {
        if(this.componenteLexico.getEtiqueta().equals(token)) {
            this.componenteLexico = this.lexico.getNextToken();
        }else {
            System.out.println("Expected: " + token);
        }
    }
}
