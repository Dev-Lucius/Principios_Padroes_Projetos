package design_patters.dominio;

import java.util.HashMap;
import java.util.Map;

public class MercadoPagoAdapter implements GatewayPagamento{

    private final MercadoPagoSDK sdk;

    public MercadoPagoAdapter(MercadoPagoSDK sdk){
        this.sdk = sdk;
    }



    @Override
    public boolean pagar(double valor, String numeroCartao) {
        Map<String, Object> dados = new HashMap<>();

        dados.put("Valor", valor);
        dados.put("Cartao", numeroCartao);

        /*
            {
                valor = 150.0,
                cartao = "123456789"
            }
        */

        String resultado = sdk.processarPagamento(dados);
        return "APROVADO".equals(resultado);
    }
}
