package com.example.autofuelx.controller.customer.serviceBooking;

import com.example.autofuelx.model.ServiceBooking;
import com.example.autofuelx.service.ServiceBookingService;
import com.example.autofuelx.util.AuthUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.autofuelx.model.Customer;
import java.io.IOException;

@WebServlet("/user/service-booking/update-status")
public class BookingStatusUpdateCustomerServlet extends HttpServlet {
    private ServiceBookingService serviceBookingService;

    @Override
    public void init() throws ServletException {
        serviceBookingService = new ServiceBookingService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer = AuthUtil.checkCustomerLogin(req, resp);
        if (customer == null) return;

        resp.sendRedirect(req.getContextPath() + "/customer/service-booking/list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Customer customer = AuthUtil.checkCustomerLogin(request, response);
        if (customer == null) return;

        // get parameters
        int bookingId = Integer.parseInt(request.getParameter("booking-ID"));
        String status = request.getParameter("status");
        String redirectUrl = request.getParameter("redirect-url");

        ServiceBooking booking = serviceBookingService.getBookingByID(bookingId);
        System.out.println(booking.getStaffID());
        booking.setStatus(status);
        serviceBookingService.updateBooking(booking);

        // Redirect back to  bookings page
        response.sendRedirect(request.getContextPath() + redirectUrl);
    }
}