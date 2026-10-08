package design_patters.negocio;

/** Opcional dinâmico: acrescenta descrição e custo sobre o que já existe por dentro. */
public class CarroReserva extends SeguroDecorator {

    public CarroReserva(Seguro seguro) {
        super(seguro);
    }

    @Override
    public String getDescricao() {
        return seguro.getDescricao() + " + carro reserva";
    }

    @Override
    public double preco() {
        return seguro.preco() + 400.00; // custo da camada + custo de tudo que está por dentro
    }
}
