package design_patters.dominio;

public class RetiradaPontoAtendimento implements CalculoFrete{

    @Override
    public double calcular(Pedido pedido) {
        return 0.00;
    }

    @Override
    public String getNome() {
        return "Retirada em Ponto Atendimento";
    }

}
