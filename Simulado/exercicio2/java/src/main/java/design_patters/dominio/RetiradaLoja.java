package design_patters.dominio;

public class RetiradaLoja implements CalcularFrete{

    @Override
    public double calcular(double peso, double distancia) {
        return (peso * 2.5) + (distancia * 0);
    }

}
