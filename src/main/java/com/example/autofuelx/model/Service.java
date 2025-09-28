package com.example.autofuelx.model;


public class Service {

    private int serviceID;
    private String type;
    private String description;
    private double cost;

    // Constructors
    public Service() {
    }

    public Service(int serviceID, String type, String description, double cost) {
        this.serviceID = serviceID;
        this.type = type;
        this.description = description;
        this.cost = cost;
    }

    // Getters and Setters
    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Optional: toString() for debugging
    @Override
    public String toString() {
        return "Service{" +
                "serviceID=" + serviceID +
                ", type='" + type + '\'' +
                ", cost=" + cost +
                '}';
    }
}
