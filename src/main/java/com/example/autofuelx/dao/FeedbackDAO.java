package com.example.autofuelx.dao;

import com.example.autofuelx.model.Feedback;
import com.example.autofuelx.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO {
    public void saveFeedback(Feedback feedback) {
        String sql = "INSERT INTO Feedback (Rate, Message, CreatedDate, CreatedTime, CustomerID) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, feedback.getRate());
            stmt.setString(2, feedback.getMessage());

            // Set current date and time
            LocalDate currentDate = LocalDate.now();
            LocalTime currentTime = LocalTime.now();
            stmt.setDate(3, java.sql.Date.valueOf(currentDate));
            stmt.setTime(4, java.sql.Time.valueOf(currentTime));

            stmt.setInt(5, feedback.getCustomerID());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteFeedback(int feedbackID) {
        String sql = "DELETE FROM Feedback WHERE FeedbackID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, feedbackID);
            stmt.executeUpdate();  // Fixed: executeUpdate() instead of executeQuery()
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Feedback> getAllFeedbacks() {
        String sql = "SELECT * FROM Feedback";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            List<Feedback> feedbacks = new ArrayList<Feedback>();
            while (rs.next()) {
                Feedback feedback = extractFeedbackFromResultSet(rs);
                feedbacks.add(feedback);
            }
            return feedbacks;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Feedback> getFeedbacksByCustomerID(int customerID) {
        String sql = "SELECT * FROM Feedback WHERE CustomerID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();
            List<Feedback> feedbacks = new ArrayList<>();
            while (rs.next()) {
                Feedback feedback = extractFeedbackFromResultSet(rs);
                feedbacks.add(feedback);
            }
            return feedbacks;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Feedback getFeedback(int feedbackID) {
        String sql = "SELECT * FROM Feedback WHERE FeedbackID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, feedbackID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractFeedbackFromResultSet(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Feedback extractFeedbackFromResultSet(ResultSet rs) {
        try {
            Feedback feedback = new Feedback();
            feedback.setFeedbackID(rs.getInt("FeedbackID"));
            feedback.setMessage(rs.getString("Message"));  // Fixed: "Message" instead of "Description"
            feedback.setCustomerID(rs.getInt("CustomerID"));
            feedback.setRate(rs.getInt("Rate"));

            // Handle separate date and time columns
            java.sql.Date createdDate = rs.getDate("CreatedDate");
            java.sql.Time createdTime = rs.getTime("CreatedTime");

            if (createdDate != null) {
                feedback.setCreatedDate(createdDate.toLocalDate());
            }

            if (createdTime != null) {
                feedback.setCreatedTime(createdTime.toLocalTime());
            }

            return feedback;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}