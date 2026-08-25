package Exercicio3;

public class Main {
    public static void main(String[] args) {

        Relatorio financeiro = new RelatorioFinanceiro();
        Relatorio vendas = new RelatorioVendas();
        Relatorio estoque = new RelatorioEstoque();

        financeiro.gerarRelatorio();

        System.out.println(" --- ");

        vendas.gerarRelatorio();

        System.out.println(" --- ");

        estoque.gerarRelatorio();
    }
}
