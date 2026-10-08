package design_patters.negocio;

public class ProcessadorTikTok extends AlgoritmoMineracao {

    @Override
    protected void autenticar() {
        System.out.println("1. Autenticando no TikTok via client key + client secret");
    }
}
