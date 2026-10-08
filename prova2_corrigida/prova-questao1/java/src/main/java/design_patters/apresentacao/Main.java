package design_patters.apresentacao;

import design_patters.negocio.Assistencia24h;
import design_patters.negocio.CarroReserva;
import design_patters.negocio.CoberturaRoubo;
import design_patters.negocio.DanosTerceiros;
import design_patters.negocio.ProtecaoVidros;
import design_patters.negocio.Seguro;
import design_patters.negocio.SeguroBasico;

public class Main {

    static void mostrar(Seguro seguro) {
        System.out.println(seguro.getDescricao());
        System.out.printf("Total: R$ %.2f%n%n", seguro.preco());
    }

    public static void main(String[] args) {

        mostrar(new SeguroBasico());

        // Básico + roubo: 600 + 250
        mostrar(new CoberturaRoubo(new SeguroBasico()));

        // Básico + roubo + carro reserva: 600 + 250 + 400
        mostrar(new CarroReserva(new CoberturaRoubo(new SeguroBasico())));

        // Montagem passo a passo (cada linha embrulha o objeto anterior)
        Seguro completo = new SeguroBasico();
        completo = new CoberturaRoubo(completo);
        completo = new Assistencia24h(completo);
        completo = new CarroReserva(completo);
        completo = new DanosTerceiros(completo);
        completo = new ProtecaoVidros(completo); // opcional NOVO: só uma classe nova, nenhuma combinação
        mostrar(completo);
    }
}
