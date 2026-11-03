package dominio;

public abstract class DecoradorTexto implements Texto{
    protected Texto texto;

    public DecoradorTexto(Texto texto){
        this.texto = texto;
    }
}
