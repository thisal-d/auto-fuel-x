package com.example.autofuelx.dto;

public class ComplaintSummaryDTO {
    // Complaint counts
    private int totalComplaints;
    private int activeComplaints;
    private int unreadComplaints;

    public int getTotalComplaints() {
        return totalComplaints;
    }

    public void setTotalComplaints(int totalComplaints) {
        this.totalComplaints = totalComplaints;
    }

    public int getActiveComplaints() {
        return activeComplaints;
    }

    public void setActiveComplaints(int activeComplaints) {
        this.activeComplaints = activeComplaints;
    }

    public int getUnreadComplaints() {
        return unreadComplaints;
    }

    public void setUnreadComplaints(int unreadComplaints) {
        this.unreadComplaints = unreadComplaints;
    }
}