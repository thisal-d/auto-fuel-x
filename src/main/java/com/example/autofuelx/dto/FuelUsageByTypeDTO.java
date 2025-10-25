package com.example.autofuelx.dto;

public class FuelUsageByTypeDTO {
    // --- Existing Fields ---
    private int fuelID;
    private String fuelType;
    private double totalQuantity;
    private double totalCost;
    private int numberOfPurchases;
    private double averageCostPerPurchase;


    // Constructors
    public FuelUsageByTypeDTO() {
    }

    // --- Getters and Setters for Existing Fields ---
    public int getFuelID() {
        return fuelID;
    }

    public void setFuelID(int fuelID) {
        this.fuelID = fuelID;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public double getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(double totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public int getNumberOfPurchases() {
        return numberOfPurchases;
    }

    public void setNumberOfPurchases(int numberOfPurchases) {
        this.numberOfPurchases = numberOfPurchases;
    }

    public double getAverageCostPerPurchase() {
        return averageCostPerPurchase;
    }

    public void setAverageCostPerPurchase(double averageCostPerPurchase) {
        this.averageCostPerPurchase = averageCostPerPurchase;
    }
}