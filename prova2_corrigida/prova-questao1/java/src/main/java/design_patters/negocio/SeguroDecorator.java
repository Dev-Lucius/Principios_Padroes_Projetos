package design_patters.negocio;

/**
 * Decorador base: É um Seguro (para poder ser usado onde um Seguro é esperado)
 * e TEM um Seguro (a camada de dentro). Por padrão apenas repassa a chamada.
 */
public abstract class SeguroDecorator implements Seguro {

    protected final Seguro seguro;

    protected SeguroDecorator(Seguro seguro) {
        this.seguro = seguro;
    }

    @Override
    public String getDescricao() {
        return seguro.getDescricao();
    }

    @Override
    public double preco() {
        return seguro.preco();
    }
}
