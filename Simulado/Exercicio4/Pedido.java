package Exercicio4;

public class Pedido {
    private EstrategiaFrete estrategiaFrete;

    public Pedido(EstrategiaFrete estrategiaFrete){
        this.estrategiaFrete = estrategiaFrete;
    }

    public void definirEstrategiaFrete(EstrategiaFrete estrategiaFrete) {
        this.estrategiaFrete = estrategiaFrete;
    }

    public double calcularFrete(double distancia, double peso){
        return estrategiaFrete.calcular(distancia, peso);
    }
}
