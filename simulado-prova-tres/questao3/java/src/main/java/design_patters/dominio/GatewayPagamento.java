package design_patters.dominio;

public interface GatewayPagamento {
    boolean pagar(double valor, String numeroCartao);
}
