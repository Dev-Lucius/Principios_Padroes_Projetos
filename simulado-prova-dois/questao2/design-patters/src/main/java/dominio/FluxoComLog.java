package dominio;

public class FluxoComLog extends DecoradorFluxoDados {

    public FluxoComLog(FluxoDados fluxo) {
        super(fluxo);
    }

    @Override
    public void escrever(String dados) {
        System.out.println("[LOG] Iniciando gravação...");
        fluxo.escrever(dados);
        System.out.println("[LOG] Gravação finalizada.");
    }
}