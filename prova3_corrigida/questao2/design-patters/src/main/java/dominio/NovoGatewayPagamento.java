package dominio;

public interface NovoGatewayPagamento {
    boolean processarPagamento(double valor, String conta);
}
