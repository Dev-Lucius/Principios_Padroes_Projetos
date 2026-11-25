package design_patters.dominio;

public abstract class DecoratorFluxo implements FluxoDados{

    protected final FluxoDados fluxo;

    protected DecoratorFluxo(FluxoDados fluxo){
        this.fluxo = fluxo;
    }
}
