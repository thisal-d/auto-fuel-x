package com.example.autofuelx.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class FuelPurchaseDetailDTO {
    private int fuelPurchaseID;

    // Customer info
    private String customerName;     // e.g. "John Doe"
    private String customerEmail;    // useful for admin

    // Vehicle info
    private String vehiclePlate;     // e.g. "ABC-1234"
    private String vehicleModel;     // e.g. "Toyota Corolla"
    private String vehicleType;      // Fixed: consistent naming

    // Fuel info
    private String fuelType;
    private double quantity;
    private double totalCost;

    // Date and time separated
    private LocalDate purchaseDate;
    private LocalTime purchaseTime;

    // Getters and Setters
    public int getFuelPurchaseID() {
        return fuelPurchaseID;
    }

    public void setFuelPurchaseID(int fuelPurchaseID) {
        this.fuelPurchaseID = fuelPurchaseID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public void setVehiclePlate(String vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
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

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}