package com.example.autofuelx.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class ServiceBooking {
    private int bookingID;
    private int customerID;
    private int vehicleID;
    private int serviceID;
    private Integer staffID; // can be null if not assigned
    private LocalDate bookingDate;
    private LocalTime bookingTime;
    private String status;
    private double totalCost;

    public ServiceBooking() {
    }

    public ServiceBooking(int bookingID, int customerID, int vehicleID, int serviceID, Integer staffID,
                          LocalDate bookingDate, LocalTime bookingTime, String status, double totalCost) {
        this.bookingID = bookingID;
        this.customerID = customerID;
        this.vehicleID = vehicleID;
        this.serviceID = serviceID;
        this.staffID = staffID;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.status = status;
        this.totalCost = totalCost;
    }

    // --- Getters & Setters ---
    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public Integer getStaffID() {
        return staffID;
    }

    public void setStaffID(Integer staffID) {
        this.staffID = staffID;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    // --- ToString ---
    @Override
    public String toString() {
        return "Booking{" +
                "bookingID=" + bookingID +
                ", customerID=" + customerID +
                ", vehicleID=" + vehicleID +
                ", serviceID=" + serviceID +
                ", staffID=" + staffID +
                ", bookingDate=" + bookingDate +
                ", bookingTime=" + bookingTime +
                ", status='" + status + '\'' +
                '}';
    }
}