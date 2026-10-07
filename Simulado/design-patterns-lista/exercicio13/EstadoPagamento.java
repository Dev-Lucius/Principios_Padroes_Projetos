/** Pagamento em andamento: acumula o saldo até atingir o preço. */
public class EstadoPagamento implements EstadoMaquina {

    @Override
    public void selecionarProduto(Maquina maquina) {
        System.out.println("Pagamento em andamento. Cancele para trocar de produto.");
    }

    @Override
    public void inserirDinheiro(Maquina maquina, double valor) {
        if (valor <= 0) {
            System.out.println("Valor inválido.");
            return;
        }
        maquina.adicionarSaldo(valor);
        System.out.println("Inserido " + Maquina.dinheiro(valor)
                + " | saldo " + Maquina.dinheiro(maquina.getSaldo())
                + " de " + Maquina.dinheiro(maquina.getPreco()));

        if (maquina.getSaldo() >= maquina.getPreco()) { // valor necessário atingido
            System.out.println("Valor atingido!");
            maquina.setEstado(new EstadoProdutoLiberado());
        }
    }

    @Override
    public void cancelar(Maquina maquina) {
        System.out.println("Pagamento cancelado. Devolvendo " + Maquina.dinheiro(maquina.getSaldo()));
        maquina.zerarSaldo();
        maquina.setEstado(new EstadoSemProduto());
    }

    @Override
    public void liberarProduto(Maquina maquina) {
        double falta = maquina.getPreco() - maquina.getSaldo();
        System.out.println("Valor insuficiente: faltam " + Maquina.dinheiro(falta));
    }
}
