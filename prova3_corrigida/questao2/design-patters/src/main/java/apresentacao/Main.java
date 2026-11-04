package apresentacao;

import dominio.BibliotecaPagamentoAntAdapter;
import dominio.BibliotecaPagamentoAntiga;
import dominio.NovoGatewayPagamento;
import dominio.Checkout;

public class Main {
    public static void main(String[] args) {
        // System.out.println("Hello world!");

        NovoGatewayPagamento pagNovo = new BibliotecaPagamentoAntAdapter(new BibliotecaPagamentoAntiga());
        Checkout checkout = new Checkout(pagNovo);

        checkout.finalizarCompra(1500.00, "001");
    }
}