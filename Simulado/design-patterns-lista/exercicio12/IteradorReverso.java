import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/** Percorre do último ao primeiro: o índice começa no fim e RECUA. */
class IteradorReverso implements Iterator<Musica> {

    private final List<Musica> musicas;
    private int posicao;

    IteradorReverso(List<Musica> musicas) {
        this.musicas = musicas;
        this.posicao = musicas.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return posicao >= 0;
    }

    @Override
    public Musica next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return musicas.get(posicao--);
    }
}
