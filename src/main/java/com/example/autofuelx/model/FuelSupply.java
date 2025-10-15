package com.example.autofuelx.model;

import java.sql.Time;
import java.sql.Date;

public class FuelSupply {
    private int supplyID;
    private int supplierID;
    private int fuelID;
    private double quantity;
    private Date supplyDate;
    private Time supplyTime;

    // Constructors
    public FuelSupply() {}

    public FuelSupply(int supplierID, int fuelID, double quantity, Date supplyDate, Time supplyTime) {
        this.supplierID = supplierID;
        this.fuelID = fuelID;
        this.quantity = quantity;
        this.supplyDate = supplyDate;
        this.supplyTime = supplyTime;
    }

    // Getters and Setters
    public int getSupplyID() { return supplyID; }
    public void setSupplyID(int supplyID) { this.supplyID = supplyID; }

    public int getSupplierID() { return supplierID; }
    public void setSupplierID(int supplierID) { this.supplierID = supplierID; }

    public int getFuelID() { return fuelID; }
    public void setFuelID(int fuelID) { this.fuelID = fuelID; }

    public double getQuantity() { return quantity; }
    public void setQuantity(double quantity) { this.quantity = quantity; }

    public Date getSupplyDate() { return supplyDate; }
    public void setSupplyDate(Date supplyDate) { this.supplyDate = supplyDate; }

    public Time getSupplyTime() { return supplyTime; }
    public void setSupplyTime(Time supplyTime) { this.supplyTime = supplyTime; }
}
