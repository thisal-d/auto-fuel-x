package com.example.autofuelx.dao;

import com.example.autofuelx.dto.ServiceBookingDTO;
import com.example.autofuelx.model.ServiceBooking;
import com.example.autofuelx.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.time.LocalDate;


public class ServiceBookingDAO {
    public ServiceBooking getBookingByID(int bookingID) {
        String sql = "SELECT * FROM ServiceBooking WHERE BookingID = ?";
        ServiceBooking booking = null;
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, bookingID);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                booking = extractBookingFromResultSet(rs);
            }
            return booking;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Insert new booking
    public boolean insertBooking(ServiceBooking booking) {
        String sql = "INSERT INTO ServiceBooking (CustomerID, VehicleID, ServiceID, BookingDate, BookingTime, Status) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, booking.getCustomerID());
            ps.setInt(2, booking.getVehicleID());
            ps.setInt(3, booking.getServiceID());

            ps.setDate(4, Date.valueOf(booking.getBookingDate()));
            ps.setTime(5, Time.valueOf(booking.getBookingTime()));
            ps.setString(6, booking.getStatus());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update existing booking
    public boolean updateBooking(ServiceBooking booking) {
        String sql = "UPDATE ServiceBooking SET VehicleID = ?, ServiceID = ?, BookingDate = ?, BookingTime = ?, Status = ?, StaffID = ? WHERE BookingID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, booking.getVehicleID());
            ps.setInt(2, booking.getServiceID());
            ps.setDate(3, Date.valueOf(booking.getBookingDate()));
            ps.setTime(4, Time.valueOf(booking.getBookingTime()));
            ps.setString(5, booking.getStatus());

            // assuming you have StaffID in your model (nullable)
            if (booking.getStaffID() != null) {
                ps.setInt(6, booking.getStaffID());
            } else {
                ps.setNull(6, java.sql.Types.INTEGER);
            }

            ps.setInt(7, booking.getBookingID());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<ServiceBookingDTO> getAllBookings() {
        List<ServiceBookingDTO> bookings = new ArrayList<>();
        String sql = """
            SELECT sb.BookingID,
                   sb.BookingDate,
                   sb.BookingTime,
                   sb.Status,
                   sb.TotalCost,
                   c.FirstName AS CustomerFirstName,
                   c.LastName AS CustomerLastName,
                   c.Email AS CustomerEmail,
                   v.VehicleID,
                   v.PlateNumber,
                   v.Model AS VehicleModel,
                   v.Type AS VehicleType,
                   s.ServiceID,
                   s.Type AS ServiceType,
                   s.Cost AS ServiceCost,
                   e.EmployeeID AS StaffID,
                   e.FirstName AS StaffFirstName,
                   e.LastName AS StaffLastName,
                   e.Role AS StaffRole
            FROM ServiceBooking sb
            JOIN Customer c ON sb.CustomerID = c.CustomerID
            JOIN Vehicle v ON sb.VehicleID = v.VehicleID
            JOIN Service s ON sb.ServiceID = s.ServiceID
            LEFT JOIN Employee e ON sb.StaffID = e.EmployeeID
            ORDER BY sb.BookingDate DESC, sb.BookingTime DESC
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                bookings.add(extractBookingDetailFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;
    }

    public ServiceBookingDTO getBookingDTOByID(int bookingID) {
        String sql = """
        SELECT sb.BookingID,
               sb.BookingDate,
               sb.BookingTime,
               sb.Status,
               sb.TotalCost,
               c.FirstName AS CustomerFirstName,
               c.LastName AS CustomerLastName,
               c.Email AS CustomerEmail,
               v.VehicleID,
               v.PlateNumber,
               v.Model AS VehicleModel,
               v.Type AS VehicleType,
               s.ServiceID,
               s.Type AS ServiceType,
               s.Cost AS ServiceCost,
               e.EmployeeID AS StaffID,
               e.FirstName AS StaffFirstName,
               e.LastName AS StaffLastName,
               e.Role AS StaffRole
        FROM ServiceBooking sb
        JOIN Customer c ON sb.CustomerID = c.CustomerID
        JOIN Vehicle v ON sb.VehicleID = v.VehicleID
        JOIN Service s ON sb.ServiceID = s.ServiceID
        LEFT JOIN Employee e ON sb.StaffID = e.EmployeeID
        WHERE sb.BookingID = ?
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookingID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return extractBookingDetailFromResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<ServiceBookingDTO> getAllBookings(String status) {
        List<ServiceBookingDTO> bookings = new ArrayList<>();
        String sql = """
            SELECT sb.BookingID,
                   sb.BookingDate,
                   sb.BookingTime,
                   sb.Status,
                   sb.TotalCost,
                   c.FirstName AS CustomerFirstName,
                   c.LastName AS CustomerLastName,
                   c.Email AS CustomerEmail,
                   v.VehicleID,
                   v.PlateNumber,
                   v.Model AS VehicleModel,
                   v.Type AS VehicleType,
                   s.ServiceID,
                   s.Type AS ServiceType,
                   s.Cost AS ServiceCost,
                   e.EmployeeID AS StaffID,
                   e.FirstName AS StaffFirstName,
                   e.LastName AS StaffLastName,
                   e.Role AS StaffRole
            FROM ServiceBooking sb
            JOIN Customer c ON sb.CustomerID = c.CustomerID
            JOIN Vehicle v ON sb.VehicleID = v.VehicleID
            JOIN Service s ON sb.ServiceID = s.ServiceID
            LEFT JOIN Employee e ON sb.StaffID = e.EmployeeID
            WHERE sb.Status = ?
            ORDER BY sb.BookingDate DESC, sb.BookingTime DESC
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                bookings.add(extractBookingDetailFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;
    }

    // Get all bookings for a customer
    public List<ServiceBookingDTO> getBookingsByCustomer(int customerID) {
        List<ServiceBookingDTO> bookings = new ArrayList<>();
        String sql = """
            SELECT sb.BookingID,
                   sb.BookingDate,
                   sb.BookingTime,
                   sb.Status,
                   sb.TotalCost,
                   c.FirstName AS CustomerFirstName,
                   c.LastName AS CustomerLastName,
                   c.Email AS CustomerEmail,
                   v.VehicleID,
                   v.PlateNumber,
                   v.Model AS VehicleModel,
                   v.Type AS VehicleType,
                   s.ServiceID,
                   s.Type AS ServiceType,
                   s.Cost AS ServiceCost,
                   e.EmployeeID AS StaffID,
                   e.FirstName AS StaffFirstName,
                   e.LastName AS StaffLastName,
                   e.Role AS StaffRole
            FROM ServiceBooking sb
            JOIN Customer c ON sb.CustomerID = c.CustomerID
            JOIN Vehicle v ON sb.VehicleID = v.VehicleID
            JOIN Service s ON sb.ServiceID = s.ServiceID
            LEFT JOIN Employee e ON sb.StaffID = e.EmployeeID
            WHERE sb.CustomerID = ?
            ORDER BY sb.BookingDate DESC, sb.BookingTime DESC
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                bookings.add(extractBookingDetailFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;
    }

    public List<ServiceBookingDTO> getBookingsByCustomer(int customerID, String status) {
        List<ServiceBookingDTO> bookings = new ArrayList<>();
        String sql = """
            SELECT sb.BookingID,
                   sb.BookingDate,
                   sb.BookingTime,
                   sb.Status,
                   sb.TotalCost,
                   c.FirstName AS CustomerFirstName,
                   c.LastName AS CustomerLastName,
                   c.Email AS CustomerEmail,
                   v.VehicleID,
                   v.PlateNumber,
                   v.Model AS VehicleModel,
                   v.Type AS VehicleType,
                   s.ServiceID,
                   s.Type AS ServiceType,
                   s.Cost AS ServiceCost,
                   e.EmployeeID AS StaffID,
                   e.FirstName AS StaffFirstName,
                   e.LastName AS StaffLastName,
                   e.Role AS StaffRole
            FROM ServiceBooking sb
            JOIN Customer c ON sb.CustomerID = c.CustomerID
            JOIN Vehicle v ON sb.VehicleID = v.VehicleID
            JOIN Service s ON sb.ServiceID = s.ServiceID
            LEFT JOIN Employee e ON sb.StaffID = e.EmployeeID
            WHERE sb.CustomerID = ? AND sb.status = ?
            ORDER BY sb.BookingDate DESC, sb.BookingTime DESC
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerID);
            stmt.setString(2, status);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                bookings.add(extractBookingDetailFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;
    }

    // Filtered bookings retrieval
    public List<ServiceBookingDTO> getBookingsByCustomer(int customerID,
                                                         String startDateStr,
                                                         String endDateStr,
                                                         String vehicleTypeFilter,
                                                         String vehicleFilter,
                                                         String serviceFilter,
                                                         String staffFilter) {

        List<ServiceBookingDTO> bookings = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();
        String sql = """
            SELECT sb.BookingID,
                   sb.BookingDate,
                   sb.BookingTime,
                   sb.Status,
                   sb.TotalCost,
                   c.FirstName AS CustomerFirstName,
                   c.LastName AS CustomerLastName,
                   c.Email AS CustomerEmail,
                   v.VehicleID,
                   v.PlateNumber,
                   v.Model AS VehicleModel,
                   v.Type AS VehicleType,
                   s.ServiceID,
                   s.Type AS ServiceType,
                   s.Cost AS ServiceCost,
                   e.EmployeeID AS StaffID,
                   e.FirstName AS StaffFirstName,
                   e.LastName AS StaffLastName,
                   e.Role AS StaffRole
            FROM ServiceBooking sb
            JOIN Customer c ON sb.CustomerID = c.CustomerID
            JOIN Vehicle v ON sb.VehicleID = v.VehicleID
            JOIN Service s ON sb.ServiceID = s.ServiceID
            LEFT JOIN Employee e ON sb.StaffID = e.EmployeeID
            WHERE sb.CustomerID = ?
            """;
        parameters.add(customerID);

        // Date filter
        if (startDateStr != null && !startDateStr.isEmpty()) {
            sql += " AND sb.BookingDate >= ?";
            parameters.add(LocalDate.parse(startDateStr));
        }
        if (endDateStr != null && !endDateStr.isEmpty()) {
            sql += " AND sb.BookingDate <= ?";
            parameters.add(LocalDate.parse(endDateStr));
        }

        // Vehicle type
        if (vehicleTypeFilter != null && !vehicleTypeFilter.equalsIgnoreCase("all")) {
            sql += " AND v.Type = ?";
            parameters.add(vehicleTypeFilter);
        }

        // Vehicle ID
        if (vehicleFilter != null && !vehicleFilter.equalsIgnoreCase("all")) {
            sql += " AND v.VehicleID = ?";
            parameters.add(Integer.parseInt(vehicleFilter));
        }

        // Service filter
        if (serviceFilter != null && !serviceFilter.equalsIgnoreCase("all")) {
            sql += " AND s.ServiceID = ?";
            parameters.add(Integer.parseInt(serviceFilter));
        }

        // Staff filter
        if (staffFilter != null && !staffFilter.equalsIgnoreCase("all")) {
            sql += " AND e.EmployeeID = ?";
            parameters.add(Integer.parseInt(staffFilter));
        }

        sql += " ORDER BY sb.BookingDate DESC, sb.BookingTime DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < parameters.size(); i++) {
                Object param = parameters.get(i);
                if (param instanceof String) {
                    stmt.setString(i + 1, (String) param);
                } else if (param instanceof Integer) {
                    stmt.setInt(i + 1, (Integer) param);
                } else if (param instanceof LocalDate) {
                    stmt.setDate(i + 1, Date.valueOf((LocalDate) param));
                }
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                bookings.add(extractBookingDetailFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;
    }

    private ServiceBooking extractBookingFromResultSet(ResultSet rs) {
        try {
            ServiceBooking booking = new ServiceBooking();
            booking.setBookingID(rs.getInt("BookingID"));
            booking.setCustomerID(rs.getInt("CustomerID"));
            booking.setVehicleID(rs.getInt("VehicleID"));
            booking.setStatus(rs.getString("Status"));
            booking.setServiceID(rs.getInt("ServiceID"));

            // Handle nullable StaffID
            Object staffIDObj = rs.getObject("StaffID");
            if (staffIDObj != null) {
                booking.setStaffID((Integer) staffIDObj);
            } else {
                booking.setStaffID(null);
            }

            // Handle booking date and time
            java.sql.Date bookingDate = rs.getDate("BookingDate");
            java.sql.Time bookingTime = rs.getTime("BookingTime");
            if (bookingDate != null) {
                booking.setBookingDate(bookingDate.toLocalDate());
            }
            if (bookingTime != null) {
                booking.setBookingTime(bookingTime.toLocalTime());
            }

            return booking;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Map ResultSet to DTO
    private ServiceBookingDTO extractBookingDetailFromResultSet(ResultSet rs) {
        ServiceBookingDTO serviceBookingDTO = null;
        try {
            serviceBookingDTO = new ServiceBookingDTO();

            serviceBookingDTO.setBookingID(rs.getInt("BookingID"));
            serviceBookingDTO.setBookingDate(rs.getDate("BookingDate").toLocalDate());
            serviceBookingDTO.setBookingTime(rs.getTime("BookingTime").toLocalTime());
            serviceBookingDTO.setStatus(rs.getString("Status"));
            serviceBookingDTO.setTotalCost(rs.getDouble("TotalCost"));

            serviceBookingDTO.setCustomerFirstName(rs.getString("CustomerFirstName"));
            serviceBookingDTO.setCustomerLastName(rs.getString("CustomerLastName"));
            serviceBookingDTO.setCustomerEmail(rs.getString("CustomerEmail"));

            serviceBookingDTO.setVehicleID(rs.getInt("VehicleID"));
            serviceBookingDTO.setVehiclePlate(rs.getString("PlateNumber"));
            serviceBookingDTO.setVehicleModel(rs.getString("VehicleModel"));
            serviceBookingDTO.setVehicleType(rs.getString("VehicleType"));

            serviceBookingDTO.setServiceID(rs.getInt("ServiceID"));
            serviceBookingDTO.setServiceType(rs.getString("ServiceType"));
            serviceBookingDTO.setServiceCost(rs.getDouble("ServiceCost"));

            int staffID = rs.getInt("StaffID");
            if (!rs.wasNull()) {
                serviceBookingDTO.setStaffID(staffID);
                serviceBookingDTO.setStaffFirstName(rs.getString("StaffFirstName"));
                serviceBookingDTO.setStaffLastName(rs.getString("StaffLastName"));
                serviceBookingDTO.setStaffRole(rs.getString("StaffRole"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serviceBookingDTO;
    }
}
