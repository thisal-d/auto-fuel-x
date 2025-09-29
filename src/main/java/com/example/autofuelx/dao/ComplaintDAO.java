package com.example.autofuelx.dao;

import com.example.autofuelx.model.Complaint;
import com.example.autofuelx.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComplaintDAO {

    // Add a new complaint
    public void addComplaint(Complaint complaint) {
        String sql = "INSERT INTO Complaint (customerID, title, description, status, createdDate, createdTime) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, complaint.getCustomerID());
            stmt.setString(2, complaint.getTitle());
            stmt.setString(3, complaint.getDescription());
            stmt.setString(4, complaint.getStatus());

            // Set current date and time
            java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
            java.sql.Time currentTime = new java.sql.Time(System.currentTimeMillis());
            stmt.setDate(5, currentDate);
            stmt.setTime(6, currentTime);

            stmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    // Get complaint by ID
    public Complaint getComplaintById(int complaintID) {
        String sql = "SELECT * FROM Complaint WHERE complaintID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, complaintID);

            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                return extractComplaintFromResultSet(rs);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // Get all Complaint
    public List<Complaint> getAllComplaints() {
        List<Complaint> Complaint = new ArrayList<>();
        String sql = "SELECT * FROM Complaint";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Complaint.add(extractComplaintFromResultSet(rs));
            }

            return Complaint;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // Update complaint
    public boolean updateComplaint(Complaint complaint) {
        String sql = "UPDATE Complaint SET customerID = ?, title = ?, description = ?, status = ?, " +
                "updatedDate = ?, updateTime = ? WHERE complaintID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, complaint.getCustomerID());
            stmt.setString(2, complaint.getTitle());
            stmt.setString(3, complaint.getDescription());
            stmt.setString(4, complaint.getStatus());

            java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
            java.sql.Time currentTime = new java.sql.Time(System.currentTimeMillis());

            stmt.setDate(5, currentDate);
            stmt.setTime(6, currentTime);
            
            stmt.setInt(7, complaint.getComplaintID());

            return stmt.executeUpdate() > 0;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    // Delete complaint
    public boolean deleteComplaint(int complaintID) {
        String sql = "DELETE FROM Complaint WHERE complaintID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, complaintID);
            return stmt.executeUpdate() > 0;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    // Get Complaint by customer ID
    public List<Complaint> getComplaintsByCustomerId(int customerID) {
        List<Complaint> Complaint = new ArrayList<>();
        String sql = "SELECT * FROM Complaint WHERE customerID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerID);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Complaint.add(extractComplaintFromResultSet(rs));
            }
            return Complaint;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // Helper method to extract complaint from ResultSet
    private Complaint extractComplaintFromResultSet(ResultSet rs) {
        try{
            Complaint complaint = new Complaint();
            complaint.setComplaintID(rs.getInt("complaintID"));
            complaint.setCustomerID(rs.getInt("customerID"));
            complaint.setTitle(rs.getString("title"));
            complaint.setDescription(rs.getString("description"));
            complaint.setStatus(rs.getString("status"));
            complaint.setCreatedDate(rs.getDate("createdDate"));
            complaint.setCreatedTime(rs.getTime("createdTime"));
            complaint.setUpdatedDate(rs.getDate("updatedDate"));
            complaint.setUpdateTime(rs.getTime("updateTime"));
        return complaint;}
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}