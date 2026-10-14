package negocio;

import java.io.IOException;

public interface API {
    public Pokemon getPokemon(String pokemonName) throws IOException, InterruptedException;


}
