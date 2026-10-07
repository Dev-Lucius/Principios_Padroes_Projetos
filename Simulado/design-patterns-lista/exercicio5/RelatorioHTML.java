public class RelatorioHTML extends Relatorio {

    @Override
    protected void abrir() {
        System.out.println("<html><body>");
    }

    @Override
    protected void cabecalho() {
        System.out.println("  <h1>Relatório de Vendas</h1>");
    }

    @Override
    protected void dados() {
        System.out.println("  <table>");
        System.out.println("    <tr><td>Caneta</td><td>10.00</td></tr>");
        System.out.println("    <tr><td>Caderno</td><td>25.00</td></tr>");
        System.out.println("  </table>");
    }

    @Override
    protected void rodape() {
        System.out.println("  <footer>Total: 35.00</footer>");
    }

    @Override
    protected void fechar() {
        System.out.println("</body></html>");
    }
}
