package dominio;

import java.util.HashMap;
import java.util.Map;

public class MercadoPagoAdapter implements GatewayPagamento {

    private final MercadoPagoSDK sdk;

    public MercadoPagoAdapter(MercadoPagoSDK sdk) {
        this.sdk = sdk;
    }

    @Override
    public boolean pagar(double valor, String numeroCartao) {

        Map<String, Object> dados = new HashMap<>();

        dados.put("valor", valor);
        dados.put("cartao", numeroCartao);

        String resultado = sdk.processarPagamento(dados);

        return "APROVADO".equals(resultado);

    }
}