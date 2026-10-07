public class SalvarCommand implements Comando {

    private final EditorTexto editor;
    private String versaoAnterior = "";

    public SalvarCommand(EditorTexto editor) {
        this.editor = editor;
    }

    @Override
    public void executar() {
        versaoAnterior = editor.salvar(); // lembra qual era a versão salva antes
    }

    @Override
    public void desfazer() {
        editor.restaurarUltimoSalvo(versaoAnterior);
    }
}
