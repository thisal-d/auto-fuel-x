package com.example.autofuelx.dao;
 

import com.example.autofuelx.model.Service;
import com.example.autofuelx.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAO {

    // Insert new Service
    public boolean insertService(Service service) {
        String sql = "INSERT INTO Service (Type, Cost) VALUES (?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, service.getType());
            ps.setDouble(2, service.getCost());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update Service by ID
    public boolean updateService(Service service) {
        String sql = "UPDATE Service SET Type = ?, Cost = ? WHERE ServiceID = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, service.getType());
            ps.setDouble(2, service.getCost());
            ps.setInt(3, service.getServiceID());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete Service by ID
    public boolean deleteService(int serviceID) {
        String sql = "DELETE FROM Service WHERE ServiceID = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, serviceID);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get Service by ID
    public Service getServiceById(int serviceID) {
        String sql = "SELECT * FROM Service WHERE ServiceID = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, serviceID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return extractServiceFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get all Services
    public List<Service> getAllServices() {
        List<Service> services = new ArrayList<>();
        String sql = "SELECT * FROM Service";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                services.add(extractServiceFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return services;
    }

    // Helper method to map ResultSet -> Service object
    private Service extractServiceFromResultSet(ResultSet rs) throws SQLException {
        Service service = new Service();
        service.setServiceID(rs.getInt("ServiceID"));
        service.setType(rs.getString("Type"));
        service.setCost(rs.getDouble("Cost"));
        return service;
    }
}
