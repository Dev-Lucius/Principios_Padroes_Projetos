public class Chantilly extends DecoradorBebida {

    public Chantilly(Bebida bebida) {
        super(bebida);
    }

    @Override
    public String descricao() {
        return bebida.descricao() + " + chantilly";
    }

    @Override
    public double preco() {
        return bebida.preco() + 2.00;
    }
}
