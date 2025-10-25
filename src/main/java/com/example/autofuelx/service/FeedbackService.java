package com.example.autofuelx.service;


import com.example.autofuelx.dao.FeedbackDAO;
import com.example.autofuelx.model.Feedback;


public class FeedbackService {
    FeedbackDAO feedbackDAO;

    public FeedbackService() {
        feedbackDAO = new FeedbackDAO();
    }

    public void submitFeedback(Feedback feedback) {
        feedbackDAO.saveFeedback(feedback);
    }

    public void deleteComplaint(int feedbackID) {
        feedbackDAO.deleteFeedback(feedbackID);
    }

    //    public List<Feedback> getComplaintsByUserID(int userID) {
    //        return feedbackDAO.getFeedbacksByCustomerID(userID);
    //    }

}
