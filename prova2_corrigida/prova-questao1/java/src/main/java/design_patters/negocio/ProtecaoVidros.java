package design_patters.negocio;

/** Opcional dinâmico: acrescenta descrição e custo sobre o que já existe por dentro. */
public class ProtecaoVidros extends SeguroDecorator {

    public ProtecaoVidros(Seguro seguro) {
        super(seguro);
    }

    @Override
    public String getDescricao() {
        return seguro.getDescricao() + " + proteção de vidros";
    }

    @Override
    public double preco() {
        return seguro.preco() + 150.00; // custo da camada + custo de tudo que está por dentro
    }
}
