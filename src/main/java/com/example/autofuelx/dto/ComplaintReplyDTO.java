package com.example.autofuelx.dto;

import java.sql.Date;
import java.sql.Time;

public class ComplaintReplyDTO {
    // Complaint fields
    private int complaintID;
    private int customerID;
    private String title;
    private String description;
    private String status;
    private Date createdDate;
    private Time createdTime;
    private Date updatedDate;
    private Time updateTime;

    // Reply fields
    private Integer replyComplaintID; // Can be null if no reply
    private Integer staffID; // Can be null if no reply
    private String replyTitle;
    private String replyDescription;
    private String replyStatus;
    private Date replyCreatedDate;
    private Time replyCreatedTime;
    private Date replyUpdatedDate;
    private Time replyUpdateTime;

    // Employee fields (from Employee table)
    private String repliedEmployeeName;
    private String repliedEmployeeType;


    // Getters and Setters
    public int getComplaintID() {
        return complaintID;
    }

    public void setComplaintID(int complaintID) {
        this.complaintID = complaintID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Time getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Time createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Time getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Time updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getReplyComplaintID() {
        return replyComplaintID;
    }

    public void setReplyComplaintID(Integer replyComplaintID) {
        this.replyComplaintID = replyComplaintID;
    }

    public Integer getStaffID() {
        return staffID;
    }

    public void setStaffID(Integer staffID) {
        this.staffID = staffID;
    }

    public String getReplyStatus() {
        return replyStatus;
    }

    public void setReplyStatus(String replyStatus) {
        this.replyStatus = replyStatus;
    }

    public String getReplyTitle() {
        return replyTitle;
    }

    public void setReplyTitle(String replyTitle) {
        this.replyTitle = replyTitle;
    }

    public String getReplyDescription() {
        return replyDescription;
    }

    public void setReplyDescription(String replyDescription) {
        this.replyDescription = replyDescription;
    }

    public Date getReplyCreatedDate() {
        return replyCreatedDate;
    }

    public void setReplyCreatedDate(Date replyCreatedDate) {
        this.replyCreatedDate = replyCreatedDate;
    }

    public Time getReplyCreatedTime() {
        return replyCreatedTime;
    }

    public void setReplyCreatedTime(Time replyCreatedTime) {
        this.replyCreatedTime = replyCreatedTime;
    }

    public Date getReplyUpdatedDate() {
        return replyUpdatedDate;
    }

    public void setReplyUpdatedDate(Date replyUpdatedDate) {
        this.replyUpdatedDate = replyUpdatedDate;
    }

    public Time getReplyUpdateTime() {
        return replyUpdateTime;
    }

    public void setReplyUpdateTime(Time replyUpdateTime) {
        this.replyUpdateTime = replyUpdateTime;
    }

    public String getRepliedEmployeeName() {
        return repliedEmployeeName;
    }

    public void setRepliedEmployeeName(String repliedEmployeeName) {
        this.repliedEmployeeName = repliedEmployeeName;
    }

    public String getRepliedEmployeeType() {
        return repliedEmployeeType;
    }

    public void setRepliedEmployeeType(String repliedEmployeeType) {
        this.repliedEmployeeType = repliedEmployeeType;
    }


}