package com.example.autofuelx.model;

public class Fuel {
    private int fuelID;
    private String type;
    private double quantity;
    private double costPerLiter;

    // Constructors
    public Fuel() {
    }

    public Fuel(int fuelID, String type, double quantity, double costPerLiter) {
        this.fuelID = fuelID;
        this.type = type;
        this.quantity = quantity;
        this.costPerLiter = costPerLiter;
    }

    // Getters & Setters
    public int getFuelID() {
        return fuelID;
    }

    public void setFuelID(int fuelID) {
        this.fuelID = fuelID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getCostPerLiter() {
        return costPerLiter;
    }

    public void setCostPerLiter(double costPerLiter) {
        this.costPerLiter = costPerLiter;
    }
}
