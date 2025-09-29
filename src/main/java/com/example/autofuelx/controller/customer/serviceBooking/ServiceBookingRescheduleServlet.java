package com.example.autofuelx.controller.customer.serviceBooking;

import com.example.autofuelx.model.ServiceBooking;
import com.example.autofuelx.service.ServiceBookingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalTime;

@WebServlet("/user/service-booking/reschedule")
public class ServiceBookingRescheduleServlet extends HttpServlet {
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
        String newDateStr = request.getParameter("new-date");
        String newTimeStr = request.getParameter("new-time");
        String redirectUrl = request.getParameter("redirect-url");

        ServiceBooking booking = serviceBookingService.getBookingByID(bookingId);

        booking.setStatus("Awaiting Confirmation");
        LocalDate newDate = LocalDate.parse(newDateStr);
        LocalTime newTime = LocalTime.parse(newTimeStr);

        booking.setBookingDate(newDate);
        booking.setBookingTime(newTime);


        serviceBookingService.updateBooking(booking);

        // Redirect back to bookings page
        response.sendRedirect(request.getContextPath() + redirectUrl);
    }
}