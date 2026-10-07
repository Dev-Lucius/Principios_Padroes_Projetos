import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/** Percorre do primeiro ao último: o índice começa em 0 e AVANÇA. */
class IteradorNormal implements Iterator<Musica> {

    private final List<Musica> musicas;
    private int posicao = 0;

    IteradorNormal(List<Musica> musicas) {
        this.musicas = musicas;
    }

    @Override
    public boolean hasNext() {
        return posicao < musicas.size();
    }

    @Override
    public Musica next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return musicas.get(posicao++);
    }
}
