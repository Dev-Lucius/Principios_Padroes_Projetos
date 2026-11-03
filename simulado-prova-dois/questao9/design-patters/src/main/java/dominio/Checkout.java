package dominio;

public class Checkout {

    private final GatewayPagamento gateway;

    public Checkout(GatewayPagamento gateway) {

        this.gateway = gateway;

    }

    public boolean finalizarCompra(
            double valor,
            String numeroCartao) {

        System.out.println("\nIniciando pagamento...");

        try {

            boolean aprovado = gateway.pagar(valor, numeroCartao);

            if (aprovado) {

                System.out.println("Pagamento aprovado!");

            } else {

                System.out.println("Pagamento recusado!");

            }

            return aprovado;

        } catch (RuntimeException e) {

            System.out.println(
                "Erro ao processar pagamento: " + e.getMessage()
            );

            return false;

        }
    }
}