package com.example.autofuelx.controller.admin.serviceBooking;

import com.example.autofuelx.dto.ServiceBookingDTO;
import com.example.autofuelx.model.Employee;
import com.example.autofuelx.service.ServiceBookingService;
import com.example.autofuelx.util.AuthUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/admin/service-booking/dashboard")
public class ServiceBookingAdminDashboardServlet extends HttpServlet {
    private ServiceBookingService serviceBookingService;

    @Override
    public void init() throws ServletException {
        serviceBookingService = new ServiceBookingService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Employee employee = AuthUtil.checkEmployeeLogin(request, response, "Admin");
        if (employee == null) return;

        // get all bookings for statistics
        List<ServiceBookingDTO> allBookings = serviceBookingService.getAllBookings();

        // count bookings by status
        Map<String, Integer> statusCounts = new HashMap<>();
        statusCounts.put("totalBookings", allBookings.size());

        // initialize all possible statuses with 0 count
        statusCounts.put("awaitingConfirmationCount", 0);
        statusCounts.put("confirmedCount", 0);
        statusCounts.put("rescheduleRequiredCount", 0);
        statusCounts.put("inProgressCount", 0);
        statusCounts.put("awaitingPickupCount", 0);
        statusCounts.put("completedCount", 0);
        statusCounts.put("cancelledCount", 0);
        statusCounts.put("missedAppointmentCount", 0);

        // count actual statuses
        for (ServiceBookingDTO booking : allBookings) {
            String status = booking.getStatus();
            switch (status) {
                case "Awaiting Confirmation":
                    statusCounts.put("awaitingConfirmationCount", statusCounts.get("awaitingConfirmationCount") + 1);
                    break;
                case "Confirmed":
                    statusCounts.put("confirmedCount", statusCounts.get("confirmedCount") + 1);
                    break;
                case "Reschedule Required":
                    statusCounts.put("rescheduleRequiredCount", statusCounts.get("rescheduleRequiredCount") + 1);
                    break;
                case "In Progress":
                    statusCounts.put("inProgressCount", statusCounts.get("inProgressCount") + 1);
                    break;
                case "Awaiting Pickup":
                    statusCounts.put("awaitingPickupCount", statusCounts.get("awaitingPickupCount") + 1);
                    break;
                case "Completed":
                    statusCounts.put("completedCount", statusCounts.get("completedCount") + 1);
                    break;
                case "Cancelled":
                    statusCounts.put("cancelledCount", statusCounts.get("cancelledCount") + 1);
                    break;
                case "Missed Appointment":
                    statusCounts.put("missedAppointmentCount", statusCounts.get("missedAppointmentCount") + 1);
                    break;
            }
        }

        // set attributes
        request.setAttribute("statusCounts", statusCounts);

        // forward to JSP
        request.getRequestDispatcher("/views/admin/service-booking/dashboard.jsp").forward(request, response);
    }
}