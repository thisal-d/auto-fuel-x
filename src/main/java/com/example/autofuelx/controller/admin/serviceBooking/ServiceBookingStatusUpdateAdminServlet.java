package com.example.autofuelx.controller.admin.serviceBooking;


import com.example.autofuelx.model.Employee;
import com.example.autofuelx.model.ServiceBooking;
import com.example.autofuelx.service.ServiceBookingService;
import com.example.autofuelx.util.AuthUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/service-booking/update-status")
public class ServiceBookingStatusUpdateAdminServlet extends HttpServlet {
    private ServiceBookingService serviceBookingService;

    @Override
    public void init() throws ServletException {
        serviceBookingService = new ServiceBookingService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = AuthUtil.checkEmployeeLogin(req, resp, "Admin");
        if (employee == null) return;

        resp.sendRedirect(req.getContextPath() + "/admin/service-booking/view");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Employee employee = AuthUtil.checkEmployeeLogin(request, response, "Admin");
        if (employee == null) return;

        // get parameters
        int bookingId = Integer.parseInt(request.getParameter("bookingId"));
        String status = request.getParameter("status");
        String redirectUrl = request.getParameter("redirect-url");

        ServiceBooking booking = serviceBookingService.getBookingByID(bookingId);
        System.out.println("STAFF ID " + booking.getStaffID());

        booking.setStatus(status);
        if (status.equals("Confirmed")) {
            int staffId = Integer.parseInt(request.getParameter("employeeId"));
            booking.setStaffID(staffId);
        } else if (status.equals("Awaiting Pickup")) {
            double totalCost = Double.parseDouble(request.getParameter("total-cost"));
            booking.setTotalCost(totalCost);
        }

        serviceBookingService.updateBooking(booking);

        // Redirect back to  bookings page
        response.sendRedirect(request.getContextPath() + redirectUrl);
    }
}