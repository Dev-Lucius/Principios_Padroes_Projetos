package dominio;

public class FluxoCriptografado extends DecoradorFluxoDados {

    public FluxoCriptografado(FluxoDados fluxo) {
        super(fluxo);
    }

    @Override
    public void escrever(String dados) {
        String dadosCriptografados = new StringBuilder(dados).reverse().toString();
        System.out.println("Criptografando dados...");
        fluxo.escrever(dadosCriptografados);
    }
}