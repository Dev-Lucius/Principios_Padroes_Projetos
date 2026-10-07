public class Main {
    public static void main(String[] args) {

        System.out.println("== Fluxo normal (exemplo do enunciado) ==");
        Maquina maquina = new Maquina();
        maquina.selecionarProduto();
        maquina.inserirDinheiro(2.00);
        maquina.inserirDinheiro(3.00);
        maquina.liberarProduto();

        System.out.println();
        System.out.println("== Cancelamento durante o pagamento ==");
        maquina.selecionarProduto();
        maquina.inserirDinheiro(2.00);
        maquina.cancelar();

        System.out.println();
        System.out.println("== Pagando a mais (troco) ==");
        maquina.selecionarProduto();
        maquina.inserirDinheiro(10.00);
        maquina.liberarProduto();

        System.out.println();
        System.out.println("== Operações inválidas em cada estado ==");
        maquina.inserirDinheiro(1.00);   // SemProduto: precisa selecionar antes
        maquina.liberarProduto();        // SemProduto: nada selecionado
        maquina.selecionarProduto();
        maquina.selecionarProduto();     // ComProduto: já selecionado
        maquina.liberarProduto();        // ComProduto: falta pagar
        maquina.inserirDinheiro(1.00);
        maquina.liberarProduto();        // Pagamento: valor insuficiente
        maquina.inserirDinheiro(4.00);   // atinge o valor
        maquina.cancelar();              // ProdutoLiberado: não pode cancelar
        maquina.inserirDinheiro(1.00);   // ProdutoLiberado: recusa
        maquina.liberarProduto();
        System.out.println("Estado final: " + maquina.getEstadoAtual());
    }
}
