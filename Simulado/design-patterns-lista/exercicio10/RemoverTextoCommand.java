public class RemoverTextoCommand implements Comando {

    private final EditorTexto editor;
    private final int quantidade;
    private String removido = ""; // estado guardado para permitir o desfazer

    public RemoverTextoCommand(EditorTexto editor, int quantidade) {
        this.editor = editor;
        this.quantidade = quantidade;
    }

    @Override
    public void executar() {
        removido = editor.removerFinal(quantidade); // guarda o que saiu
    }

    @Override
    public void desfazer() {
        editor.inserir(removido); // devolve exatamente o que foi removido
    }
}
