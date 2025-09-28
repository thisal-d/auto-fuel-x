package com.example.autofuelx.dao;

import com.example.autofuelx.dto.FuelPurchaseDetailDTO;
import com.example.autofuelx.model.FuelPurchase;
import com.example.autofuelx.util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FuelPurchaseDAO {

    public boolean insertFuelPurchase(FuelPurchase purchase) {
        // Insert purchase record
        String sql = "INSERT INTO FuelPurchase (CustomerID, FuelID, VehicleID, Quantity, TotalCost, PurchaseDate, PurchaseTime) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, purchase.getCustomerID());
            ps.setInt(2, purchase.getFuelID());
            ps.setInt(3, purchase.getVehicleID());
            ps.setDouble(4, purchase.getQuantity());
            ps.setDouble(5, purchase.getTotalCost());

            // Set current date and time
            LocalDate currentDate = LocalDate.now();
            LocalTime currentTime = LocalTime.now();
            ps.setDate(6, Date.valueOf(currentDate));
            ps.setTime(7, Time.valueOf(currentTime));

            return ps.executeUpdate() > 0;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public List<FuelPurchaseDetailDTO> getFuelPurchaseDetailByCustomer(int customerID) {
        List<FuelPurchaseDetailDTO> purchases = new ArrayList<>();

        String sql = """
            SELECT fp.FuelPurchaseID, 
                   c.FirstName + ' ' + c.LastName AS CustomerName,
                   c.Email,
                   v.PlateNumber,
                   v.Model,
                   f.Type AS FuelType,
                   fp.Quantity,
                   fp.TotalCost,
                   fp.PurchaseDate,
                   fp.PurchaseTime
            FROM FuelPurchase fp
            JOIN Customer c ON fp.CustomerID = c.CustomerID
            JOIN Vehicle v ON fp.VehicleID = v.VehicleID
            JOIN Fuel f ON fp.FuelID = f.FuelID
            WHERE fp.CustomerID = ?
            ORDER BY fp.PurchaseDate DESC, fp.PurchaseTime DESC
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                purchases.add(extractFuelPurchaseDetailFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return purchases;
    }

    public List<FuelPurchaseDetailDTO> getFuelPurchaseDetailByCustomer(int customerID,
                                                                       String durationFilterDateStart,
                                                                       String durationFilterDateEnd,
                                                                       String vehicleTypeFilter,
                                                                       String vehicleFilter,
                                                                       String fuelTypeFilter) {

        List<FuelPurchaseDetailDTO> purchases = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();
        StringBuilder sql = new StringBuilder("""
                SELECT fp.FuelPurchaseID, 
                       c.FirstName + ' ' + c.LastName AS CustomerName,
                       c.Email,
                       v.PlateNumber,
                       v.Model,
                       f.Type AS FuelType,
                       fp.Quantity,
                       fp.TotalCost,
                       fp.PurchaseDate,
                       fp.PurchaseTime
                FROM FuelPurchase fp
                JOIN Customer c ON fp.CustomerID = c.CustomerID
                JOIN Vehicle v ON fp.VehicleID = v.VehicleID
                JOIN Fuel f ON fp.FuelID = f.FuelID
                WHERE fp.CustomerID = ?
                """);

        parameters.add(customerID);

        // Add date range filter - simplified for date only
        if (durationFilterDateStart != null && !durationFilterDateStart.isEmpty()) {
            sql.append(" AND fp.PurchaseDate >= ?");
            LocalDate startDate = LocalDate.parse(durationFilterDateStart);
            parameters.add(startDate);
        }
        if (durationFilterDateEnd != null && !durationFilterDateEnd.isEmpty()) {
            sql.append(" AND fp.PurchaseDate <= ?");
            LocalDate endDate = LocalDate.parse(durationFilterDateEnd);
            parameters.add(endDate);
        }

        // Add vehicle type filter
        if (vehicleTypeFilter != null && !vehicleTypeFilter.isEmpty() && !vehicleTypeFilter.equals("all")) {
            sql.append(" AND v.Type = ?");
            parameters.add(vehicleTypeFilter);
        }

        System.out.println("vehicleTypeFilter :"+ vehicleTypeFilter);

        // Add vehicle filter
        if (vehicleFilter != null && !vehicleFilter.isEmpty() && !vehicleFilter.equals("all")) {
            sql.append(" AND v.VehicleID = ?");
            parameters.add(Integer.parseInt(vehicleFilter));
        }

        // Add fuel type filter
        if (fuelTypeFilter != null && !fuelTypeFilter.isEmpty() && !fuelTypeFilter.equals("all")) {
            sql.append(" AND f.FuelID = ?");
            parameters.add(Integer.parseInt(fuelTypeFilter));
        }

        sql.append(" ORDER BY fp.PurchaseDate DESC, fp.PurchaseTime DESC");

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            // Set all parameters dynamically
            for (int i = 0; i < parameters.size(); i++) {
                Object param = parameters.get(i);
                if (param instanceof String) {
                    stmt.setString(i+1 , (String) param);
                } else if (param instanceof Integer) {
                    stmt.setInt(i+1, (Integer) param);
                } else if (param instanceof LocalDate) {
                    stmt.setDate(i+1, Date.valueOf((LocalDate) param));
                }
            }

            System.out.println(stmt.getMetaData());

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                purchases.add(extractFuelPurchaseDetailFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return purchases;
    }

    public FuelPurchaseDetailDTO extractFuelPurchaseDetailFromResultSet(ResultSet rs) {
        FuelPurchaseDetailDTO fuelPurchaseDetailDTO;
        try {
            fuelPurchaseDetailDTO = new FuelPurchaseDetailDTO();

            // Set basic properties
            fuelPurchaseDetailDTO.setFuelPurchaseID(rs.getInt("FuelPurchaseID"));
            fuelPurchaseDetailDTO.setFuelType(rs.getString("FuelType"));
            fuelPurchaseDetailDTO.setQuantity(rs.getDouble("Quantity"));
            fuelPurchaseDetailDTO.setTotalCost(rs.getDouble("TotalCost"));

            // Set customer information
            fuelPurchaseDetailDTO.setCustomerName(rs.getString("CustomerName"));
            fuelPurchaseDetailDTO.setCustomerEmail(rs.getString("Email"));

            // Set vehicle information
            fuelPurchaseDetailDTO.setVehiclePlate(rs.getString("PlateNumber"));
            fuelPurchaseDetailDTO.setVehicleModel(rs.getString("Model"));

            // Set purchase date and time
            java.sql.Date purchaseDate = rs.getDate("PurchaseDate");
            java.sql.Time purchaseTime = rs.getTime("PurchaseTime");
            if (purchaseDate != null) {
                fuelPurchaseDetailDTO.setPurchaseDate(purchaseDate.toLocalDate());
            }
            if (purchaseTime != null) {
                fuelPurchaseDetailDTO.setPurchaseTime(purchaseTime.toLocalTime());
            }

            return fuelPurchaseDetailDTO;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}