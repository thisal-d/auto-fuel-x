package com.example.autofuelx.controller.customer.serviceBooking;

import com.example.autofuelx.dto.ServiceBookingDTO;
import com.example.autofuelx.model.Customer;
import com.example.autofuelx.model.Vehicle;
import com.example.autofuelx.service.ServiceBookingService;
import com.example.autofuelx.service.VehicleService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/customer/service-booking/history")
public class HistoryBookingHistoryServlet extends HttpServlet {
    private ServiceBookingService serviceBookingService;
    private VehicleService vehicleService ;

    @Override
    public void init() throws ServletException {
        serviceBookingService = new ServiceBookingService();
        vehicleService = new VehicleService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer loggedCustomer = (Customer) session.getAttribute("customer");

        if (loggedCustomer == null) {
            response.sendRedirect(request.getContextPath() + "/views/customer/login.jsp");
            return;
        }

        // Get filter parameters
        String startDate = request.getParameter("start-date");
        String endDate = request.getParameter("end-date");
        String vehicleType = request.getParameter("vehicle-type");
        String vehicle = request.getParameter("vehicle");
        String status = request.getParameter("status");
        String minCost = request.getParameter("min-cost");
        String maxCost = request.getParameter("max-cost");
        String keyword = request.getParameter("keyword");

        // Set attributes for the form to remember selections
        request.setAttribute("start-date", startDate);
        request.setAttribute("end-date", endDate);
        request.setAttribute("vehicle-type", vehicleType);
        request.setAttribute("vehicle", vehicle);
        request.setAttribute("status", status);
        request.setAttribute("min-cost", minCost);
        request.setAttribute("max-cost", maxCost);
        request.setAttribute("keyword", keyword);

        // Get data for dropdowns
        List<String> vehicleTypes = vehicleService.getVehicleTypesByCustomerID(loggedCustomer.getCustomerID());
        List<Vehicle> vehicles = vehicleService.getVehiclesByCustomerID(loggedCustomer.getCustomerID());
        List<String> statuses = Arrays.asList("All", "Awaiting Confirmation", "Confirmed", "In Progress",
                "Completed", "Missed Appointment", "Awaiting Pickup", "Canceled");

        // Get filtered bookings
        List<ServiceBookingDTO> bookings = serviceBookingService.getBookingsByCustomerWithFilters(
                loggedCustomer.getCustomerID(),
                startDate,
                endDate,
                vehicleType,
                vehicle,
                status,
                minCost,
                maxCost,
                keyword
        );

        request.setAttribute("vehicleTypes", vehicleTypes);
        request.setAttribute("vehicles", vehicles);
        request.setAttribute("statuses", statuses);
        request.setAttribute("bookings", bookings);

        request.getRequestDispatcher("/views/customer/service-booking/history.jsp").forward(request, response);
    }
}