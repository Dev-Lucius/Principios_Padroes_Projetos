/** Pagamento concluído: falta apenas entregar o produto (e o troco, se houver). */
public class EstadoProdutoLiberado implements EstadoMaquina {

    @Override
    public void selecionarProduto(Maquina maquina) {
        System.out.println("Retire o produto antes de nova seleção.");
    }

    @Override
    public void inserirDinheiro(Maquina maquina, double valor) {
        System.out.println("Pagamento já concluído. Recusado: " + Maquina.dinheiro(valor));
    }

    @Override
    public void cancelar(Maquina maquina) {
        System.out.println("Não é possível cancelar: o produto já foi pago.");
    }

    @Override
    public void liberarProduto(Maquina maquina) {
        double troco = maquina.getSaldo() - maquina.getPreco();
        System.out.println("Produto liberado!");
        if (troco > 0) {
            System.out.println("Troco: " + Maquina.dinheiro(troco));
        }
        maquina.zerarSaldo();
        maquina.setEstado(new EstadoSemProduto()); // ciclo completo: volta ao início
    }
}
