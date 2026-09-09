package negocio;

import java.util.Iterator;

public class CardapioCafeDaManhaIterator implements Iterator<MenuItem>{

    private CardapioCafeDaManha cardapioCafeDaManha;
    private int i;

    public CardapioCafeDaManhaIterator(CardapioCafeDaManha cardapioCafeDaManha) {
        this.cardapioCafeDaManha = cardapioCafeDaManha;
        this.i = 0;
    }

    @Override
    public boolean hasNext() {
        if (this.i < this.cardapioCafeDaManha.getItens().length) return true;
        return false;
    }

    @Override
    public MenuItem next() {
        MenuItem menuItem = this.cardapioCafeDaManha.getItens()[this.i];
        this.i++;
        return menuItem;
    }

}
