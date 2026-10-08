package design_patters.negocio;

/** Opcional dinâmico: acrescenta descrição e custo sobre o que já existe por dentro. */
public class CoberturaRoubo extends SeguroDecorator {

    public CoberturaRoubo(Seguro seguro) {
        super(seguro);
    }

    @Override
    public String getDescricao() {
        return seguro.getDescricao() + " + cobertura contra roubo";
    }

    @Override
    public double preco() {
        return seguro.preco() + 250.00; // custo da camada + custo de tudo que está por dentro
    }
}
