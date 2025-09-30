package com.example.autofuelx.dao;

import com.example.autofuelx.model.EmployeePhoneNumber;
import com.example.autofuelx.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeePhoneNumberDAO {

    // Add new phone number
    public boolean addPhoneNumber(EmployeePhoneNumber phone) {
        String sql = "INSERT INTO EmployeePhoneNumber (EmployeeID, PhoneNumber) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, phone.getEmployeeID());
            stmt.setString(2, phone.getPhoneNumber());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete a phone number
    public boolean deletePhoneNumber(int employeeID, String phoneNumber) {
        String sql = "DELETE FROM EmployeePhoneNumber WHERE EmployeeID = ? AND PhoneNumber = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeID);
            stmt.setString(2, phoneNumber);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get all phone numbers for a employee
    public List<String> getPhoneNumbersByEmployee(int employeeID) {
        List<String> numbers = new ArrayList<>();
        String sql = "SELECT PhoneNumber FROM EmployeePhoneNumber WHERE EmployeeID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeID);
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
