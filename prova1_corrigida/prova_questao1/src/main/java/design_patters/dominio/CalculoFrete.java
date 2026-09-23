package design_patters.dominio;

public interface CalculoFrete {
    double calcular(Pedido pedido);
    String getNome();
}
