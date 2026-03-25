package com.cryptometric.api;

import com.cryptometric.database.ConfigLoader;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CMCClient {
    // Replace this with your actual API Key from CoinMarketCap
    private static final String API_KEY = ConfigLoader.get("CMC_API_KEY");
    private static final String BASE_URL = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest";

    public static double getLatestPrice(String symbol) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "?symbol=" + symbol))
                    .header("X-CMC_PRO_API_KEY", API_KEY)
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Parsing the JSON response using GSON
            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();

            // Navigate through the JSON structure: data -> SYMBOL -> quote -> USD -> price
            return jsonObject.getAsJsonObject("data")
                    .getAsJsonObject(symbol.toUpperCase())
                    .getAsJsonObject("quote")
                    .getAsJsonObject("USD")
                    .get("price").getAsDouble();

        } catch (Exception e) {
            System.out.println("Error fetching price for " + symbol + ": " + e.getMessage());
            return 0.0;
        }
    }
    public static void showTopCoins() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?limit=10"))
                    .header("X-CMC_PRO_API_KEY", API_KEY)
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();

            System.out.print("Popular Symbols: ");
            json.getAsJsonArray("data").forEach(element -> {
                System.out.print(element.getAsJsonObject().get("symbol").getAsString() + " ");
            });
            System.out.println(); // New line
        } catch (Exception e) {
            System.out.println("Could not load symbols.");
        }
    }
}