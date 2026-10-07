public record Musica(String titulo, String artista, int duracaoSegundos) {

    @Override
    public String toString() {
        return String.format("%s - %s (%d:%02d)", titulo, artista, duracaoSegundos / 60, duracaoSegundos % 60);
    }
}
