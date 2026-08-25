package Exercicio4;

public class FreteEconomico implements EstrategiaFrete{

    @Override
    public double calcular(double distancia, double peso) {
        return distancia * 1.5 + peso * 0.3; 
    }
}
