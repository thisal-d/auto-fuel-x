package com.example.autofuelx.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class FuelPurchase {
    private int fuelPurchaseID;
    private int customerID;
    private int fuelID;
    private int vehicleID;
    private double quantity;
    private double totalCost;
    private LocalDate purchaseDate;
    private LocalTime purchaseTime;

    // Getters and Setters
    public int getFuelPurchaseID() {
        return fuelPurchaseID;
    }

    public void setFuelPurchaseID(int fuelPurchaseID) {
        this.fuelPurchaseID = fuelPurchaseID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getFuelID() {
        return fuelID;
    }

    public void setFuelID(int fuelID) {
        this.fuelID = fuelID;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalTime getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(LocalTime purchaseTime) {
        this.purchaseTime = purchaseTime;
    }
}