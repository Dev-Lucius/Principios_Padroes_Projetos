package Exercicio4;

public class FreteExpresso implements EstrategiaFrete{

    @Override
    public double calcular(double distancia, double peso) {
        return distancia * 4.0 + peso * 1.0;
    }
}
