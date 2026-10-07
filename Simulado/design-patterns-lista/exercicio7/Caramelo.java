public class Caramelo extends DecoradorBebida {

    public Caramelo(Bebida bebida) {
        super(bebida);
    }

    @Override
    public String descricao() {
        return bebida.descricao() + " + caramelo";
    }

    @Override
    public double preco() {
        return bebida.preco() + 1.50;
    }
}
