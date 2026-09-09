package apresentacao;

import java.util.Iterator;
import negocio.CardapioAlmoco;
import negocio.CardapioAlmocoInvertidoIterador;
import negocio.CardapioCafeDaManha;
import negocio.CardapioCafeDaManhaIterator;
import negocio.CardapioJanta;
import negocio.MenuItem;

public class Main {
    public static void main(String[] args) {
        CardapioCafeDaManha cardapioCafeDaManha = new CardapioCafeDaManha();
        Iterator<MenuItem> iteratorCafeDaManha = new CardapioCafeDaManhaIterator(cardapioCafeDaManha);
        percorreMenuItens(iteratorCafeDaManha);

        CardapioAlmoco cardapioAlmoco = new CardapioAlmoco();
        // List<MenuItem> itensAlmoco = cardapioAlmoco.getItens();
        // Iterator<MenuItem> iteratorAlmoco = itensAlmoco.iterator();   
        // Iterator<MenuItem> iteratorAlmoco = new CardapioAlmocoAleatorioIterator(cardapioAlmoco); 
        Iterator<MenuItem> iteratorAlmoco = new CardapioAlmocoInvertidoIterador(cardapioAlmoco);   
        percorreMenuItens(iteratorAlmoco);

        CardapioJanta cardapioJanta = new CardapioJanta();
        percorreMenuItens(cardapioJanta);
      
    }

    private static void percorreMenuItens(Iterator<MenuItem> iterator) {
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}