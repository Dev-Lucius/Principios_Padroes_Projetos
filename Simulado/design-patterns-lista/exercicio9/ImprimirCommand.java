/**
 * COMMAND: guarda o receptor (impressora) e os PARÂMETROS da ação (documento).
 * Assim a ação pode ser criada agora e executada depois.
 */
public class ImprimirCommand implements Comando {

    private final Impressora impressora;
    private final String documento;

    public ImprimirCommand(Impressora impressora, String documento) {
        this.impressora = impressora;
        this.documento = documento;
    }

    @Override
    public void executar() {
        impressora.imprimir(documento);
    }
}
