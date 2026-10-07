import java.util.Iterator;

public class Main {
    public static void main(String[] args) {

        Playlist playlist = new Playlist();
        playlist.adicionar(new Musica("Faixa A", "Artista 1", 185));
        playlist.adicionar(new Musica("Faixa B", "Artista 2", 210));
        playlist.adicionar(new Musica("Faixa C", "Artista 3", 98));

        System.out.println("--- Normal ---");
        Iterator<Musica> iterator = playlist.iterator();
        while (iterator.hasNext()) {
            Musica musica = iterator.next();
            System.out.println(musica);
        }

        // O código de travessia é o MESMO; só muda qual iterador foi pedido
        System.out.println("--- Reverso ---");
        iterator = playlist.iteradorReverso();
        while (iterator.hasNext()) {
            Musica musica = iterator.next();
            System.out.println(musica);
        }
    }
}
