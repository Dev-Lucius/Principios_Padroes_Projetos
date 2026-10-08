package design_patters.negocio;

import java.util.List;

/** Substitui as DUAS etapas variáveis: autenticação e captura. */
public class ProcessadorInstagram extends AlgoritmoMineracao {

    @Override
    protected void autenticar() {
        System.out.println("1. Autenticando no Instagram via token da Graph API");
    }

    @Override
    protected List<String> capturarPostsBrutos() {
        System.out.println("2. Capturando posts via Graph API (paginação por cursor)");
        return List.of("Foto linda", "Você é um idiota", "Amei o look");
    }
}
