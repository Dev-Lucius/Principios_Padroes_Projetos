package apresentacao;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import negocio.Pokemon;
import negocio.PokemonAPI;
import negocio.PokemonProxy;
import persistencia.ConexaoSQlite;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Acessando diretamente a api:");
        System.out.println(new PokemonAPI().getPokemon("pikachu").getImage());
        System.out.println(new PokemonAPI().getPokemon("pikachu").getImage());

        System.out.println("acessando o proxy");
        PokemonProxy pokemonProxyApi = new PokemonProxy();
        Pokemon pokemonProxy = pokemonProxyApi.getPokemon("CHARMANDER");
        Runtime.getRuntime().exec("google-chrome "+pokemonProxy.getImage());
       
   
            // Connection conexao = new ConexaoSQlite().getConexao();
        // System.out.println(conexao);
    }
}