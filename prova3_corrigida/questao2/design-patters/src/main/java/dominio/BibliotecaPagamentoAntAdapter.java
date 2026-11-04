package dominio;

public class BibliotecaPagamentoAntAdapter implements NovoGatewayPagamento{

    private final BibliotecaPagamentoAntiga api;

    public BibliotecaPagamentoAntAdapter(BibliotecaPagamentoAntiga api){
        this.api = api;
    }

    @Override
    public boolean processarPagamento(double valor, String conta) {
        int valorEmCentavos = (int)Math.round(valor * 100);
        api.executarTransacao(valorEmCentavos);
        return true;
    }

}
