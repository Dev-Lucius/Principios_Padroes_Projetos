package design_patters.dominio;

public class Sedex implements CalcularFrete{

    @Override
    public double calcular(double peso, double distancia) {
        return ((peso * 4) + (distancia * 2));
    }

}
