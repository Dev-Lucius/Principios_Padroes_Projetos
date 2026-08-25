package Exercicio3;

public class RelatorioFinanceiro extends Relatorio{

    @Override
    protected void buscarDados() {
        System.out.println("Buscando Dados Financeiros");
    }

    @Override
    protected void processarDados() {
        System.out.println("Processando Dados Financeiros");
    }

    @Override
    protected void gerarArquivo() {
        System.out.println("Gerando Arquivos Financeiros");
    }

}