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

    public ServiceBooking getBookingByID(int bookingID) {
        return serviceBookingDAO.getBookingByID(bookingID);
    }

    public ServiceBookingDTO getBookingDTOByID(int bookingID) {
        return serviceBookingDAO.getBookingDTOByID(bookingID);
    }

}
