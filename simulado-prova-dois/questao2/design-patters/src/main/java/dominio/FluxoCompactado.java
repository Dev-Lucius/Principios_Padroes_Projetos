package dominio;

public class FluxoCompactado extends DecoradorFluxoDados {

    public FluxoCompactado(FluxoDados fluxo) {
        super(fluxo);
    }

    @Override
    public void escrever(String dados) {
        String dadosCompactados = "[ZIP] " + dados;
        System.out.println("Compactando dados...");
        fluxo.escrever(dadosCompactados);
    }
}