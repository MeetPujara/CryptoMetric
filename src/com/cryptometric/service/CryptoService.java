package com.cryptometric.service;

import com.cryptometric.api.CMCClient;
import com.cryptometric.dao.PortfolioDAO;
import com.cryptometric.model.Asset;
import java.util.List;

public class CryptoService {
    private PortfolioDAO dao = new PortfolioDAO();

    public void displayPortfolioReport() {
        List<Asset> myAssets = dao.getAllAssets();

        System.out.println("\n--- CRYPTOMETRIC REAL-TIME REPORT ---");
        System.out.printf("%-10s %-10s %-12s %-12s %-10s%n", "SYMBOL", "QTY", "BUY PRICE", "LIVE PRICE", "P&L ($)");
        System.out.println("------------------------------------------------------------");

        double totalPortfolioValue = 0;

        for (Asset asset : myAssets) {
            // 1. Fetch live price from API
            double livePrice = CMCClient.getLatestPrice(asset.getSymbol());
            asset.setCurrentPrice(livePrice);

            // 2. Calculate Profit/Loss
            double pnl = (livePrice - asset.getBuyPrice()) * asset.getQuantity();
            totalPortfolioValue += (livePrice * asset.getQuantity());

            // 3. Print formatted row
            System.out.printf("%-10s %-10.4f $%-11.2f $%-11.2f $%-10.2f%n",
                    asset.getSymbol(), asset.getQuantity(), asset.getBuyPrice(), livePrice, pnl);
        }

        System.out.println("------------------------------------------------------------");
        System.out.printf("TOTAL CURRENT PORTFOLIO VALUE: $%.2f%n", totalPortfolioValue);
    }
}