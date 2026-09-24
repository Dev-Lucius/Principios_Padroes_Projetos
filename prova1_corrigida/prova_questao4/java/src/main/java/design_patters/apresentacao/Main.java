package design_patters.apresentacao;

import design_patters.dominio.Bar;
import design_patters.dominio.Confeitaria;
import design_patters.dominio.Cozinha;
import design_patters.dominio.EsteiraDePedidos;
import design_patters.dominio.PrepararBebida;
import design_patters.dominio.PrepararPratoPrincipal;
import design_patters.dominio.PrepararSobremesa;
import design_patters.dominio.SolicitacaoPreparo;

public class Main {
    public static void main(String[] args){
        // System.out.println("Hello World");

        System.out.println("Questão 4 - Command");

        Cozinha cozinha = new Cozinha();
        Confeitaria confeitaria = new Confeitaria();
        Bar bar = new Bar();

        EsteiraDePedidos esteira = new EsteiraDePedidos();

        // A interface (garçom) apenas cria comandos e os envia para a esteira
        esteira.enviar(new PrepararPratoPrincipal(1, 5, "Risoto de Cogumelos", cozinha));
        esteira.enviar(new PrepararBebida(2, 5, "Caipirinha", bar));
        esteira.enviar(new PrepararSobremesa(3, 5, "Petit gateau", confeitaria));
        esteira.enviar(new PrepararBebida(4, 7, "Suco de laranja", bar));

        System.out.println("Pedido #2 Enviado por Engano: Cancelando Antes do Preparo");
        esteira.cancelar(2);

        System.out.println("Processando a Fila em Ordem de Chegada");
        esteira.processarProximo(); // Executa #1
        esteira.processarProximo(); // Executa #3

        System.out.println("Tentando Cancelar um Pedido já Preparado");
        esteira.cancelar(1);

        esteira.processarTodos(); // Executa #4

        System.out.println("\nHistorico (para conferencia e fechamento da conta):");
        for (SolicitacaoPreparo s : esteira.getHistorico()) {
            System.out.printf("  %s | %s%n", s.getDescricao(), s.getStatus());
        }
    }
}
