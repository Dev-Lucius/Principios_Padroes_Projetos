package design_patters.dominio;

public class Transportadora implements CalcularFrete{

    @Override
    public double calcular(double peso, double distancia) {
        return (10 + (peso * 3) + (distancia * 3));
    }

}
