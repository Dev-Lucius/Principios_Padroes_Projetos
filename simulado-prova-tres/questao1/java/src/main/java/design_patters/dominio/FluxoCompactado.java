package design_patters.dominio;

public class FluxoCompactado extends DecoratorFluxo{

    public FluxoCompactado(FluxoDados fluxo) {
        super(fluxo);
    }

    @Override
    public void escrever(String dados) {
        String compactado = "[ZIP]" + dados;
        fluxo.escrever(compactado);
    }

}
