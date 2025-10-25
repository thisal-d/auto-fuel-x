package com.example.autofuelx.dao;


import com.example.autofuelx.model.Service;
import com.example.autofuelx.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAO {

    // Insert new Service
    public boolean insertService(Service service) {
        String sql = "INSERT INTO Service (Type, Description, Cost) VALUES (?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, service.getType());
            ps.setString(2, service.getDescription());
            ps.setDouble(3, service.getCost());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update Service by ID
    public boolean updateService(Service service) {
        String sql = "UPDATE Service SET Type = ?, Cost = ?, Description = ? WHERE ServiceID = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, service.getType());
            ps.setDouble(2, service.getCost());
            ps.setString(3, service.getDescription());
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

    public List<Service> getFilteredServices(Double minCost, Double maxCost, String keyword) {
        List<Service> services = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM Service WHERE 1=1");
        List<Object> parameters = new ArrayList<>();

        // Add cost filters
        if (minCost != null) {
            sql.append(" AND Cost >= ?");
            parameters.add(minCost);
        }
        if (maxCost != null) {
            sql.append(" AND Cost <= ?");
            parameters.add(maxCost);
        }

        // Add keyword filter (search in Type and Description)
        if (keyword != null && !keyword.isEmpty()) {
            sql.append(" AND (Type LIKE ? OR Description LIKE ?)");
            String keywordPattern = "%" + keyword + "%";
            parameters.add(keywordPattern);
            parameters.add(keywordPattern);
        }

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            // Set parameters
            for (int i = 0; i < parameters.size(); i++) {
                ps.setObject(i + 1, parameters.get(i));
            }

            ResultSet rs = ps.executeQuery();
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
        service.setDescription(rs.getString("Description"));
        service.setType(rs.getString("Type"));
        service.setCost(rs.getDouble("Cost"));
        return service;
    }
}
