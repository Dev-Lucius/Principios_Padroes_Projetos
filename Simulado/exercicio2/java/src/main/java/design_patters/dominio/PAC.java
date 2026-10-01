package design_patters.dominio;

public class PAC implements CalcularFrete{

    @Override
    public double calcular(double peso, double distancia) {
        return ((peso * 1) + (distancia * 1));
    }

}
