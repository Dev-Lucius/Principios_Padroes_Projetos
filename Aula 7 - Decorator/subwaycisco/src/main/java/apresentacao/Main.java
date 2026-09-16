package apresentacao;

import negocio.Alface;
import negocio.Barbecue;
import negocio.Lanche;
import negocio.LancheDePaoDeOregano;
import negocio.Tomate;

public class Main {
    public static void main(String[] args) {
        // exemplo de lanche com oregano para o cliente igor
        Lanche lancheNovo = new LancheDePaoDeOregano();
        lancheNovo.setClienteNome("Igor");
        // adicionei estes condimentos
        lancheNovo = new Tomate(lancheNovo);
        lancheNovo = new Tomate(lancheNovo);
        lancheNovo = new Alface(lancheNovo);
        lancheNovo = new Barbecue(lancheNovo);
        // e quis remover os 2 ultimos
        lancheNovo = lancheNovo.removeItem();

        // exibindo o extrato do pedido
        // exibindo o nome do cliente
        System.out.println("Cliente:" + lancheNovo.getClienteNome());
        // exibindo todos os itens: o tipo de lanche + ingredientes
        System.out.println("Descricao do Pedido:\n" + lancheNovo.descricao());
        // exibindo o preco total
        System.out.println("Total:" + lancheNovo.preco());

    }
}