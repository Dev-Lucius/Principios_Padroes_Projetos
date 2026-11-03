package dominio;

public class TextoSimples implements Texto{
    protected String texto;

    public TextoSimples(String texto) {
        this.texto = texto;
    }

	@Override
    public String renderizar() {
        return texto;
    }
}
