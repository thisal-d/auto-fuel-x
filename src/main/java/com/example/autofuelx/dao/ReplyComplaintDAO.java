package com.example.autofuelx.dao;

import com.example.autofuelx.model.ReplyComplaint;
import com.example.autofuelx.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReplyComplaintDAO {

    // Add a new reply complaint
    public boolean addReplyComplaint(ReplyComplaint replyComplaint) {
        String sql = "INSERT INTO ReplyComplaint (staffID, complaintID, title, description, createdDate, createdTime) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, replyComplaint.getStaffID());
            stmt.setInt(2, replyComplaint.getComplaintID());
            stmt.setString(3, replyComplaint.getTitle());
            stmt.setString(4, replyComplaint.getDescription());

            // Set current date and time
            java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
            java.sql.Time currentTime = new java.sql.Time(System.currentTimeMillis());
            stmt.setDate(5, currentDate);
            stmt.setTime(6, currentTime);

            stmt.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get reply complaint by ID
    public ReplyComplaint getReplyComplaintByComplaintID(int replyComplaintID) {
        String sql = "SELECT * FROM ReplyComplaint WHERE replyComplaintID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, replyComplaintID);

            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                return extractReplyComplaintFromResultSet(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get all reply complaints
    public List<ReplyComplaint> getAllReplyComplaints() {
        List<ReplyComplaint> replyComplaints = new ArrayList<>();
        String sql = "SELECT * FROM ReplyComplaint";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                replyComplaints.add(extractReplyComplaintFromResultSet(rs));
            }

            return replyComplaints;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update reply complaint
    public boolean updateReplyComplaint(ReplyComplaint replyComplaint) {
        String sql = "UPDATE ReplyComplaint SET staffID = ?, complaintID = ?, title = ?, description = ?, " +
                "updatedDate = ?, updateTime = ? WHERE replyComplaintID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, replyComplaint.getStaffID());
            stmt.setInt(2, replyComplaint.getComplaintID());
            stmt.setString(3, replyComplaint.getTitle());
            stmt.setString(4, replyComplaint.getDescription());

            java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
            java.sql.Time currentTime = new java.sql.Time(System.currentTimeMillis());

            stmt.setDate(5, currentDate);
            stmt.setTime(6, currentTime);

            stmt.setInt(7, replyComplaint.getReplyComplaintID());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete reply complaint
    public boolean deleteReplyComplaint(int replyComplaintID) {
        String sql = "DELETE FROM ReplyComplaint WHERE replyComplaintID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, replyComplaintID);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get reply complaints by complaint ID
    public List<ReplyComplaint> getReplyComplaintsByComplaintId(int complaintID) {
        List<ReplyComplaint> replyComplaints = new ArrayList<>();
        String sql = "SELECT * FROM ReplyComplaint WHERE complaintID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, complaintID);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                replyComplaints.add(extractReplyComplaintFromResultSet(rs));
            }
            return replyComplaints;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get reply complaints by staff ID
    public List<ReplyComplaint> getReplyComplaintsByStaffId(int staffID) {
        List<ReplyComplaint> replyComplaints = new ArrayList<>();
        String sql = "SELECT * FROM ReplyComplaint WHERE staffID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, staffID);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                replyComplaints.add(extractReplyComplaintFromResultSet(rs));
            }
            return replyComplaints;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Helper method to extract reply complaint from ResultSet
    private ReplyComplaint extractReplyComplaintFromResultSet(ResultSet rs) {
        try {
            ReplyComplaint replyComplaint = new ReplyComplaint();
            replyComplaint.setReplyComplaintID(rs.getInt("replyComplaintID"));
            replyComplaint.setStaffID(rs.getInt("staffID"));
            replyComplaint.setComplaintID(rs.getInt("complaintID"));
            replyComplaint.setTitle(rs.getString("title"));
            replyComplaint.setDescription(rs.getString("description"));
            replyComplaint.setCreatedDate(rs.getDate("createdDate"));
            replyComplaint.setCreatedTime(rs.getTime("createdTime"));
            replyComplaint.setUpdatedDate(rs.getDate("updatedDate"));
            replyComplaint.setUpdateTime(rs.getTime("updateTime"));
            return replyComplaint;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}