package com.example.autofuelx.model;

public class CustomerPhoneNumber {
    private int customerID;
    private String phoneNumber;

    public CustomerPhoneNumber() {
    }

    public CustomerPhoneNumber(int customerID, String phoneNumber) {
        this.customerID = customerID;
        this.phoneNumber = phoneNumber;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
