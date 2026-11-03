package dominio;

public class FluxoBuffer extends DecoradorFluxoDados {

    private final StringBuilder buffer = new StringBuilder();

    public FluxoBuffer(FluxoDados fluxo) {
        super(fluxo);
    }

    @Override
    public void escrever(String dados) {
        buffer.append(dados);
        System.out.println("[BUFFER] Dados armazenados temporariamente.");
    }

    public void descarregar() {

        if (buffer.length() > 0) {
            System.out.println("[BUFFER] Descarregando dados...");
            fluxo.escrever(buffer.toString());
            buffer.setLength(0);
        }
    }
}