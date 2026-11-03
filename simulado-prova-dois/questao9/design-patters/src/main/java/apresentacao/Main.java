package apresentacao;

import dominio.Checkout;
import dominio.GatewayPagamento;
import dominio.MercadoPagoAdapter;
import dominio.MercadoPagoSDK;
import dominio.PagSeguroAPI;
import dominio.PagSeguroAdapter;

public class Main {

    public static void main(String[] args) {

        // =================================
        // PAGAMENTO COM PAGSEGURO
        // =================================

        GatewayPagamento pagSeguro =
            new PagSeguroAdapter(new PagSeguroAPI());

        Checkout checkout = new Checkout(pagSeguro);

        checkout.finalizarCompra(
            150.00,
            "1234567890123456"
        );


        // =================================
        // PAGAMENTO COM MERCADO PAGO
        // =================================

        GatewayPagamento mercadoPago =
            new MercadoPagoAdapter(new MercadoPagoSDK());

        checkout = new Checkout(mercadoPago);

        checkout.finalizarCompra(
            150.00,
            "1234567890123456"
        );

    }
}