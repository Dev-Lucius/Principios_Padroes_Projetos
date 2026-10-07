/** Máquina ociosa: a única ação válida é selecionar um produto. */
public class EstadoSemProduto implements EstadoMaquina {

    @Override
    public void selecionarProduto(Maquina maquina) {
        System.out.println("Produto selecionado. Preço: " + Maquina.dinheiro(maquina.getPreco()));
        maquina.setEstado(new EstadoComProduto());
    }

    @Override
    public void inserirDinheiro(Maquina maquina, double valor) {
        System.out.println("Selecione um produto antes. Devolvendo " + Maquina.dinheiro(valor));
    }

    @Override
    public void cancelar(Maquina maquina) {
        System.out.println("Não há nada para cancelar.");
    }

    @Override
    public void liberarProduto(Maquina maquina) {
        System.out.println("Nenhum produto selecionado.");
    }
}
