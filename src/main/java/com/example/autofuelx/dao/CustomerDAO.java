package com.example.autofuelx.dao;


import com.example.autofuelx.model.Customer;
import com.example.autofuelx.model.Customer;
import com.example.autofuelx.model.Employee;
import com.example.autofuelx.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public boolean registerCustomer(Customer customer) {
        boolean success = false;
        String sql = "INSERT INTO Customer (FirstName, LastName, Email, Password, AddressNo, AddressLane, AddressArea) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getLastName());
            stmt.setString(3, customer.getEmail());     // EMAIL
            stmt.setString(4, customer.getPassword());
            stmt.setString(5, customer.getAddressNo());
            stmt.setString(6, customer.getAddressLane());
            stmt.setString(7, customer.getAddressArea());

            int rows = stmt.executeUpdate();
            success = rows > 0;
            System.out.println("Customer Registration : " + success);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    // Check login by email + password
    public Customer loginCustomer(String email, String password) {
        Customer customer = null;
        String sql = "SELECT * FROM Customer WHERE Email = ? AND Password = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            var rs = stmt.executeQuery();
            if (rs.next()) {
                customer = new Customer();
                customer.setCustomerID(rs.getInt("CustomerID"));
                customer.setFirstName(rs.getString("FirstName"));
                customer.setLastName(rs.getString("LastName"));
                customer.setEmail(rs.getString("Email"));
                customer.setPassword(rs.getString("Password"));
                customer.setAddressNo(rs.getString("AddressNo"));
                customer.setAddressLane(rs.getString("AddressLane"));
                customer.setAddressArea(rs.getString("AddressArea"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM Customer";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Customer customer = extractCustomerFromResultSet(rs);
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    public Customer getCustomerById(int id) {
        String query = "SELECT * FROM Customer WHERE CustomerID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractCustomerFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Customer getCustomerByEmail(String email) {
        String query = "SELECT * FROM Customer WHERE Email = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractCustomerFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteCustomer(int id) {
        String query = "DELETE FROM Customer WHERE CustomerID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean updateCustomer(Customer customer) {
        String query = "UPDATE Customer SET FirstName = ?, LastName = ?, Email = ?, Password = ?, " +
                "AddressNo = ?, AddressLane = ?, AddressArea = ? " +
                "WHERE CustomerID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, customer.getFirstName());
            pstmt.setString(2, customer.getLastName());
            pstmt.setString(3, customer.getEmail());
            pstmt.setString(4, customer.getPassword());
            pstmt.setString(5, customer.getAddressNo());
            pstmt.setString(6, customer.getAddressLane());
            pstmt.setString(7, customer.getAddressArea());
            pstmt.setInt(8, customer.getCustomerID());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Customer extractCustomerFromResultSet(ResultSet rs) {
        try{
            Customer customer = new Customer();
            customer.setCustomerID(rs.getInt("CustomerID"));
            customer.setFirstName(rs.getString("FirstName"));
            customer.setLastName(rs.getString("LastName"));
            customer.setEmail(rs.getString("Email"));
            customer.setPassword(rs.getString("Password"));
            customer.setAddressNo(rs.getString("AddressNo"));
            customer.setAddressLane(rs.getString("AddressLane"));
            customer.setAddressArea(rs.getString("AddressArea"));
            return customer;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
