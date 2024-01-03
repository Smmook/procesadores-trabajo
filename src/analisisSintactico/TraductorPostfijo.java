package analisisSintactico;

import analisislexico.Lexico;
import analisislexico.Token;

import java.util.Stack;

public class TraductorPostfijo {
    private Token token;
    private Lexico lexico;
    private Stack<String> pila;
    private String postfijo;

    public TraductorPostfijo(Lexico lexico) {
        this.lexico = lexico;
        this.token = this.lexico.getNextToken();
        this.pila = new Stack<String>();
        this.postfijo = "";
    }

    public String postfijo() {
        expresion();
        // si se hace con print en vez de con pila, no se necesita este while
        while (!pila.isEmpty()) {
            postfijo += pila.remove(0) + " ";
        }
        return postfijo;
    }

    private void expresion() {
        termino();
        masTerminos();
    }

    public void factor() {
        if (this.token.getEtiqueta().equals("open_parenthesis")) {
            compara("open_parenthesis");
            expresion();
            compara("closed_parenthesis");
        } else if (this.token.getEtiqueta().equals("int")) {
            pila.push(this.token.getValor());
            compara("int");
        } else {
            System.out.println("ERROR. Se esperaba un factor");
        }
    }

    public void termino() {
        factor();
        masFactores();
    }

    private void masTerminos() {
        if (this.token.getEtiqueta().equals("add")) {
            compara("add");
            termino();
            pila.push("+");
            masTerminos();
        } else if (this.token.getEtiqueta().equals("substract")) {
            compara("substract");
            termino();
            pila.push("-");
            masTerminos();
        }
    }

    private void masFactores() {
        if (this.token.getEtiqueta().equals("multiply")) {
            compara("multiply");
            factor();
            pila.push("*");
            masFactores();
        } else if (this.token.getEtiqueta().equals("divide")) {
            compara("divide");
            factor();
            pila.push("/");
            masFactores();
        }
    }

    //compara se utiliza cada vez que, segÃºn la gramÃ¡tica, te encuentras con una etiqueta lexica
    //como: add, substract, open_parenthesis ...
    public void compara(String etiquetaLexica) {
        if (this.token.getEtiqueta().equals(etiquetaLexica)) {
            this.token = lexico.getNextToken();
        } else {
            System.out.println("ERROR. Se esperaba " + etiquetaLexica);
        }
    }

    // parq calcular el valor de esta expresiÃ³n
    public int valor() {
        Stack<Integer> pilaValor = new Stack<>();
        String[] tokens = postfijo.trim().split("\\s+"); // Dividir por espacios

        for (String token : tokens) {
            if (isNumeric(token)) {
                pilaValor.push(Integer.parseInt(token));
            } else {
                int num2 = pilaValor.pop(); // Segundo nÃºmero
                int num1 = pilaValor.pop(); // Primer nÃºmero
                switch (token) {
                    case "+":
                        pilaValor.push(num1 + num2);
                        break;
                    case "-":
                        pilaValor.push(num1 - num2);
                        break;
                    case "*":
                        pilaValor.push(num1 * num2);
                        break;
                    case "/":
                        if (num2 != 0) {
                            pilaValor.push(num1 / num2);
                        } else {
                            System.out.println("ERROR: DivisiÃ³n por cero.");
                            return -1; // O cualquier otro valor de error.
                        }
                        break;
                    default:
                        System.out.println("ERROR: Operador desconocido " + token);
                        return -1; // O cualquier otro valor de error.
                }
            }
        }

        return pilaValor.pop();
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
