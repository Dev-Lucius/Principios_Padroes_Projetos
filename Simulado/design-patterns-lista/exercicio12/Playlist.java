import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ITERATOR.
 *
 * A Playlist é responsável pelo ARMAZENAMENTO (a lista é privada).
 * Os iteradores são responsáveis pela NAVEGAÇÃO.
 * Para o cliente existe apenas: iterator() e iteradorReverso(),
 * ambos devolvendo Iterator<Musica>.
 */
public class Playlist implements Iterable<Musica> {

    private final List<Musica> musicas = new ArrayList<>();

    public void adicionar(Musica musica) {
        musicas.add(musica);
    }

    @Override
    public Iterator<Musica> iterator() {
        return new IteradorNormal(musicas);
    }

    public Iterator<Musica> iteradorReverso() {
        return new IteradorReverso(musicas);
    }
}
