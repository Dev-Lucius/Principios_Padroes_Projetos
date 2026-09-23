package design_patters.dominio;

public class EntregaNormal implements CalculoFrete{

    @Override
    public double calcular(Pedido pedido) {
        return 10.00 + (pedido.getDistanciaKm() * 0.05) + (pedido.getPesoKg() * 1.00);
    }

    @Override
    public String getNome() {
        return "Entrega Normal"; 
    }

}
