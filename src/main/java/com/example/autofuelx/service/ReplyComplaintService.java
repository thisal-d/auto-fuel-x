package com.example.autofuelx.service;

import com.example.autofuelx.dao.ReplyComplaintDAO;
import com.example.autofuelx.model.ReplyComplaint;

public class ReplyComplaintService {

    private final ReplyComplaintDAO replyComplaintDAO;

    public ReplyComplaintService() {
        this.replyComplaintDAO = new ReplyComplaintDAO();
    }

    public void submitReplyComplaint(ReplyComplaint replyComplaint) {
        boolean success = replyComplaintDAO.addReplyComplaint(replyComplaint);
        System.out.println("Reply Complaint " + replyComplaint.getReplyComplaintID() + " Added : " + success);
    }

    // Update reply
    public void updateReplyComplaint(ReplyComplaint replyComplaint) {
        boolean success =  replyComplaintDAO.updateReplyComplaint(replyComplaint);
        System.out.println("Reply Complaint " + replyComplaint.getReplyComplaintID() + " Updating : " + success);
    }

    public void updateReplyComplaintStatus(int replyComplaintID, String status) {
        boolean success = replyComplaintDAO.updateReplyComplaintStatus(replyComplaintID, status);
        System.out.println("Reply Complaint Status " + replyComplaintID + " Updating : " + success);
    }

    //    // Delete reply
    //    public boolean deleteReply(int replyComplaintID) {
    //        return replyComplaintDAO.deleteReplyComplaint(replyComplaintID);
    //    }

    // Get reply by ID
    public ReplyComplaint getReplyComplaintByReplyComplaintID(int replyComplaintID) {
        return replyComplaintDAO.getReplyComplaintByComplaintID(replyComplaintID);
    }

    public ReplyComplaint getReplyComplaintByComplaintID(int ComplaintID) {
        return replyComplaintDAO.getReplyComplaintByComplaintID(ComplaintID);
    }


    //    // Get all replies
    //    public List<ReplyComplaint> getAllReplyComplaints() {
    //        return replyComplaintDAO.getAllReplyComplaints();
    //    }


    //    // Get replies by staff ID
    //    public List<ReplyComplaint> getReplyComplaintByStaffID(int staffID) {
    //        return replyComplaintDAO.getReplyComplaintsByStaffId(staffID);
    //    }
}
