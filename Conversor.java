import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Conversor {

    private final String apiKey = "69ab747af64c39ef7d6df13f";
    private final String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/USD";

    public void realizarConversao(int opcao, double valor) {
        try {
            // Cliente HTTP e requisição
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Parse JSON
            JsonElement root = JsonParser.parseString(response.body());
            JsonObject jsonObj = root.getAsJsonObject();
            JsonObject rates = jsonObj.getAsJsonObject("conversion_rates");

            double convertido = 0.0;

            switch (opcao) {
                case 1: // USD -> BRL
                    convertido = valor * rates.get("BRL").getAsDouble();
                    System.out.printf("USD %.2f = BRL %.2f%n", valor, convertido);
                    break;
                case 2: // USD -> EUR
                    convertido = valor * rates.get("EUR").getAsDouble();
                    System.out.printf("USD %.2f = EUR %.2f%n", valor, convertido);
                    break;
                case 3: // USD -> ARS
                    convertido = valor * rates.get("ARS").getAsDouble();
                    System.out.printf("USD %.2f = ARS %.2f%n", valor, convertido);
                    break;
                case 4: // BRL -> USD
                    convertido = valor / rates.get("BRL").getAsDouble();
                    System.out.printf("BRL %.2f = USD %.2f%n", valor, convertido);
                    break;
                case 5: // EUR -> USD
                    convertido = valor / rates.get("EUR").getAsDouble();
                    System.out.printf("EUR %.2f = USD %.2f%n", valor, convertido);
                    break;
                case 6: // BRL -> EUR
                    double usd = valor / rates.get("BRL").getAsDouble(); // BRL -> USD
                    convertido = usd * rates.get("EUR").getAsDouble();   // USD -> EUR
                    System.out.printf("BRL %.2f = EUR %.2f%n", valor, convertido);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } catch (Exception e) {
            System.out.println("Erro ao realizar conversão: " + e.getMessage());
        }
    }
}
