package Exercicio3;

public class RelatorioEstoque extends Relatorio{

    @Override
    protected void buscarDados() {
        System.out.println("Buscando Dados de Estoque");
    }

    @Override
    protected void processarDados() {
        System.out.println("Processando Dados de Estoque");
    }

    @Override
    protected void gerarArquivo() {
        System.out.println("Gerando Arquivos de Estoque");
    }
    
}
