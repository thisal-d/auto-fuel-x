package com.example.autofuelx.dao;

import com.example.autofuelx.model.Complaint;
import com.example.autofuelx.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ComplaintDAO {

    public void saveComplaint(Complaint complaint) {
        String sql = "INSERT INTO Complaint (Title, Description, CreatedDate, CreatedTime, CustomerID) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, complaint.getTitle());
            stmt.setString(2, complaint.getDescription());

            // Set current date and time
            LocalDate currentDate = LocalDate.now();
            LocalTime currentTime = LocalTime.now();
            stmt.setDate(3, java.sql.Date.valueOf(currentDate));
            stmt.setTime(4, java.sql.Time.valueOf(currentTime));

            stmt.setInt(5, complaint.getCustomerID());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateComplaint(Complaint complaint) {
        // Only update title and description, not the creation date/time
        String sql = "UPDATE Complaint SET Title = ?, Description = ? WHERE ComplaintID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, complaint.getTitle());
            stmt.setString(2, complaint.getDescription());
            stmt.setInt(3, complaint.getComplaintID());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // New method to handle reply updates
    public void updateComplaintReply(Complaint complaint) {
        String sql = "UPDATE Complaint SET ReplyText = ?, RepliedDate = ?, RepliedTime = ?, StaffID = ?, Status = ? WHERE ComplaintID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, complaint.getReplyText());

            // Set current date and time for reply
            LocalDate currentDate = LocalDate.now();
            LocalTime currentTime = LocalTime.now();
            stmt.setDate(2, java.sql.Date.valueOf(currentDate));
            stmt.setTime(3, java.sql.Time.valueOf(currentTime));

            if (complaint.getStaffID() != null) {
                stmt.setInt(4, complaint.getStaffID());
            } else {
                stmt.setNull(4, java.sql.Types.INTEGER);
            }

            stmt.setString(5, complaint.getStatus());
            stmt.setInt(6, complaint.getComplaintID());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteComplaint(int complaintID) {
        String sql = "DELETE FROM Complaint WHERE ComplaintID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, complaintID);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Complaint> getComplaintsByCustomerID(int customerID) {
        String sql = "SELECT * FROM Complaint WHERE CustomerID = ?";
        List<Complaint> complaints = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Complaint complaint = extractComplaintFromResultSet(rs);
                complaints.add(complaint);
            }
            return complaints;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Complaint getComplaintByID(int complaintID) {
        String sql = "SELECT * FROM Complaint WHERE ComplaintID = ?";
        Complaint complaint = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, complaintID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                complaint = extractComplaintFromResultSet(rs);
                return complaint;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Complaint extractComplaintFromResultSet(ResultSet rs) {
        try {
            Complaint complaint = new Complaint();
            complaint.setComplaintID(rs.getInt("ComplaintID"));
            complaint.setTitle(rs.getString("Title"));
            complaint.setDescription(rs.getString("Description"));
            complaint.setCustomerID(rs.getInt("CustomerID"));
            complaint.setStatus(rs.getString("Status"));

            // Handle created date and time
            java.sql.Date createdDate = rs.getDate("CreatedDate");
            java.sql.Time createdTime = rs.getTime("CreatedTime");
            if (createdDate != null) {
                complaint.setCreatedDate(createdDate.toLocalDate());
            }
            if (createdTime != null) {
                complaint.setCreatedTime(createdTime.toLocalTime());
            }

            // Handle reply information
            complaint.setReplyText(rs.getString("ReplyText"));

            // Handle nullable StaffID
            Object staffIDObj = rs.getObject("StaffID");
            if (staffIDObj != null) {
                complaint.setStaffID((Integer) staffIDObj);
            } else {
                complaint.setStaffID(null);
            }

            // Handle replied date and time
            java.sql.Date repliedDate = rs.getDate("RepliedDate");
            java.sql.Time repliedTime = rs.getTime("RepliedTime");
            if (repliedDate != null) {
                complaint.setRepliedDate(repliedDate.toLocalDate());
            }
            if (repliedTime != null) {
                complaint.setRepliedTime(repliedTime.toLocalTime());
            }

            return complaint;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}