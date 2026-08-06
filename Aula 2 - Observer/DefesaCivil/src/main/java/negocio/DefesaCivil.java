package negocio;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONObject;

public class DefesaCivil implements Subject {
    private final List<Observer> vetObserver = new ArrayList<>();
    private final HttpClient client = HttpClient.newHttpClient();
    private String climaAtual = "0"; // Mantém o estado real

    public void changedState() {
        double latitude = -32.035;
        double longitude = -52.0986;

        String urlString = String.format(Locale.US,
            "https://api.open-meteo.com/v1/forecast?latitude=%.4f&longitude=%.4f&hourly=temperature_2m",
            latitude, longitude);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JSONObject jsonObject = new JSONObject(response.body());
                JSONArray temperaturas = jsonObject.getJSONObject("hourly").getJSONArray("temperature_2m");

                if (temperaturas.length() > 0) {
                    // Pega a temperatura atual/primeira posição previsível
                    String novaTemperatura = temperaturas.get(0).toString().trim();

                    // Notifica APENAS se a temperatura realmente mudou
                    if (!novaTemperatura.equals(this.climaAtual)) {
                        this.climaAtual = novaTemperatura;
                        this.notifyObservers();
                    }
                }
            } else {
                System.err.println("Erro na requisição API. Status: " + response.statusCode());
            }

        } catch (Exception e) {
            System.err.println("Erro ao conectar com a API: " + e.getMessage());
        }
    }

    @Override
    public void addObserver(Observer observer) {
        this.vetObserver.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.vetObserver.remove(observer);
    }

    @Override
    public void removeObserver(int pos) {
        if (pos >= 0 && pos < this.vetObserver.size()) {
            this.vetObserver.remove(pos);
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : this.vetObserver) {
            observer.update("Mudando de Clima: " + this.climaAtual + "°C");
        }
    }
}