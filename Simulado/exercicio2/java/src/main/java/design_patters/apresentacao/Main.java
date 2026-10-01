package design_patters.apresentacao;

import java.util.UUID;

import design_patters.dominio.Cliente;
import design_patters.dominio.Pedido;
import design_patters.dominio.Status;
import design_patters.dominio.Transportadora;

public class Main {
    public static void main(String[] args) {
        // System.out.println("Hello world!");

        Cliente lucas = new Cliente(1, "111.222.333-44", "Lucas Oliveira", "lucas@exemplo.com");
        System.out.println(lucas.toString());

        Pedido p1 = new Pedido(UUID.randomUUID(), "Pedido Teste", 1200.00);

        p1.addObserver(lucas);
        p1.setFrete(new Transportadora());

        System.out.println(p1.valorFrete(1, 100));
        p1.changeStatus(Status.ENVIADO);
    }
}