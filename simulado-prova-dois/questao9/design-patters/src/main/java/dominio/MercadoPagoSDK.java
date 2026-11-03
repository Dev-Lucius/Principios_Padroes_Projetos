package dominio;

import java.util.Map;

public class MercadoPagoSDK {
    public String processarPagamento(Map<String, Object> dados){
        System.out.println("Mercado Pago: " + dados);
        return "Aprovado";
    }
}
