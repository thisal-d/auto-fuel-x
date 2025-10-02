package com.example.autofuelx.service;

import com.example.autofuelx.dao.ServiceBookingDAO;
import com.example.autofuelx.dto.ServiceBookingDTO;
import com.example.autofuelx.model.ServiceBooking;
import java.util.List;

public class ServiceBookingService {
    ServiceBookingDAO serviceBookingDAO;

    public ServiceBookingService() {
        serviceBookingDAO = new ServiceBookingDAO();
    }

    public boolean bookService(ServiceBooking serviceBooking) {
        return serviceBookingDAO.insertBooking(serviceBooking);
    }

    public boolean updateBooking(ServiceBooking serviceBooking) {
        return serviceBookingDAO.updateBooking(serviceBooking);
    }

    public List<ServiceBookingDTO> getAllBookings() {
        return serviceBookingDAO.getAllBookings();
    }

    public List<ServiceBookingDTO> getAllBookings(String status) {
        return serviceBookingDAO.getAllBookings(status);
    }

    public List<ServiceBookingDTO> getBookingsByCustomerID(int customerID) {
        return serviceBookingDAO.getBookingsByCustomer(customerID);
    }

    public List<ServiceBookingDTO> getActiveBookingsByCustomerID(int customerID) {
        return serviceBookingDAO.getActiveBookingsByCustomerID(customerID);
    }

    public ServiceBooking getBookingByID(int bookingID) {
        return serviceBookingDAO.getBookingByID(bookingID);
    }

    public ServiceBookingDTO getBookingDTOByID(int bookingID) {
        return serviceBookingDAO.getBookingDTOByID(bookingID);
    }

    public List<ServiceBookingDTO> getBookingsByCustomerIDAndStatus(int customerID, String status) {
        return serviceBookingDAO.getBookingsByCustomerIDAndStatus(customerID, status);
    }

    public List<ServiceBookingDTO> getActiveBookingByEmployee(int employeeID) {
        return serviceBookingDAO.getActiveBookingByEmployee(employeeID);
    }

    public List<ServiceBookingDTO> getBookingsByCustomerWithFilters(
            int customerID,
            String startDate,
            String endDate,
            String vehicleType,
            String vehicle,
            String status,
            String minCost,
            String maxCost,
            String keyword) {
        return serviceBookingDAO.getBookingsByCustomerWithFilters(
                customerID, startDate, endDate, vehicleType, vehicle, status, minCost, maxCost, keyword);
    }


}
