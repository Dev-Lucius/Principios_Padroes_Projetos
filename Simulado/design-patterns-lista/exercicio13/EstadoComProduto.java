/** Produto escolhido, pagamento ainda não iniciado. */
public class EstadoComProduto implements EstadoMaquina {

    @Override
    public void selecionarProduto(Maquina maquina) {
        System.out.println("Produto já selecionado. Insira o dinheiro ou cancele.");
    }

    @Override
    public void inserirDinheiro(Maquina maquina, double valor) {
        if (valor <= 0) {
            System.out.println("Valor inválido.");
            return;
        }
        // A primeira moeda INICIA o pagamento: troca de estado e repassa o valor ao novo estado
        maquina.setEstado(new EstadoPagamento());
        maquina.inserirDinheiro(valor);
    }

    @Override
    public void cancelar(Maquina maquina) {
        System.out.println("Seleção cancelada.");
        maquina.setEstado(new EstadoSemProduto());
    }

    @Override
    public void liberarProduto(Maquina maquina) {
        System.out.println("Efetue o pagamento primeiro.");
    }
}
