package com.example.autofuelx.controller.customer.serviceBooking;

import com.example.autofuelx.model.Customer;
import com.example.autofuelx.model.ServiceBooking;
import com.example.autofuelx.service.ServiceBookingService;
import com.example.autofuelx.util.AuthUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

@WebServlet("/customer/service-booking/create")
public class ServiceBookingServlet extends HttpServlet {
    private ServiceBookingService serviceBookingService;

    @Override
    public void init() throws ServletException {
        serviceBookingService = new ServiceBookingService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer = AuthUtil.checkCustomerLogin(req, resp);
        if (customer == null) return;

        resp.sendRedirect(req.getContextPath() + "/views/customer/service-booking/form.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Customer customer = AuthUtil.checkCustomerLogin(request, response);
        if (customer == null) return;

        // get form parameters
        int vehicleId = Integer.parseInt(request.getParameter("vehicle-id"));
        int serviceId = Integer.parseInt(request.getParameter("service-id"));
        String date = request.getParameter("booking-date");
        String time = request.getParameter("booking-time");

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
        response.sendRedirect(request.getContextPath() + "/views/customer/service-booking/success.jsp");
    }
}