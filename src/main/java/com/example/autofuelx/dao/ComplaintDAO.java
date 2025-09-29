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

    /*
    public ComplaintDTO getComplaintDTOByCustomerID(int customerId) {
        String sql = "SELECT c.title AS complaintTitle, c.description AS complaintDescription, c.status, " +
                "c.createdDate, c.createdTime, c.updatedDate, c.updateTime, " +
                "e.FirstName + ' ' + e.LastName AS repliedEmployeeName, e.Type AS repliedEmployeeType, " +
                "rc.title AS replyTitle, rc.description AS replyDescription, " +
                "rc.createdDate AS replyCreatedDate, rc.createdTime AS replyCreatedTime, " +
                "rc.updatedDate AS replyUpdatedDate, rc.updateTime AS replyUpdateTime " +
                "FROM Complaint c " +
                "LEFT JOIN ReplyComplaint rc ON c.complaintID = rc.complaintID " +
                "LEFT JOIN Employee e ON rc.staffID = e.EmployeeID " +
                "WHERE c.customerID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, customerId);
            
            ComplaintDTO complaintDTO = new ComplaintDTO();
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    // Complaint details
                    complaintDTO.setTitle(rs.getString("complaintTitle"));
                    complaintDTO.setDescription(rs.getString("complaintDescription"));
                    complaintDTO.setStatus(rs.getString("status"));
                    complaintDTO.setCreatedDate(rs.getDate("createdDate"));
                    complaintDTO.setCreatedTime(rs.getTime("createdTime"));
                    complaintDTO.setUpdatedDate(rs.getDate("updatedDate"));
                    complaintDTO.setUpdateTime(rs.getTime("updateTime"));

                    // Reply details
                    complaintDTO.setRepliedEmployeeName(rs.getString("repliedEmployeeName"));
                    complaintDTO.setRepliedEmployeeType(rs.getString("repliedEmployeeType"));
                    complaintDTO.setReplyTitle(rs.getString("replyTitle"));
                    complaintDTO.setReplyDescription(rs.getString("replyDescription"));
                    complaintDTO.setReplyCreatedDate(rs.getDate("replyCreatedDate"));
                    complaintDTO.setReplyCreatedTime(rs.getTime("replyCreatedTime"));
                    complaintDTO.setReplyUpdatedDate(rs.getDate("replyUpdatedDate"));
                    complaintDTO.setReplyUpdateTime(rs.getTime("replyUpdateTime"));
                }
                return complaintDTO;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    */

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