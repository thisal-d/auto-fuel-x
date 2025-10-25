package com.example.autofuelx.dao;

import com.example.autofuelx.dto.ServiceBookingDTO;
import com.example.autofuelx.dto.ServiceBookingSummaryDTO;
import com.example.autofuelx.model.ServiceBooking;
import com.example.autofuelx.util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ServiceBookingDAO {
    public ServiceBooking getBookingByID(int bookingID) {
        String sql = "SELECT * FROM ServiceBooking WHERE BookingID = ?";
        ServiceBooking booking = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookingID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                booking = extractBookingFromResultSet(rs);
            }
            return booking;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Insert new booking
    public boolean insertBooking(ServiceBooking booking) {
        String sql = "INSERT INTO ServiceBooking (CustomerID, VehicleID, ServiceID, BookingDate, BookingTime, Status) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, booking.getCustomerID());
            stmt.setInt(2, booking.getVehicleID());
            stmt.setInt(3, booking.getServiceID());

            stmt.setDate(4, Date.valueOf(booking.getBookingDate()));
            stmt.setTime(5, Time.valueOf(booking.getBookingTime()));
            stmt.setString(6, booking.getStatus());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<ServiceBookingDTO> getActiveBookingsByCustomerID(int customerID) {
        List<ServiceBookingDTO> bookingDTOList = new ArrayList<>();
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
                       s.Description AS ServiceDescription,
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
                WHERE (sb.Status = ? OR sb.Status = ? OR sb.Status = ? OR sb.Status = ? OR sb.Status = ? ) AND c.CustomerID = ?
                ORDER BY sb.BookingDate DESC, sb.BookingTime DESC
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "Awaiting Confirmation");
            stmt.setString(2, "Confirmed");
            stmt.setString(3, "In Progress");
            stmt.setString(4, "Reschedule Required");
            stmt.setString(5, "Awaiting Pickup");
            stmt.setInt(6, customerID);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                bookingDTOList.add(extractBookingDetailFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookingDTOList;
    }

    public List<ServiceBookingDTO> getBookingsByCustomerIDAndStatus(int customerID, String status) {
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
                       s.Description AS ServiceDescription,
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
                WHERE sb.Status = ? AND sb.CustomerID = ?
                ORDER BY sb.BookingDate DESC, sb.BookingTime DESC
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, customerID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                bookings.add(extractBookingDetailFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;
    }

    // Update existing booking
    public boolean updateBooking(ServiceBooking booking) {
        String sql = "UPDATE ServiceBooking SET VehicleID = ?, ServiceID = ?, BookingDate = ?, BookingTime = ?, Status = ?, StaffID = ?, TotalCost = ? WHERE BookingID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, booking.getVehicleID());
            stmt.setInt(2, booking.getServiceID());
            stmt.setDate(3, Date.valueOf(booking.getBookingDate()));
            stmt.setTime(4, Time.valueOf(booking.getBookingTime()));
            stmt.setString(5, booking.getStatus());

            if (booking.getStaffID() != null) {
                stmt.setInt(6, booking.getStaffID());
            } else {
                stmt.setNull(6, Types.INTEGER);
            }
            if (booking.getTotalCost() != null) stmt.setDouble(7, booking.getTotalCost());
            else {
                stmt.setNull(7, Types.DOUBLE);
            }

            stmt.setInt(8, booking.getBookingID());

            return stmt.executeUpdate() > 0;

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
                       s.Description AS ServiceDescription,
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
                        s.Description AS ServiceDescription,
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
                       s.Description AS ServiceDescription,
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
                       s.Description AS ServiceDescription,
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
                       s.Description AS ServiceDescription,
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
                       s.Description AS ServiceDescription,
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

    public List<ServiceBookingDTO> getActiveBookingByEmployee(int employeeID) {
        List<ServiceBookingDTO> bookingDTOList = new ArrayList<>();
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
                       s.Description AS ServiceDescription,
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
                WHERE (sb.Status = ? OR sb.Status = ?) AND sb.StaffID = ?
                ORDER BY sb.BookingDate DESC, sb.BookingTime DESC
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "Confirmed");
            stmt.setString(2, "In Progress");
            stmt.setInt(3, employeeID);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                bookingDTOList.add(extractBookingDetailFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookingDTOList;
    }

    private ServiceBooking extractBookingFromResultSet(ResultSet rs) {
        try {
            ServiceBooking booking = new ServiceBooking();
            booking.setBookingID(rs.getInt("BookingID"));
            booking.setCustomerID(rs.getInt("CustomerID"));
            booking.setVehicleID(rs.getInt("VehicleID"));
            booking.setServiceID(rs.getInt("ServiceID"));
            booking.setStatus(rs.getString("Status"));
            booking.setTotalCost(rs.getDouble("TotalCost"));

            // Fix: set StaffID properly
            int staffID = rs.getInt("StaffID");
            if (!rs.wasNull()) {
                booking.setStaffID(staffID);
            }

            // Handle booking date and time
            java.sql.Date bookingDate = rs.getDate("BookingDate");
            java.sql.Time bookingTime = rs.getTime("BookingTime");

            if (bookingDate != null) booking.setBookingDate(bookingDate.toLocalDate());
            if (bookingTime != null) booking.setBookingTime(bookingTime.toLocalTime());

            return booking;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<ServiceBookingDTO> getBookingsByCustomerWithFilters(
            int customerID,
            String startDateStr,
            String endDateStr,
            String vehicleTypeFilter,
            String vehicleFilter,
            String statusFilter,
            String minCostStr,
            String maxCostStr,
            String keyword) {

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
                       s.Description AS ServiceDescription,
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

        // date range filter
        if (startDateStr != null && !startDateStr.isEmpty()) {
            sql += " AND sb.BookingDate >= ?";
            parameters.add(LocalDate.parse(startDateStr));
        }
        if (endDateStr != null && !endDateStr.isEmpty()) {
            sql += " AND sb.BookingDate <= ?";
            parameters.add(LocalDate.parse(endDateStr));
        }

        // vehicle type filter
        if (vehicleTypeFilter != null && !vehicleTypeFilter.isEmpty() && !vehicleTypeFilter.equals("All")) {
            sql += " AND v.Type = ?";
            parameters.add(vehicleTypeFilter);
        }

        // vehicle filter
        if (vehicleFilter != null && !vehicleFilter.isEmpty() && !vehicleFilter.equals("All")) {
            sql += " AND v.VehicleID = ?";
            parameters.add(Integer.parseInt(vehicleFilter));
        }

        // status filter
        if (statusFilter != null && !statusFilter.isEmpty() && !statusFilter.equals("All")) {
            sql += " AND sb.Status = ?";
            parameters.add(statusFilter);
        }

        // cost range filter
        if (minCostStr != null && !minCostStr.isEmpty()) {
            sql += " AND sb.TotalCost >= ?";
            parameters.add(Double.parseDouble(minCostStr));
        }
        if (maxCostStr != null && !maxCostStr.isEmpty()) {
            sql += " AND sb.TotalCost <= ?";
            parameters.add(Double.parseDouble(maxCostStr));
        }

        // keywo
        if (keyword != null && !keyword.isEmpty()) {
            sql += " AND (s.Type LIKE ? OR s.Description LIKE ?)";
            String keywordPattern = "%" + keyword + "%";
            parameters.add(keywordPattern);
            parameters.add(keywordPattern);
        }

        sql += " ORDER BY sb.BookingDate DESC, sb.BookingTime DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set parameters
            for (int i = 0; i < parameters.size(); i++) {
                Object param = parameters.get(i);
                if (param instanceof String) {
                    stmt.setString(i + 1, (String) param);
                } else if (param instanceof Integer) {
                    stmt.setInt(i + 1, (Integer) param);
                } else if (param instanceof LocalDate) {
                    stmt.setDate(i + 1, Date.valueOf((LocalDate) param));
                } else if (param instanceof Double) {
                    stmt.setDouble(i + 1, (Double) param);
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

    public ServiceBookingSummaryDTO getCustomerServiceBookingSummary(int customerID) {
        ServiceBookingSummaryDTO summary = new ServiceBookingSummaryDTO();

        String sql = """
                    SELECT
                        -- Total bookings for this customer
                        COUNT(*) AS TotalBookings,
                
                        -- Active bookings are those that are not completed, missed, or cancelled
                        SUM(CASE WHEN Status NOT IN ('Completed', 'Missed Appointment', 'Cancelled') THEN 1 ELSE 0 END) AS TotalActiveBookings,
                
                        -- Count of completed bookings
                        SUM(CASE WHEN Status = 'Completed' THEN 1 ELSE 0 END) AS TotalCompletedBookings,
                
                        -- Detailed breakdown of each status
                        SUM(CASE WHEN Status = 'Awaiting Confirmation' THEN 1 ELSE 0 END) AS TotalAwaitingConfirmation,
                        SUM(CASE WHEN Status = 'Confirmed' THEN 1 ELSE 0 END) AS TotalConfirmed,
                        SUM(CASE WHEN Status = 'In Progress' THEN 1 ELSE 0 END) AS TotalInProgress,
                        SUM(CASE WHEN Status = 'Missed Appointment' THEN 1 ELSE 0 END) AS TotalMissedAppointment,
                        SUM(CASE WHEN Status = 'Awaiting Pickup' THEN 1 ELSE 0 END) AS TotalAwaitingPickup,
                        SUM(CASE WHEN Status = 'Reschedule Required' THEN 1 ELSE 0 END) AS TotalRescheduleRequired,
                        SUM(CASE WHEN Status = 'Cancelled' THEN 1 ELSE 0 END) AS TotalCancelled
                    FROM ServiceBooking
                    WHERE CustomerID = ?
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Populate the summary DTO with the results from the query
                summary.setTotalBookings(rs.getInt("TotalBookings"));
                summary.setTotalActiveBookings(rs.getInt("TotalActiveBookings"));
                summary.setTotalCompletedBookings(rs.getInt("TotalCompletedBookings"));
                summary.setTotalAwaitingConfirmation(rs.getInt("TotalAwaitingConfirmation"));
                summary.setTotalConfirmed(rs.getInt("TotalConfirmed"));
                summary.setTotalInProgress(rs.getInt("TotalInProgress"));
                summary.setTotalMissedAppointment(rs.getInt("TotalMissedAppointment"));
                summary.setTotalAwaitingPickup(rs.getInt("TotalAwaitingPickup"));
                summary.setTotalRescheduleRequired(rs.getInt("TotalRescheduleRequired"));
                summary.setTotalCancelled(rs.getInt("TotalCancelled"));
            }

            return summary;

        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Return null to indicate an error
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
            serviceBookingDTO.setServiceDescription(rs.getString("ServiceDescription"));
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
