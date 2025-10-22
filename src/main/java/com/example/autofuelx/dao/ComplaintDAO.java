package com.example.autofuelx.dao;

import com.example.autofuelx.dto.ComplaintReplyDTO;
import com.example.autofuelx.dto.ComplaintSummaryDTO;
import com.example.autofuelx.model.Complaint;
import com.example.autofuelx.model.ReplyComplaint;
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

    public boolean updateComplaintStatus(int ComplaintID, String status) {
        String sql = "UPDATE Complaint SET Status = ? WHERE ComplaintID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, ComplaintID);

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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

    public List<ComplaintReplyDTO> getComplaintReplyDTOsByStatus(String Status) {
        String sql = "SELECT c.title AS complaintTitle, c.description AS complaintDescription, c.status, " +
                "c.createdDate, c.createdTime, c.updatedDate, c.updateTime, c.ComplaintID, " +
                "e.FirstName + ' ' + e.LastName AS repliedEmployeeName, e.Type AS repliedEmployeeType, " +
                "rc.ReplyComplaintID , rc.Status AS replyStatus ,rc.title AS replyTitle,  rc.description AS replyDescription, " +
                "rc.createdDate AS replyCreatedDate, rc.createdTime AS replyCreatedTime, " +
                "rc.updatedDate AS replyUpdatedDate, rc.updateTime AS replyUpdateTime " +
                "FROM Complaint c " +
                "LEFT JOIN ReplyComplaint rc ON c.complaintID = rc.complaintID " +
                "LEFT JOIN Employee e ON rc.staffID = e.EmployeeID " +
                "WHERE c.Status = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, Status);

            List<ComplaintReplyDTO> complaintReplyDTOs = new ArrayList<>();
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ComplaintReplyDTO complaintReplyDTO = new ComplaintReplyDTO();

                    complaintReplyDTO = extractComplaintDetailsFromResultSet(rs);

                    complaintReplyDTOs.add(complaintReplyDTO);
                }
                return complaintReplyDTOs;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return empty list instead of null
    }


    public List<ComplaintReplyDTO> getComplaintReplyDTOsFiltered(String keyword,
                                                                 String lastUpdateDate,
                                                                 String customerEmail,
                                                                 String status) {
        List<ComplaintReplyDTO> complaintReplyDTOs = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();

        String sql = """
            SELECT c.title AS complaintTitle, 
                   c.description AS complaintDescription, 
                   c.status, 
                   c.createdDate, 
                   c.createdTime, 
                   c.updatedDate, 
                   c.updateTime, 
                   c.ComplaintID, 
                   cust.Email AS customerEmail,
                   e.FirstName + ' ' + e.LastName AS repliedEmployeeName, 
                   e.Type AS repliedEmployeeType, 
                   rc.ReplyComplaintID, 
                   rc.Status AS replyStatus, 
                   rc.title AS replyTitle,  
                   rc.description AS replyDescription, 
                   rc.createdDate AS replyCreatedDate, 
                   rc.createdTime AS replyCreatedTime, 
                   rc.updatedDate AS replyUpdatedDate, 
                   rc.updateTime AS replyUpdateTime 
            FROM Complaint c
            JOIN Customer cust ON c.CustomerID = cust.CustomerID
            LEFT JOIN ReplyComplaint rc ON c.complaintID = rc.complaintID 
            LEFT JOIN Employee e ON rc.staffID = e.EmployeeID 
            WHERE 1=1
            """;

        // --- Keyword search in Complaint Title + Description ---
        if (keyword != null && !keyword.trim().isEmpty()) {
            sql += " AND (c.title LIKE ? OR c.description LIKE ?)";
            String likeKeyword = "%" + keyword.trim() + "%";
            parameters.add(likeKeyword);
            parameters.add(likeKeyword);
        }

        // --- Last Update Date filter (complaint only) ---
        if (lastUpdateDate != null && !lastUpdateDate.isEmpty()) {
            sql += " AND c.updatedDate >= ?";
            parameters.add(Date.valueOf(lastUpdateDate));
        }

        // --- Customer Email filter ---
        if (customerEmail != null && !customerEmail.trim().isEmpty()) {
            sql += " AND cust.Email = ?";
            parameters.add(customerEmail.trim());
        }

        // --- Complaint Status filter ---
        if (status != null && !status.trim().isEmpty() && !"all".equalsIgnoreCase(status)) {
            sql += " AND c.Status = ?";
            if (status.equalsIgnoreCase("Open")) parameters.add("Sent");
            else parameters.add("Seen");
        }

        sql += " ORDER BY c.updatedDate DESC, c.updateTime DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set all dynamic parameters
            for (int i = 0; i < parameters.size(); i++) {
                Object param = parameters.get(i);
                if (param instanceof String) {
                    stmt.setString(i + 1, (String) param);
                } else if (param instanceof Integer) {
                    stmt.setInt(i + 1, (Integer) param);
                } else if (param instanceof Date) {
                    stmt.setDate(i + 1, (Date) param);
                }
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ComplaintReplyDTO complaintReplyDTO = new ComplaintReplyDTO();

                    // Complaint details
                    complaintReplyDTO = extractComplaintDetailsFromResultSet(rs);

                    complaintReplyDTOs.add(complaintReplyDTO);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return complaintReplyDTOs;
    }


    public ComplaintReplyDTO getComplaintReplyDTOByComplaintID(int ComplaintID) {
        String sql = "SELECT c.title AS complaintTitle, c.description AS complaintDescription, c.status, " +
                "c.createdDate, c.createdTime, c.updatedDate, c.updateTime, c.ComplaintID, " +
                "e.FirstName + ' ' + e.LastName AS repliedEmployeeName, e.Type AS repliedEmployeeType, " +
                "rc.ReplyComplaintID , rc.Status AS replyStatus ,rc.title AS replyTitle,  rc.description AS replyDescription, " +
                "rc.createdDate AS replyCreatedDate, rc.createdTime AS replyCreatedTime, " +
                "rc.updatedDate AS replyUpdatedDate, rc.updateTime AS replyUpdateTime " +
                "FROM Complaint c " +
                "LEFT JOIN ReplyComplaint rc ON c.complaintID = rc.complaintID " +
                "LEFT JOIN Employee e ON rc.staffID = e.EmployeeID " +
                "WHERE c.ComplaintID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ComplaintID);

            ComplaintReplyDTO complaintReplyDTO = new ComplaintReplyDTO();
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Complaint details
                    complaintReplyDTO = extractComplaintDetailsFromResultSet(rs);
                }
                return complaintReplyDTO;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ComplaintReplyDTO> getComplaintReplyDTOsByCustomerID(int customerId) {
        String sql = "SELECT c.title AS complaintTitle, c.description AS complaintDescription, c.status, " +
                "c.createdDate, c.createdTime, c.updatedDate, c.updateTime, c.ComplaintID, " +
                "e.FirstName + ' ' + e.LastName AS repliedEmployeeName, e.Type AS repliedEmployeeType, " +
                "rc.ReplyComplaintID , rc.Status AS replyStatus ,rc.title AS replyTitle,  rc.description AS replyDescription, " +
                "rc.createdDate AS replyCreatedDate, rc.createdTime AS replyCreatedTime, " +
                "rc.updatedDate AS replyUpdatedDate, rc.updateTime AS replyUpdateTime " +
                "FROM Complaint c " +
                "LEFT JOIN ReplyComplaint rc ON c.complaintID = rc.complaintID " +
                "LEFT JOIN Employee e ON rc.staffID = e.EmployeeID " +
                "WHERE c.customerID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, customerId);

            List<ComplaintReplyDTO> complaintReplyDTOs = new ArrayList<>();
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ComplaintReplyDTO complaintReplyDTO = new ComplaintReplyDTO();

                    complaintReplyDTO = extractComplaintDetailsFromResultSet(rs);


                    complaintReplyDTOs.add(complaintReplyDTO);
                }
                return complaintReplyDTOs;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return empty list instead of null
    }

    public List<ComplaintReplyDTO> getComplaintReplyDTOsByCustomerID(int customerId, String status) {
        String sql = "SELECT c.title AS complaintTitle, c.description AS complaintDescription, c.status, " +
                "c.createdDate, c.createdTime, c.updatedDate, c.updateTime, c.ComplaintID, " +
                "e.FirstName + ' ' + e.LastName AS repliedEmployeeName, e.Type AS repliedEmployeeType, " +
                "rc.ReplyComplaintID , rc.Status AS replyStatus ,rc.title AS replyTitle,  rc.description AS replyDescription, " +
                "rc.createdDate AS replyCreatedDate, rc.createdTime AS replyCreatedTime, " +
                "rc.updatedDate AS replyUpdatedDate, rc.updateTime AS replyUpdateTime " +
                "FROM Complaint c " +
                "LEFT JOIN ReplyComplaint rc ON c.complaintID = rc.complaintID " +
                "LEFT JOIN Employee e ON rc.staffID = e.EmployeeID " +
                "WHERE c.customerID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             ) {

            if (status!=null || !status.equalsIgnoreCase("all")){
                if (status.equalsIgnoreCase("Open")){
                    sql += " AND NOT(rc.status = 'Seen') OR NOT(c.Status = 'Seen')";
                }
                else {
                    sql += " AND (rc.status = 'Seen') AND (c.Status = 'Seen')";
                }
            }

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, customerId);

            List<ComplaintReplyDTO> complaintReplyDTOs = new ArrayList<>();
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ComplaintReplyDTO complaintReplyDTO = new ComplaintReplyDTO();

                    complaintReplyDTO = extractComplaintDetailsFromResultSet(rs);


                    complaintReplyDTOs.add(complaintReplyDTO);
                }
                return complaintReplyDTOs;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return empty list instead of null
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

    public ComplaintSummaryDTO getComplaintSummaryByCustomerId(int customerId) {
        ComplaintSummaryDTO summary = new ComplaintSummaryDTO();

        try (Connection conn = DatabaseConnection.getConnection()) {

            // 1️ Total complaints
            String totalQuery = "SELECT COUNT(*) FROM Complaint WHERE CustomerID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(totalQuery)) {
                stmt.setInt(1, customerId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        summary.setTotalComplaints(rs.getInt(1));
                    }
                }
            }

            // 2 Active complaints
            String activeQuery = """
                SELECT COUNT(*) 
                FROM Complaint c
                LEFT JOIN ReplyComplaint rc ON c.ComplaintID = rc.ComplaintID
                WHERE c.CustomerID = ?
                  AND (rc.ReplyComplaintID IS NULL)
                """;

            try (PreparedStatement stmt = conn.prepareStatement(activeQuery)) {
                stmt.setInt(1, customerId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        summary.setActiveComplaints(rs.getInt(1));
                    }
                }
            }

            // New received complaints
            String newReceivedQuery = """
            SELECT COUNT(*) 
            FROM Complaint c
            INNER JOIN ReplyComplaint rc ON c.ComplaintID = rc.ComplaintID
            WHERE c.CustomerID = ?
              AND rc.Status = 'Sent'
        """;
            try (PreparedStatement stmt = conn.prepareStatement(newReceivedQuery)) {
                stmt.setInt(1, customerId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        summary.setUnreadComplaints(rs.getInt(1));
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return summary;
    }


    private ComplaintReplyDTO extractComplaintDetailsFromResultSet(ResultSet rs){
        ComplaintReplyDTO complaintReplyDTO;
        try{
            complaintReplyDTO = new ComplaintReplyDTO();

            // Complaint details
            complaintReplyDTO.setComplaintID(rs.getInt("ComplaintID"));
            complaintReplyDTO.setTitle(rs.getString("complaintTitle"));
            complaintReplyDTO.setDescription(rs.getString("complaintDescription"));
            complaintReplyDTO.setStatus(rs.getString("status"));
            complaintReplyDTO.setCreatedDate(rs.getDate("createdDate"));
            complaintReplyDTO.setCreatedTime(rs.getTime("createdTime"));
            complaintReplyDTO.setUpdatedDate(rs.getDate("updatedDate"));
            complaintReplyDTO.setUpdateTime(rs.getTime("updateTime"));

            // Reply details
            complaintReplyDTO.setReplyComplaintID((Integer) rs.getObject("ReplyComplaintID"));
            complaintReplyDTO.setRepliedEmployeeName(rs.getString("repliedEmployeeName"));
            complaintReplyDTO.setRepliedEmployeeType(rs.getString("repliedEmployeeType"));
            complaintReplyDTO.setReplyTitle(rs.getString("replyTitle"));
            complaintReplyDTO.setReplyDescription(rs.getString("replyDescription"));
            complaintReplyDTO.setReplyStatus(rs.getString("replyStatus"));
            complaintReplyDTO.setReplyCreatedDate(rs.getDate("replyCreatedDate"));
            complaintReplyDTO.setReplyCreatedTime(rs.getTime("replyCreatedTime"));
            complaintReplyDTO.setReplyUpdatedDate(rs.getDate("replyUpdatedDate"));
            complaintReplyDTO.setReplyUpdateTime(rs.getTime("replyUpdateTime"));
            return complaintReplyDTO;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
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
            return complaint;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}