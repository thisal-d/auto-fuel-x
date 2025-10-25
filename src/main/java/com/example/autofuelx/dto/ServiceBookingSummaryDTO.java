package com.example.autofuelx.dto;

public class ServiceBookingSummaryDTO {
    // Core Summary Counts
    private int totalActiveBookings;
    private int totalCompletedBookings;
    private int totalBookings;

    // Detailed Status Counts
    private int totalAwaitingConfirmation;
    private int totalConfirmed;
    private int totalInProgress;
    private int totalMissedAppointment;
    private int totalAwaitingPickup;
    private int totalRescheduleRequired;
    private int totalCancelled;

    // Constructors
    public ServiceBookingSummaryDTO() {
    }

    // Getters and Setters
    public int getTotalActiveBookings() {
        return totalActiveBookings;
    }

    public void setTotalActiveBookings(int totalActiveBookings) {
        this.totalActiveBookings = totalActiveBookings;
    }

    public int getTotalCompletedBookings() {
        return totalCompletedBookings;
    }

    public void setTotalCompletedBookings(int totalCompletedBookings) {
        this.totalCompletedBookings = totalCompletedBookings;
    }

    public int getTotalBookings() {
        return totalBookings;
    }

    public void setTotalBookings(int totalBookings) {
        this.totalBookings = totalBookings;
    }

    public int getTotalAwaitingConfirmation() {
        return totalAwaitingConfirmation;
    }

    public void setTotalAwaitingConfirmation(int totalAwaitingConfirmation) {
        this.totalAwaitingConfirmation = totalAwaitingConfirmation;
    }

    public int getTotalConfirmed() {
        return totalConfirmed;
    }

    public void setTotalConfirmed(int totalConfirmed) {
        this.totalConfirmed = totalConfirmed;
    }

    public int getTotalInProgress() {
        return totalInProgress;
    }

    public void setTotalInProgress(int totalInProgress) {
        this.totalInProgress = totalInProgress;
    }

    public int getTotalMissedAppointment() {
        return totalMissedAppointment;
    }

    public void setTotalMissedAppointment(int totalMissedAppointment) {
        this.totalMissedAppointment = totalMissedAppointment;
    }

    public int getTotalAwaitingPickup() {
        return totalAwaitingPickup;
    }

    public void setTotalAwaitingPickup(int totalAwaitingPickup) {
        this.totalAwaitingPickup = totalAwaitingPickup;
    }

    public int getTotalRescheduleRequired() {
        return totalRescheduleRequired;
    }

    public void setTotalRescheduleRequired(int totalRescheduleRequired) {
        this.totalRescheduleRequired = totalRescheduleRequired;
    }

    public int getTotalCancelled() {
        return totalCancelled;
    }

    public void setTotalCancelled(int totalCancelled) {
        this.totalCancelled = totalCancelled;
    }
}