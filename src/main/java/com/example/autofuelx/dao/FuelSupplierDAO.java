package com.example.autofuelx.dao;

import com.example.autofuelx.model.FuelSupplier;
import com.example.autofuelx.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuelSupplierDAO {

    public boolean addSupplier(FuelSupplier supplier) {
        String sql = "INSERT INTO FuelSupplier (Name, PhoneNumber) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getPhoneNumber());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<FuelSupplier> getAllSuppliers() {
        List<FuelSupplier> suppliers = new ArrayList<>();
        String sql = "SELECT SupplierID, Name, PhoneNumber FROM FuelSupplier ORDER BY Name";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                FuelSupplier supplier = new FuelSupplier();
                supplier.setSupplierID(rs.getInt("SupplierID"));
                supplier.setName(rs.getString("Name"));
                supplier.setPhoneNumber(rs.getString("PhoneNumber"));
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliers;
    }

    // --- READ (Get by ID) - Helper for Update/Delete ---
    public FuelSupplier getSupplierById(int supplierId) {
        String sql = "SELECT SupplierID, Name, PhoneNumber FROM FuelSupplier WHERE SupplierID = ?";
        FuelSupplier supplier = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, supplierId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    supplier = new FuelSupplier();
                    supplier.setSupplierID(rs.getInt("SupplierID"));
                    supplier.setName(rs.getString("Name"));
                    supplier.setPhoneNumber(rs.getString("PhoneNumber"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplier;
    }

    // --- UPDATE ---
    public boolean updateSupplier(FuelSupplier supplier) {
        String sql = "UPDATE FuelSupplier SET Name = ?, PhoneNumber = ? WHERE SupplierID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getPhoneNumber());
            stmt.setInt(3, supplier.getSupplierID());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // --- DELETE ---
    public boolean deleteSupplier(int supplierId) {
        String sql = "DELETE FROM FuelSupplier WHERE SupplierID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, supplierId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}