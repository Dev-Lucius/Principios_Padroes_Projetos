package Exercicio3;

public class RelatorioVendas extends Relatorio{

    @Override
    protected void buscarDados() {
        System.out.println("Buscnado Dados de Vendas");
    }

    @Override
    protected void processarDados() {
        System.out.println("Processando Dados de Vendas");
    }

    @Override
    protected void gerarArquivo() {
        System.out.println("Gerando Arquivos de Vendas");
    }
    
}
