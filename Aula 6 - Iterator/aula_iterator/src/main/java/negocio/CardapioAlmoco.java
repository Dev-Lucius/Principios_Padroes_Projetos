package negocio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CardapioAlmoco {
    private List<MenuItem> itens;

    public CardapioAlmoco(){
        this.itens = new ArrayList<>();
        this.itens.add(new MenuItem("Alaminuta", 1.99, false, Segmento.ALMOCO));
        this.itens.add(new MenuItem("Feijão", 2.99, false, Segmento.ALMOCO));
        this.itens.add(new MenuItem("Calabresa", 3.99, false, Segmento.ALMOCO));
    }

    // para tentar isolar um pouco - definimos a visibilidade de pacote. Logo, qualquer classe fora do pacote de negocio não consegue utilizar o metodo em questão abaixo. O ideal seria mais privatizo ainda
    List<MenuItem> getItens() {
        return itens;
    }

    // mais interessante - encapsular/isolar o tipo de estrutura de dados utilizada
    // public Iterator<MenuItem> createIterator(){
    //     return new CardapioAlmocoInvertidoIterador(this.itens);
    // }

}
