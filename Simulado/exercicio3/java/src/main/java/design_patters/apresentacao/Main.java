package design_patters.apresentacao;

import design_patters.dominio.Cliente;
import design_patters.dominio.Estoque;
import design_patters.dominio.Pedido;
import design_patters.dominio.Status;
import design_patters.dominio.Transportadora;

public class Main {
    public static void main(String[] args) {
        // System.out.println("Hello world!");

        Pedido p1 = new Pedido(1, 5, 500.00);

        p1.addObserver(new Cliente("Pedro", "111.111.111-11"));
        p1.addObserver(new Estoque("Estoque Teste", 1121));
        p1.addObserver(new Transportadora("Transportadora Teste"));

        p1.alterarStatus(Status.PAGO);

        p1.notifyObserver();
    }
}