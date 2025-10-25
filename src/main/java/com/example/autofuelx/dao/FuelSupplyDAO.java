package com.example.autofuelx.dao;


import com.example.autofuelx.model.FuelSupply;
import com.example.autofuelx.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuelSupplyDAO {

    // Insert new record
    public boolean addFuelSupply(FuelSupply fs) {
        String sql = "INSERT INTO FuelSupply (SupplierID, FuelID, Quantity, SupplyDate, SupplyTime) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {


            // Get current date and time
            // Get current date and time
            long now = System.currentTimeMillis();
            Date currentDate = new Date(now);  // java.sql.Date for date column
            Time currentTime = new Time(now);  // java.sql.Time for time column

            ps.setInt(1, fs.getSupplierID());
            ps.setInt(2, fs.getFuelID());
            ps.setDouble(3, fs.getQuantity());
            ps.setDate(4, currentDate);
            ps.setTime(5, currentTime);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve all records
    public List<FuelSupply> getAllFuelSupplies() {
        List<FuelSupply> list = new ArrayList<>();
        String sql = "SELECT * FROM FuelSupply ORDER BY SupplyDate DESC, SupplyTime DESC";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                FuelSupply fs = new FuelSupply();
                fs.setSupplyID(rs.getInt("SupplyID"));
                fs.setSupplierID(rs.getInt("SupplierID"));
                fs.setFuelID(rs.getInt("FuelID"));
                fs.setQuantity(rs.getDouble("Quantity"));
                fs.setSupplyDate(rs.getDate("SupplyDate"));
                fs.setSupplyTime(rs.getTime("SupplyTime"));
                list.add(fs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<FuelSupply> getAllFuelSuppliesBySupplierID(int supplierID) {
        List<FuelSupply> list = new ArrayList<>();
        String sql = "SELECT * FROM FuelSupply WHERE supplierID = ? ORDER BY SupplyDate DESC, SupplyTime DESC ";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, supplierID);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                FuelSupply fs = new FuelSupply();
                fs.setSupplyID(rs.getInt("SupplyID"));
                fs.setSupplierID(rs.getInt("SupplierID"));
                fs.setFuelID(rs.getInt("FuelID"));
                fs.setQuantity(rs.getDouble("Quantity"));
                fs.setSupplyDate(rs.getDate("SupplyDate"));
                fs.setSupplyTime(rs.getTime("SupplyTime"));
                list.add(fs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


}
