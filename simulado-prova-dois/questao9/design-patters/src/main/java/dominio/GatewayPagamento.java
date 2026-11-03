package dominio;

public interface GatewayPagamento {
    boolean pagar(double valor, String nrmCartao);
}
