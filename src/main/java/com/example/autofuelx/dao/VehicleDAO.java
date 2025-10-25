package com.example.autofuelx.dao;


import com.example.autofuelx.dto.VehicleSummaryDTO;
import com.example.autofuelx.model.Vehicle;
import com.example.autofuelx.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {

    // Add Vehicle
    public boolean addVehicle(Vehicle vehicle) {
        boolean success = false;
        String sql = "INSERT INTO Vehicle (PlateNumber, Type, Model, Color, CustomerID, RegistrationDate) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehicle.getPlateNumber());
            stmt.setString(2, vehicle.getType());
            stmt.setString(3, vehicle.getModel());
            stmt.setString(4, vehicle.getColor());
            stmt.setInt(5, vehicle.getCustomerID());
            stmt.setDate(6, new java.sql.Date(vehicle.getRegistrationDate().getTime()));

            success = stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    // Get all vehicles for a customer
    public List<Vehicle> getVehiclesByCustomerID(int customerID) {
        List<Vehicle> list = new ArrayList<>();
        String sql = "SELECT * FROM Vehicle WHERE CustomerID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle = extractVehicleFromResultSet(rs);
                list.add(vehicle);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    // Update Vehicle
    public boolean updateVehicle(Vehicle vehicle) {
        boolean success = false;
        String sql = "UPDATE Vehicle SET PlateNumber=?, Type=?, Model=?, Color=?, RegistrationDate=? WHERE VehicleID=? AND CustomerID=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehicle.getPlateNumber());
            stmt.setString(2, vehicle.getType());
            stmt.setString(3, vehicle.getModel());
            stmt.setString(4, vehicle.getColor());
            stmt.setDate(5, new java.sql.Date(vehicle.getRegistrationDate().getTime()));
            stmt.setInt(6, vehicle.getVehicleID());
            stmt.setInt(7, vehicle.getCustomerID());

            success = stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    // Delete Vehicle
    public boolean deleteVehicle(int vehicleID, int customerID) {
        boolean success = false;
        String sql = "DELETE FROM Vehicle WHERE VehicleID=? AND CustomerID=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, vehicleID);
            stmt.setInt(2, customerID);

            success = stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    // Get vehicle by ID
    public Vehicle getVehicleByID(int vehicleID) {
        Vehicle vehicle = null;
        String sql = "SELECT * FROM Vehicle WHERE VehicleID=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, vehicleID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                vehicle = extractVehicleFromResultSet(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicle;
    }

    public Vehicle getVehicleByPlateNo(String plateNo) {
        Vehicle vehicle = null;
        String sql = "SELECT * FROM Vehicle WHERE PlateNumber=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, plateNo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                vehicle = extractVehicleFromResultSet(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicle;
    }

    public Vehicle getVehicleByPlateNumber(String plateNumber) {
        Vehicle vehicle = null;
        String sql = "SELECT * FROM Vehicle WHERE PlateNumber = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, plateNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                vehicle = extractVehicleFromResultSet(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicle;
    }

    public List<String> getVehicleTypesByCustomerID(int customerID) {
        List<String> vehicleTypes = new ArrayList<>();
        String sql = "SELECT DISTINCT  Type FROM Vehicle WHERE CustomerID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                vehicleTypes.add(rs.getString("Type"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicleTypes;
    }

    public List<String> getVehicleNamesByCustomerID(int customerID) {
        List<String> vehicleNames = new ArrayList<>();
        String sql = "SELECT DISTINCT  Model + ' - ' + Vehicle.PlateNumber as Vehicle FROM Vehicle WHERE CustomerID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                vehicleNames.add(rs.getString("Vehicle"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicleNames;
    }

    public List<VehicleSummaryDTO> getVehicleSummaryByCustomerID(int customerID) {
        List<VehicleSummaryDTO> summaryList = new ArrayList<>();

        // Query to get the count of vehicles for each type for a specific customer
        String sql = """
                SELECT 
                    Type,
                    COUNT(*) AS VehicleCount
                FROM Vehicle
                WHERE CustomerID = ?
                GROUP BY Type
                ORDER BY VehicleCount DESC
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerID);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Create a new DTO for each vehicle type found
                    VehicleSummaryDTO summary = new VehicleSummaryDTO();

                    // Set the vehicle type and its count from the result set
                    summary.setVehicleType(rs.getString("Type"));
                    summary.setVehicleCount(rs.getInt("VehicleCount"));

                    // Add the populated DTO to our list
                    summaryList.add(summary);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // In case of an error, you might want to return an empty list or null
            // Returning an empty list is often safer to prevent NullPointerExceptions
        }

        return summaryList;
    }

    private Vehicle extractVehicleFromResultSet(ResultSet rs) {

        try {
            Vehicle vehicle = new Vehicle();

            vehicle.setVehicleID(rs.getInt("VehicleID"));
            vehicle.setPlateNumber(rs.getString("PlateNumber"));
            vehicle.setType(rs.getString("Type"));
            vehicle.setModel(rs.getString("Model"));
            vehicle.setColor(rs.getString("Color"));
            vehicle.setCustomerID(rs.getInt("CustomerID"));
            vehicle.setRegistrationDate(rs.getDate("RegistrationDate"));
            return vehicle;
        } catch (Exception e) {
            return null;
        }
    }
}
