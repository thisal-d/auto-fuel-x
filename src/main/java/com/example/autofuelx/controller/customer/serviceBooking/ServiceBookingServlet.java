package com.example.autofuelx.controller.customer.serviceBooking;

import com.example.autofuelx.model.Customer;
import com.example.autofuelx.model.ServiceBooking;
import com.example.autofuelx.service.ServiceBookingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

@WebServlet("/customer/service/booking")
public class ServiceBookingServlet extends HttpServlet {
    private ServiceBookingService serviceBookingService;

    @Override
    public void init() throws ServletException {
        serviceBookingService = new ServiceBookingService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get form parameters
        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
        int serviceId = Integer.parseInt(request.getParameter("serviceId"));
        String date = request.getParameter("date");
        String time = request.getParameter("time");

        // Get logged-in customer from session
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        // Create new service bookings
        ServiceBooking booking = new ServiceBooking();
        booking.setVehicleID(vehicleId);
        booking.setServiceID(serviceId);
        booking.setBookingDate(LocalDate.parse(date));
        booking.setBookingTime(LocalTime.parse(time));
        booking.setCustomerID(customer.getCustomerID());
        booking.setStatus("Awaiting Confirmation");

        // Save booking to database
        serviceBookingService.bookService(booking);

        // Redirect to confirmation page
        response.sendRedirect(request.getContextPath() + "/views/customer/service/booking-success.jsp");
    }
}