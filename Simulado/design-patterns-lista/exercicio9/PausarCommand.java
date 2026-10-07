public class PausarCommand implements Comando {

    private final Impressora impressora;

    public PausarCommand(Impressora impressora) {
        this.impressora = impressora;
    }

    @Override
    public void executar() {
        impressora.pausar();
    }
}
