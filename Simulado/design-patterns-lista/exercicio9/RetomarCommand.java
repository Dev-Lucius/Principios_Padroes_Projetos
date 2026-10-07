public class RetomarCommand implements Comando {

    private final Impressora impressora;

    public RetomarCommand(Impressora impressora) {
        this.impressora = impressora;
    }

    @Override
    public void executar() {
        impressora.retomar();
    }
}
