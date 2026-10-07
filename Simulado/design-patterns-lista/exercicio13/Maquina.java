import java.util.Locale;

/**
 * STATE - CONTEXTO.
 *
 * A Maquina NÃO tem if/else sobre o estado: cada operação pública apenas
 * delega ao objeto de estado atual. Quem decide o que acontece (e para
 * qual estado ir em seguida) é o próprio estado, chamando setEstado().
 *
 * Observação: em sistema real, dinheiro deveria usar BigDecimal ou centavos (int),
 * porque double acumula erro de arredondamento. Aqui double segue a interface do enunciado.
 */
public class Maquina {

    private static final double PRECO_PRODUTO = 5.00;

    private EstadoMaquina estado = new EstadoSemProduto(); // estado inicial
    private double saldo = 0;

    // ----- Operações públicas: só delegam -----
    public void selecionarProduto() {
        estado.selecionarProduto(this);
    }

    public void inserirDinheiro(double valor) {
        estado.inserirDinheiro(this, valor);
    }

    public void cancelar() {
        estado.cancelar(this);
    }

    public void liberarProduto() {
        estado.liberarProduto(this);
    }

    // ----- Usados apenas pelos estados (visibilidade de pacote) -----
    void setEstado(EstadoMaquina novo) {
        System.out.println("   [transição] " + estado.getClass().getSimpleName()
                + " -> " + novo.getClass().getSimpleName());
        this.estado = novo;
    }

    double getSaldo() {
        return saldo;
    }

    void adicionarSaldo(double valor) {
        saldo += valor;
    }

    void zerarSaldo() {
        saldo = 0;
    }

    double getPreco() {
        return PRECO_PRODUTO;
    }

    static String dinheiro(double valor) {
        return String.format(Locale.forLanguageTag("pt-BR"), "R$ %.2f", valor);
    }

    public String getEstadoAtual() {
        return estado.getClass().getSimpleName();
    }
}
