package negocio;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class PokemonAPI implements API {

    public Pokemon getPokemon(String pokemonName) throws IOException, InterruptedException {
        // Gson gson = new Gson();

        // 1. Create a client
        HttpClient client = HttpClient.newHttpClient();

        // 2. Build the request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://pokeapi.co/api/v2/pokemon/" + pokemonName))
                .header("Accept", "application/json")
                .GET()
                .build();

        // 3. Send the request and get the response
        long startTime = System.nanoTime();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        // Captura o tempo final
        long endTime = System.nanoTime();
        // Calcula a duração em nanossegundos
        long duration = (endTime - startTime);

        // 4. Print results
        System.out.println("Status Code: " + response.statusCode());
        JsonObject obj = JsonParser.parseString(response.body()).getAsJsonObject();
        // System.out.println(obj.get("forms").getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString());
        System.out.println(duration);
        // System.out.println("Response: " + response.body());
        String name = obj.get("forms").getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString();
        String image = obj.get("sprites").getAsJsonObject().get("front_default").getAsString();
        return new Pokemon(name, image);
    }
}
