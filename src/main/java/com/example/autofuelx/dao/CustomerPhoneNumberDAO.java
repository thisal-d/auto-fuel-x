package com.example.autofuelx.dao;

import com.example.autofuelx.model.CustomerPhoneNumber;
import com.example.autofuelx.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerPhoneNumberDAO {

    // Add new phone number
    public boolean addPhoneNumber(CustomerPhoneNumber phone) {
        String sql = "INSERT INTO CustomerPhoneNumber (CustomerID, PhoneNumber) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, phone.getCustomerID());
            stmt.setString(2, phone.getPhoneNumber());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete a phone number
    public boolean deletePhoneNumber(int customerID, String phoneNumber) {
        String sql = "DELETE FROM CustomerPhoneNumber WHERE CustomerID = ? AND PhoneNumber = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerID);
            stmt.setString(2, phoneNumber);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get all phone numbers for a customer
    public List<String> getPhoneNumbersByCustomer(int customerID) {
        List<String> numbers = new ArrayList<>();
        String sql = "SELECT PhoneNumber FROM CustomerPhoneNumber WHERE CustomerID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                numbers.add(rs.getString("PhoneNumber"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numbers;
    }
}
