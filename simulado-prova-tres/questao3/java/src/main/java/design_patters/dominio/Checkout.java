package design_patters.dominio;

public class Checkout {

    private final GatewayPagamento gateway;

    public Checkout(GatewayPagamento gateway) {
        this.gateway = gateway;
    }

    public void finalizarCompra(double valor, String numeroCartao) {

        System.out.println("\nIniciando pagamento...");

        boolean aprovado = gateway.pagar(valor, numeroCartao);

        if (aprovado) {
            System.out.println("Pagamento aprovado!");
        } else {
            System.out.println("Pagamento recusado!");
        }

    }

}