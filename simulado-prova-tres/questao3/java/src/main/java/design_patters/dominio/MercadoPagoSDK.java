package design_patters.dominio;

import java.util.Map;

public class MercadoPagoSDK {

    public String processarPagamento(Map<String, Object> dados) {

        System.out.println("MercadoPago: " + dados);

        return "APROVADO";

    }

}
