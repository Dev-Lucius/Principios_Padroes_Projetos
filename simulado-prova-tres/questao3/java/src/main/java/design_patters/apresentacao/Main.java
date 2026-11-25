package design_patters.apresentacao;

import design_patters.dominio.Checkout;
import design_patters.dominio.MercadoPagoAdapter;
import design_patters.dominio.MercadoPagoSDK;
import design_patters.dominio.PagSeguroAPI;
import design_patters.dominio.PagSeguroAdapter;

public class Main {
    public static void main(String[] args) {
        // System.out.println("Hello world!");
       
        // ========================================
        // PAGSEGURO
        // ========================================

        Checkout checkout = new Checkout(
            new PagSeguroAdapter(new PagSeguroAPI())
        );

        checkout.finalizarCompra(
            150.00,
            "1234567890123456"
        );

        // ========================================
        // MERCADO PAGO
        // ========================================

        checkout = new Checkout(
            new MercadoPagoAdapter(new MercadoPagoSDK())
        );

        checkout.finalizarCompra(
            150.00,
            "1234567890123456"
        );
    }
}