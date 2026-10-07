public interface EstadoMaquina {

    void selecionarProduto(Maquina maquina);

    void inserirDinheiro(Maquina maquina, double valor);

    void cancelar(Maquina maquina);

    void liberarProduto(Maquina maquina);
}
