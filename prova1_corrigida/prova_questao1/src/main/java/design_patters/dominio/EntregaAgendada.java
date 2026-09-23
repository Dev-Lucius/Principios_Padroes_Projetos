package design_patters.dominio;

public class EntregaAgendada implements CalculoFrete{

    public static final double TAXA_AGENDAMENTO = 7.50;

    @Override
    public double calcular(Pedido pedido) {
        return 10.00 + TAXA_AGENDAMENTO + (pedido.getDistanciaKm() * 0.05) + (pedido.getPesoKg() * 1.00);
    }

    @Override
    public String getNome() {
        return "Entrega Agendada";
    }
    
}
