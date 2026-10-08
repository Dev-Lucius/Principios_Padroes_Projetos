package design_patters.negocio;

/** Substitui APENAS uma etapa variável (autenticação). O resto vem pronto da classe base. */
public class ProcessadorTwitter extends AlgoritmoMineracao {

    @Override
    protected void autenticar() {
        System.out.println("1. Autenticando no X (Twitter) via OAuth 2.0");
    }
}
