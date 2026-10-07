public class Main {
    public static void main(String[] args) {

        // ---- Execução direta (exemplo do enunciado) ----
        System.out.println("== Execução direta ==");
        Impressora impressora = new Impressora();

        Comando comando1 = new ImprimirCommand(impressora, "trabalho.pdf");
        Comando comando2 = new PausarCommand(impressora);

        comando1.executar();
        comando2.executar();

        // ---- Desafio: execução adiada pela fila ----
        System.out.println();
        System.out.println("== Fila de impressão ==");
        Impressora outra = new Impressora();
        FilaDeImpressao fila = new FilaDeImpressao();

        fila.adicionar(new ImprimirCommand(outra, "trabalho.pdf"));
        fila.adicionar(new PausarCommand(outra));
        fila.adicionar(new ImprimirCommand(outra, "relatorio.docx"));
        fila.adicionar(new ImprimirCommand(outra, "foto.png"));
        fila.adicionar(new CancelarCommand(outra, "foto.png"));
        fila.adicionar(new RetomarCommand(outra));

        System.out.println("Nada foi executado até aqui. Executando a fila...");
        fila.executarTodos();
    }
}
