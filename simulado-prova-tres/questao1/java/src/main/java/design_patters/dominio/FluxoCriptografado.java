package design_patters.dominio;

public class FluxoCriptografado extends DecoratorFluxo{

    public FluxoCriptografado(FluxoDados fluxo) {
        super(fluxo);
    }

    @Override
    public void escrever(String dados) {
        String criptografado = new StringBuilder(dados)
                .reverse()
                .toString();

        fluxo.escrever(criptografado);
    }
}
