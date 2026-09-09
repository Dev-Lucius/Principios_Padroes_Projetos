package negocio;

import java.util.Collections;
import java.util.Iterator;

public class CardapioAlmocoAleatorioIterator implements Iterator<MenuItem> {

    private CardapioAlmoco cardapioAlmoco;
    protected int i;

    public CardapioAlmocoAleatorioIterator(CardapioAlmoco cardapioAlmoco){
        this.cardapioAlmoco = cardapioAlmoco;
        this.i = 0;
        Collections.shuffle(cardapioAlmoco.getItens());
    }

    @Override
    public boolean hasNext() {
        if (this.i < this.cardapioAlmoco.getItens().size()) return true;
        return false;
    }

    @Override
    public MenuItem next() {
        MenuItem menuItem = this.cardapioAlmoco.getItens().get(this.i);
        this.i++;
        return menuItem;

    }

}
