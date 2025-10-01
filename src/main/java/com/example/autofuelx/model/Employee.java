package com.example.autofuelx.model;


import java.sql.Date;

public class Employee {
    private int employeeID;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private double salary;
    private String status;
    private Date hireDate;
    private String addressNo;
    private String addressLane;
    private String addressArea;
    private String email;
    private String password;
    private String skillSet;
    private String role;
    private String shift;
    private String type;

    // Constructors
    public Employee() {}

    public Employee(int employeeID, String firstName, String lastName, Date dateOfBirth,
                    double salary, String status, Date hireDate, String addressNo,
                    String addressLane, String addressArea, String email, String password, String skillSet,
                    String role, String shift, String type) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.status = status;
        this.hireDate = hireDate;
        this.addressNo = addressNo;
        this.addressLane = addressLane;
        this.addressArea = addressArea;
        this.email = email;
        this.skillSet = skillSet;
        this.role = role;
        this.shift = shift;
        this.type = type;
        this.password = password;
    }

    // Getters and Setters
    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getAddressNo() {
        return addressNo;
    }

    public void setAddressNo(String addressNo) {
        this.addressNo = addressNo;
    }

    public String getAddressLane() {
        return addressLane;
    }

    public void setAddressLane(String addressLane) {
        this.addressLane = addressLane;
    }

    public String getAddressArea() {
        return addressArea;
    }

    public String getFullyAddress(){
        return addressNo + " " +  addressLane + " " + addressArea;
    }

    public void setAddressArea(String addressArea) {
        this.addressArea = addressArea;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSkillSet() {
        return skillSet;
    }

    public void setSkillSet(String skillSet) {
        this.skillSet = skillSet;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}