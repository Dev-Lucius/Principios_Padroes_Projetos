package Exercicio4;

public class Main {

    public static void main(String[] args) {

        Pedido pedido = new Pedido(new FreteNormal());

        System.out.println(
                "Frete normal: " +
                pedido.calcularFrete(100, 5)
        );

        pedido.definirEstrategiaFrete(new FreteExpresso());

        System.out.println(
                "Frete expresso: " +
                pedido.calcularFrete(100, 5)
        );

        pedido.definirEstrategiaFrete(new FreteEconomico());

        System.out.println(
                "Frete econômico: " +
                pedido.calcularFrete(100, 5)
        );
    }
}