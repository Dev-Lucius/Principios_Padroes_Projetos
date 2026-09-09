package negocio;

import java.util.Iterator;
import java.util.List;

public class CardapioAlmocoInvertidoIterador implements Iterator<MenuItem> {

    private CardapioAlmoco cardapioAlmoco;
    private int i;

    public CardapioAlmocoInvertidoIterador(CardapioAlmoco cardapioAlmoco) {
        this.cardapioAlmoco = cardapioAlmoco;
        this.i = this.cardapioAlmoco.getItens().size()-1;
    }
    
    // public CardapioAlmocoInvertidoIterador(List<MenuItem> itens) {
    //     //TODO Auto-generated constructor stub
    // }

    @Override
    public boolean hasNext() {
        if (this.i >= 0) return true;
        return false;
    }

    @Override
    public MenuItem next() {
        MenuItem menuItem = this.cardapioAlmoco.getItens().get(i);
        i--;
        return menuItem;
    }

}
