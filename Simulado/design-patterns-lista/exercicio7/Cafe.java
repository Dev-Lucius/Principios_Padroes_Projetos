public class Cafe implements Bebida {
    @Override
    public String descricao() {
        return "Café";
    }

    @Override
    public double preco() {
        return 5.00;
    }
}
