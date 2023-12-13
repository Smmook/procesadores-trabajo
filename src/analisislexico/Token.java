package analisislexico;

public class Token {
    private final String etiqueta;
    private final String valor;

    public Token(String etiqueta, String valor) {
        this.etiqueta = etiqueta;
        this.valor = valor;
    }

    public Token(String etiqueta) {
        this.etiqueta = etiqueta;
        this.valor = "";
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString() {
        if (valor.isEmpty())
            return etiqueta;
        else
            return etiqueta + ", " + valor;
    }
}
