package dominio;

public class PagSeguroAdapter implements GatewayPagamento{

    private final PagSeguroAPI api;

    public PagSeguroAdapter(PagSeguroAPI api){
        this.api = api;
    }

    @Override
    public boolean pagar(double valor, String nrmCartao) {
        int valorEmCentavos = (int)Math.round(valor * 100);
        api.efetuarCobranca(nrmCartao, valorEmCentavos);
        return true;
    }
    
}
