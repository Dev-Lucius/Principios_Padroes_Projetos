public class Canela extends DecoradorBebida {

    public Canela(Bebida bebida) {
        super(bebida);
    }

    @Override
    public String descricao() {
        return bebida.descricao() + " + canela";
    }

    @Override
    public double preco() {
        return bebida.preco() + 0.50;
    }
}
