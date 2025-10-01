package com.example.autofuelx.service;

import com.example.autofuelx.dao.ReplyComplaintDAO;
import com.example.autofuelx.model.ReplyComplaint;

import java.util.List;

public class ReplyComplaintService {

    private final ReplyComplaintDAO replyComplaintDAO;

    public ReplyComplaintService() {
        this.replyComplaintDAO = new ReplyComplaintDAO();
    }

    public boolean submitReplyComplaint(ReplyComplaint replyComplaint) {
        return replyComplaintDAO.addReplyComplaint(replyComplaint);

    }

    // Update reply
    public boolean updateReplyComplaint(ReplyComplaint replyComplaint) {
        return replyComplaintDAO.updateReplyComplaint(replyComplaint);
    }

    public boolean updateReplyComplaintStatus(int replyComplaintID, String status) {
        return replyComplaintDAO.updateReplyComplaintStatus(replyComplaintID, status);
    }

    // Delete reply
    public boolean deleteReply(int replyComplaintID) {
        return replyComplaintDAO.deleteReplyComplaint(replyComplaintID);
    }

    // Get reply by ID
    public ReplyComplaint getReplyComplaintByReplyComplaintID(int replyComplaintID) {
        return replyComplaintDAO.getReplyComplaintByComplaintID(replyComplaintID);
    }

    public ReplyComplaint getReplyComplaintByComplaintID(int ComplaintID) {
        return replyComplaintDAO.getReplyComplaintByComplaintID(ComplaintID);
    }

    // Get all replies
    public List<ReplyComplaint> getAllReplyComplaints() {
        return replyComplaintDAO.getAllReplyComplaints();
    }


    // Get replies by staff ID
    public List<ReplyComplaint> getReplyComplaintByStaffID(int staffID) {
        return replyComplaintDAO.getReplyComplaintsByStaffId(staffID);
    }
}
