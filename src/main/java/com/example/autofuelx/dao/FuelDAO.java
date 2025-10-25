package com.example.autofuelx.dao;

import com.example.autofuelx.model.Fuel;
import com.example.autofuelx.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuelDAO {

    // Create (Insert new fuel record)
    public boolean addFuel(Fuel fuel) {
        String sql = "INSERT INTO Fuel (Type, Quantity, CostPerLiter) VALUES (?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, fuel.getType());
            ps.setDouble(2, fuel.getQuantity());
            ps.setDouble(3, fuel.getCostPerLiter());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Read (Fetch fuel by ID)
    public Fuel getFuelById(int id) {
        String sql = "SELECT * FROM Fuel WHERE FuelID = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Fuel(
                        rs.getInt("FuelID"),
                        rs.getString("Type"),
                        rs.getDouble("Quantity"),
                        rs.getDouble("CostPerLiter")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Fuel getFuelByType(String fuelType) {
        Fuel fuel = null;
        String sql = "SELECT * FROM Fuel WHERE Type = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, fuelType);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                fuel = extractFuelFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fuel;
    }

    // Read (Fetch all fuels)
    public List<Fuel> getAllFuels() {
        List<Fuel> fuels = new ArrayList<>();
        String sql = "SELECT * FROM Fuel";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Fuel fuel = extractFuelFromResultSet(rs);
                fuels.add(fuel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fuels;
    }

    // Update (Modify fuel details)
    public boolean updateFuel(Fuel fuel) {
        String sql = "UPDATE Fuel SET Type=?, Quantity=?, CostPerLiter=? WHERE FuelID=?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, fuel.getType());
            ps.setDouble(2, fuel.getQuantity());
            ps.setDouble(3, fuel.getCostPerLiter());
            ps.setInt(4, fuel.getFuelID());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete (Remove fuel record)
    public boolean deleteFuel(int id) {
        String sql = "DELETE FROM Fuel WHERE FuelID=?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<String> getFuelTypes() {
        List<String> fuelTypes = new ArrayList<>();
        String sql = "SELECT Type FROM Fuel";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                fuelTypes.add(rs.getString("Type"));
            }
            return fuelTypes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Fuel extractFuelFromResultSet(ResultSet rs) {
        try {
            Fuel fuel = new Fuel();
            fuel.setFuelID(rs.getInt("FuelID"));
            fuel.setType(rs.getString("Type"));
            fuel.setQuantity(rs.getDouble("Quantity"));
            fuel.setCostPerLiter(rs.getDouble("CostPerLiter"));
            return fuel;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
