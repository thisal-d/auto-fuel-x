package com.example.autofuelx.model;

public class EmployeePhoneNumber {
    private int employeeID;
    private String phoneNumber;

    public EmployeePhoneNumber() {}

    public EmployeePhoneNumber(int employeeID, String phoneNumber) {
        this.employeeID = employeeID;
        this.phoneNumber = phoneNumber;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
