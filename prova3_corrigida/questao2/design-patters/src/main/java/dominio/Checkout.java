package dominio;

public class Checkout {

    private final NovoGatewayPagamento gateway;

    public Checkout(NovoGatewayPagamento gateway) {

        this.gateway = gateway;

    }

    public boolean finalizarCompra(double valor, String conta) {

        System.out.println("\nIniciando pagamento...");

        try {

            boolean aprovado = gateway.processarPagamento(valor, conta);

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