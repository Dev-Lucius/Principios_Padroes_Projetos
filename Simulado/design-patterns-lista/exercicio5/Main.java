public class Main {
    public static void main(String[] args) {

        // O cliente só conhece Relatorio e chama sempre o mesmo método: gerar().
        // Quem define o que acontece em cada etapa é o objeto concreto (polimorfismo).
        Relatorio relatorio = new RelatorioPDF();
        relatorio.gerar();

        System.out.println();
        relatorio = new RelatorioHTML();
        relatorio.gerar();

        System.out.println();
        relatorio = new RelatorioCSV();
        relatorio.gerar();
    }
}
