package negocio;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import persistencia.ConexaoSQlite;

public class PokemonProxy implements API {

    private PokemonAPI pokemonAPI;

    public PokemonProxy() {
        this.pokemonAPI = new PokemonAPI();
    }

    @Override
    public Pokemon getPokemon(String pokemonName) throws IOException, InterruptedException {
        Pokemon pokemon = null;
        // se eu tenho no meu cache - chamo meu cache
        String sql = "select * from pokemon where name = '" + pokemonName + "';";
        try {
            Connection conexao = new ConexaoSQlite().getConexao();
            long startTime = System.nanoTime();
            ResultSet rs = conexao.prepareStatement(sql).executeQuery();
            if (rs.next()) {
                long endTime = System.nanoTime();
                // Calcula a duração em nanossegundos
                long duration = (endTime - startTime);
                System.out.println("Com o BD:"+duration);
                return new Pokemon(rs.getString("name"), rs.getString("image"));
            } else {
                pokemon = this.pokemonAPI.getPokemon(pokemonName);
                sql = "insert into pokemon (name, image) values('" + pokemon.getName() + "', '"+pokemon.getImage()+"');";
                conexao.prepareStatement(sql).execute();
            }
            conexao.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // se não, inevitavelmente chamo a metodo da classe real
        return pokemon;
    }

}
