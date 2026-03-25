package com.cryptometric;

import com.cryptometric.api.CMCClient;
import com.cryptometric.dao.PortfolioDAO;
import com.cryptometric.model.Asset;
import com.cryptometric.service.CryptoService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PortfolioDAO dao = new PortfolioDAO();
        CryptoService service = new CryptoService();

        while (true) {
            System.out.println("\n--- CRYPTOMETRIC MAIN MENU ---");
            System.out.println("1. Add New Asset (Buy)");
            System.out.println("2. View Real-Time Portfolio Report");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");

            int choice = sc.nextInt();
            if (choice == 3) break;

            switch (choice) {
                case 1:
                    // Show top 10 first so they know what to type
                    CMCClient.showTopCoins();

                    System.out.print("\nEnter Coin Symbol (e.g., BTC): ");
                    String symbol = sc.next().toUpperCase();

                    // NEW: Fetch and show the price immediately
                    double livePrice = CMCClient.getLatestPrice(symbol);

                    if (livePrice == 0.0) {
                        System.out.println("Could not find price for " + symbol + ". Check the symbol and try again.");
                    } else {
                        System.out.printf("Current Live Price: $%.2f%n", livePrice);

                        System.out.print("Enter Quantity to buy: ");
                        double qty = sc.nextDouble();

                        // Use the livePrice we just fetched as the buy_price
                        dao.addAsset(new Asset(symbol, qty, livePrice));
                        System.out.println("Purchase recorded at current market price.");
                    }
                    break;
                case 2:
                    service.displayPortfolioReport();
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        System.out.println("Exiting CryptoMetric. Happy Trading!");
        sc.close();
    }
}