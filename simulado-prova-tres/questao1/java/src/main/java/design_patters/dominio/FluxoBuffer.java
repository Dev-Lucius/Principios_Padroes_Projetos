package design_patters.dominio;

public class FluxoBuffer extends DecoratorFluxo {

    public FluxoBuffer(FluxoDados fluxo) {
        super(fluxo);
    }

    @Override
    public void escrever(String dados) {
        String buffered = "[BUFFER]" + dados;

        fluxo.escrever(buffered);
    }
}
