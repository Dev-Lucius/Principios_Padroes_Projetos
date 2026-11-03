package dominio;

public class Italico extends DecoradorTexto{

    public Italico(Texto texto){
        super(texto);
    }

    @Override
    public String renderizar() {
        return "<i>" + texto.renderizar() + "</i>";
    }
    
}
