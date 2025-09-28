package com.example.autofuelx.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Complaint {
    private int complaintID;
    private String title;
    private String description;
    private String status;
    private LocalDate createdDate;
    private LocalTime createdTime;
    private int customerID;
    private Integer staffID;      // staff may be null if not yet assigned
    private String replyText;
    private LocalDate repliedDate;
    private LocalTime repliedTime;

    // --- Constructors ---
    public Complaint() {}

    public Complaint(int complaintID, String title, String description, String status,
                     LocalDate createdDate, LocalTime createdTime, int customerID, Integer staffID,
                     String replyText, LocalDate repliedDate, LocalTime repliedTime) {
        this.complaintID = complaintID;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdDate = createdDate;
        this.createdTime = createdTime;
        this.customerID = customerID;
        this.staffID = staffID;
        this.replyText = replyText;
        this.repliedDate = repliedDate;
        this.repliedTime = repliedTime;
    }

    // --- Getters & Setters ---
    public int getComplaintID() {
        return complaintID;
    }

    public void setComplaintID(int complaintId) {
        this.complaintID = complaintId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Integer getStaffID() {
        return staffID;
    }

    public void setStaffID(Integer staffID) {
        this.staffID = staffID;
    }

    public String getReplyText() {
        return replyText;
    }

    public void setReplyText(String replyText) {
        this.replyText = replyText;
    }

    public LocalDate getRepliedDate() {
        return repliedDate;
    }

    public void setRepliedDate(LocalDate repliedDate) {
        this.repliedDate = repliedDate;
    }

    public LocalTime getRepliedTime() {
        return repliedTime;
    }

    public void setRepliedTime(LocalTime repliedTime) {
        this.repliedTime = repliedTime;
    }

    // --- toString() for debugging ---
    @Override
    public String toString() {
        return "Complaint{" +
                "complaintId=" + complaintID +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", createdDate=" + createdDate +
                ", createdTime=" + createdTime +
                ", customerId=" + customerID +
                ", staffId=" + staffID +
                ", replyText='" + replyText + '\'' +
                ", repliedDate=" + repliedDate +
                ", repliedTime=" + repliedTime +
                '}';
    }
}