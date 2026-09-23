package design_patters.dominio;

public class EntregaExpressa implements CalculoFrete{

    @Override
    public double calcular(Pedido pedido) {
        return 25.00 + (pedido.getDistanciaKm()) + (pedido.getPesoKg() * 2.50);
    }

    @Override
    public String getNome() {
        return "Entrega Expressa";
    }

}
