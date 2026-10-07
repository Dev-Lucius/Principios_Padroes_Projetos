public class CancelarCommand implements Comando {

    private final Impressora impressora;
    private final String documento;

    public CancelarCommand(Impressora impressora, String documento) {
        this.impressora = impressora;
        this.documento = documento;
    }

    @Override
    public void executar() {
        impressora.cancelar(documento);
    }
}
