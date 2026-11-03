package dominio;

public abstract class DecoradorFluxoDados implements FluxoDados{
    protected FluxoDados fluxo;

    public DecoradorFluxoDados(FluxoDados fluxo){
        this.fluxo = fluxo;
    }

    @Override
    public void escrever(String dados) {
        fluxo.escrever(dados);
    }
}
