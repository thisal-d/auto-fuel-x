package com.example.autofuelx.controller.customer.serviceBooking;


import com.example.autofuelx.model.ServiceBooking;
import com.example.autofuelx.service.ServiceBookingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user/service-booking/update-status")
public class BookingStatusUpdateCustomerServlet extends HttpServlet {
    private ServiceBookingService serviceBookingService;

    @Override
    public void init() throws ServletException {
        serviceBookingService = new ServiceBookingService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get parameters
        int bookingId = Integer.parseInt(request.getParameter("booking-ID"));
        String status = request.getParameter("status");
        String redirectUrl = request.getParameter("redirect-url");

        ServiceBooking booking = serviceBookingService.getBookingByID(bookingId);
        booking.setStatus(status);
        serviceBookingService.updateBooking(booking);

        // Redirect back to  bookings page
        response.sendRedirect(request.getContextPath() + redirectUrl);
    }
}