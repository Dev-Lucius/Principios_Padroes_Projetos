package design_patters.dominio;

public class FluxoComLog extends DecoratorFluxo {

    public FluxoComLog(FluxoDados fluxo) {
        super(fluxo);
    }

    @Override
    public void escrever(String dados) {
        System.out.println("[LOG] Iniciando escrita...");
        System.out.println("[LOG] Dados recebidos: " + dados);

        fluxo.escrever(dados);

        System.out.println("[LOG] Escrita finalizada.");
    }
}
