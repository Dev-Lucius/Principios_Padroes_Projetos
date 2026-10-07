public class RelatorioPDF extends Relatorio {

    @Override
    protected void abrir() {
        System.out.println("[PDF] Abrindo documento PDF");
    }

    @Override
    protected void cabecalho() {
        System.out.println("[PDF] Cabeçalho: logotipo + título centralizado");
    }

    @Override
    protected void dados() {
        System.out.println("[PDF] Dados: tabela formatada com bordas");
    }

    @Override
    protected void rodape() {
        System.out.println("[PDF] Rodapé: numeração de páginas");
    }

    @Override
    protected void fechar() {
        System.out.println("[PDF] Fechando e salvando relatorio.pdf");
    }
}
