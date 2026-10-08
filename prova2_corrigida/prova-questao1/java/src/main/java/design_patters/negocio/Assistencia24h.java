package design_patters.negocio;

/** Opcional dinâmico: acrescenta descrição e custo sobre o que já existe por dentro. */
public class Assistencia24h extends SeguroDecorator {

    public Assistencia24h(Seguro seguro) {
        super(seguro);
    }

    @Override
    public String getDescricao() {
        return seguro.getDescricao() + " + assistência 24h";
    }

    @Override
    public double preco() {
        return seguro.preco() + 350.00; // custo da camada + custo de tudo que está por dentro
    }
}
