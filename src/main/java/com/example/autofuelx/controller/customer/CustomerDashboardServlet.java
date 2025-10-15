package com.example.autofuelx.controller.customer;


import com.example.autofuelx.dto.ComplaintSummaryDTO;
import com.example.autofuelx.dto.FuelUsageByTypeDTO;
import com.example.autofuelx.dto.ServiceBookingSummaryDTO;
import com.example.autofuelx.dto.VehicleSummaryDTO;
import com.example.autofuelx.model.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.example.autofuelx.service.ComplaintService;
import com.example.autofuelx.service.FuelPurchaseService;
import com.example.autofuelx.service.ServiceBookingService;
import com.example.autofuelx.service.VehicleService;

@WebServlet("/customer/dashboard")
public class CustomerDashboardServlet extends HttpServlet {
    private ComplaintService complaintService;
    private FuelPurchaseService fuelPurchaseService;
    private ServiceBookingService serviceBookingService;
    private VehicleService vehicleService;

    public void init() {
        complaintService = new ComplaintService();
        fuelPurchaseService = new FuelPurchaseService();
        serviceBookingService = new ServiceBookingService();
        vehicleService = new VehicleService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Check if customer is logged in
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("customer") == null) {
            // Redirect to login page if not logged in
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Customer customer = (Customer) session.getAttribute("customer");
        int customerID = customer.getCustomerID();

        try {
            // Get all summary data
            List<FuelUsageByTypeDTO> fuelUsageSummary = fuelPurchaseService.getFuelUsageSummary(customerID);
            List<VehicleSummaryDTO> vehicleSummary = vehicleService.getVehicleSummaryByCustomerID(customerID);
            ComplaintSummaryDTO complaintSummary = complaintService.getComplaintSummaryByCustomerID(customerID);
            ServiceBookingSummaryDTO serviceBookingSummary = serviceBookingService.getServiceBookingSummary(customerID);

            // Set attributes for JSP
            request.setAttribute("fuelUsageSummary", fuelUsageSummary);
            request.setAttribute("vehicleSummary", vehicleSummary);
            request.setAttribute("complaintSummary", complaintSummary);
            request.setAttribute("serviceBookingSummary", serviceBookingSummary);

            // Forward to dashboard JSP
            request.getRequestDispatcher("/views/customer/dashboard.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // Forward to error page
            request.setAttribute("errorMessage", "Error loading dashboard: " + e.getMessage());
            request.getRequestDispatcher("/views/error.jsp").forward(request, response);
        }
    }
}