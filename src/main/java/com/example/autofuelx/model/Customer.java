package com.example.autofuelx.model;

public class Customer {
    private int customerID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String addressNo;
    private String addressLane;
    private String addressArea;

    // getters and setters
    public int getCustomerID() { return customerID; }
    public void setCustomerID(int customerID) { this.customerID = customerID; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getAddressNo() { return addressNo; }
    public void setAddressNo(String addressNo) { this.addressNo = addressNo; }

    public String getAddressLane() { return addressLane; }
    public void setAddressLane(String addressLane) { this.addressLane = addressLane; }

    public String getAddressArea() { return addressArea; }
    public void setAddressArea(String addressArea) { this.addressArea = addressArea; }
}
