public class InserirTextoCommand implements Comando {

    private final EditorTexto editor;
    private final String trecho;

    public InserirTextoCommand(EditorTexto editor, String trecho) {
        this.editor = editor;
        this.trecho = trecho;
    }

    @Override
    public void executar() {
        editor.inserir(trecho);
    }

    @Override
    public void desfazer() {
        // O inverso de inserir é remover exatamente o que foi inserido
        editor.removerFinal(trecho.length());
    }
}
