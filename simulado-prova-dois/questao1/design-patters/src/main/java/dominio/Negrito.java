package dominio;

public class Negrito extends DecoradorTexto{
    public Negrito(Texto texto){
        super(texto);
    }

    @Override
    public String renderizar(){
        return "<b>" + texto.renderizar() + "</b>";
    }
}
