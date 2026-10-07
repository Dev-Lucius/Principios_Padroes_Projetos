public class Main {

    static void mostrar(String acao, EditorTexto editor) {
        System.out.printf("%-26s texto=\"%s\" | salvo=\"%s\"%n",
                acao, editor.getTexto(), editor.getUltimoSalvo());
    }

    public static void main(String[] args) {
        EditorTexto editor = new EditorTexto();
        Historico historico = new Historico();

        historico.executar(new InserirTextoCommand(editor, "Olá"));
        mostrar("inserir \"Olá\"", editor);

        historico.executar(new InserirTextoCommand(editor, ", mundo!"));
        mostrar("inserir \", mundo!\"", editor);

        historico.executar(new SalvarCommand(editor));
        mostrar("salvar", editor);

        historico.executar(new RemoverTextoCommand(editor, 6));
        mostrar("remover 6", editor);

        System.out.println("--- desfazendo tudo ---");
        historico.desfazer();
        mostrar("desfazer (remover)", editor);
        historico.desfazer();
        mostrar("desfazer (salvar)", editor);
        historico.desfazer();
        mostrar("desfazer (inserir)", editor);
        historico.desfazer();
        mostrar("desfazer (inserir)", editor);
        historico.desfazer();
    }
}
