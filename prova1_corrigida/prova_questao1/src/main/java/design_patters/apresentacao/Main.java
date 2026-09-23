package design_patters.apresentacao;

import java.util.List;

import design_patters.dominio.CalculoFrete;
import design_patters.dominio.EntregaAgendada;
import design_patters.dominio.EntregaExpressa;
import design_patters.dominio.EntregaNormal;
import design_patters.dominio.Pedido;
import design_patters.dominio.RetiradaPontoAtendimento;

public class Main {
    public static void main(String[] args){
        
        System.out.println("Questão 1 - Cálculo de Frete (Strategy) ");

        Pedido p1 = new Pedido(1, 250.00, 3.00, 120.00);

        List<CalculoFrete> modalidades = List.of(
            new EntregaNormal(),
            new EntregaAgendada(),
            new EntregaExpressa(),
            new RetiradaPontoAtendimento()
        );

        // Entrega Normal
        p1.setModalidade(modalidades.get(0)); 
        System.out.println(p1.calcularFrete());
        System.out.println(p1.calcularTotal());

        System.out.println(" --- ");

        // Troca a modalidade em tempo de execucao, sem alterar a classe Pedido
        for(CalculoFrete modalidade : modalidades){
            p1.setModalidade(modalidade);
            System.out.printf("%-34s frete: R$ %7.2f | total: R$ %7.2f%n",
                    modalidade.getNome(), p1.calcularFrete(), p1.calcularTotal());
        }
    }
}
