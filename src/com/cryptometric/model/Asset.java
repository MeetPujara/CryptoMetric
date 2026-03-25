package com.cryptometric.model;

public class Asset {
    private int id;
    private String symbol;
    private double quantity;
    private double buyPrice;
    private double currentPrice;

    // Constructor for adding new assets
    public Asset(String symbol, double quantity, double buyPrice) {
        this.symbol = symbol.toUpperCase();
        this.quantity = quantity;
        this.buyPrice = buyPrice;
    }

    // Getters
    public int getId() { return id; }
    public String getSymbol() { return symbol; }
    public double getQuantity() { return quantity; }
    public double getBuyPrice() { return buyPrice; }
    public double getCurrentPrice() { return currentPrice; }

    // Setter for the API to update the live price later
    public void setCurrentPrice(double currentPrice) { this.currentPrice = currentPrice; }
    public void setId(int id) { this.id = id; }
}