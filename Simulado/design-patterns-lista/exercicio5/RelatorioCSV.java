public class RelatorioCSV extends Relatorio {

    @Override
    protected void abrir() {
        System.out.println("[CSV] Criando relatorio.csv");
    }

    @Override
    protected void cabecalho() {
        System.out.println("produto,valor");
    }

    @Override
    protected void dados() {
        System.out.println("Caneta,10.00");
        System.out.println("Caderno,25.00");
    }

    @Override
    protected void rodape() {
        System.out.println("TOTAL,35.00");
    }

    @Override
    protected void fechar() {
        System.out.println("[CSV] Arquivo fechado");
    }
}
