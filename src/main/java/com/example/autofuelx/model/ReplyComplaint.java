package com.example.autofuelx.model;

import java.sql.Date;
import java.sql.Time;

public class ReplyComplaint {

    private int replyComplaintID;
    private int staffID;
    private int complaintID;
    private String title;
    private String description;
    private Date createdDate;
    private Time createdTime;
    private Date updatedDate;
    private Time updateTime;

    // Getters and Setters
    public int getReplyComplaintID() {
        return replyComplaintID;
    }

    public void setReplyComplaintID(int replyComplaintID) {
        this.replyComplaintID = replyComplaintID;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public int getComplaintID() {
        return complaintID;
    }

    public void setComplaintID(int complaintID) {
        this.complaintID = complaintID;
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
}
