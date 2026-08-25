package Exercicio4;

public class FreteNormal implements EstrategiaFrete{

    @Override
    public double calcular(double distancia, double peso) {
        return distancia * 2.0 + peso * 0.5;
    }

}
