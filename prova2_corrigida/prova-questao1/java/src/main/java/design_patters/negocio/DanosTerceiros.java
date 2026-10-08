package design_patters.negocio;

/** Opcional dinâmico: acrescenta descrição e custo sobre o que já existe por dentro. */
public class DanosTerceiros extends SeguroDecorator {

    public DanosTerceiros(Seguro seguro) {
        super(seguro);
    }

    @Override
    public String getDescricao() {
        return seguro.getDescricao() + " + danos a terceiros";
    }

    @Override
    public double preco() {
        return seguro.preco() + 300.00; // custo da camada + custo de tudo que está por dentro
    }
}
