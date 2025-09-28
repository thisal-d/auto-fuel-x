package com.example.autofuelx.controller.customer.Fuel;

import com.example.autofuelx.dto.FuelPurchaseDetailDTO;
import com.example.autofuelx.model.Customer;
import com.example.autofuelx.model.Fuel;
import com.example.autofuelx.model.Vehicle;
import com.example.autofuelx.service.FuelPurchaseService;
import com.example.autofuelx.service.FuelService;
import com.example.autofuelx.service.VehicleService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/customer/fuel/history")
public class UsageHistoryViewServlet extends HttpServlet {
    private FuelPurchaseService fuelPurchaseService;
    private VehicleService vehicleService;
    private FuelService fuelService;

    @Override
    public void init() throws ServletException {
        fuelPurchaseService = new FuelPurchaseService();
        vehicleService = new VehicleService();
        fuelService = new FuelService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Customer loggedCustomer = (Customer) session.getAttribute("customer");

        if (loggedCustomer == null) {
            response.sendRedirect(request.getContextPath() + "/views/customer/login.jsp");
            return;
        }

        // read filter parameters from request
        String durationDateStartFilter = request.getParameter("duration-date-start-filter");
        String durationDateEndFilter = request.getParameter("duration-date-end-filter");
        String vehicleTypeFilter = request.getParameter("vehicle-type-filter");
        String vehicleFilter = request.getParameter("vehicle-filter");  // e.g., vehicle ID as String
        String fuelTypeFilter = request.getParameter("fuel-type-filter");  // e.g., fuel ID as String

        request.setAttribute("duration-date-start-filter", durationDateStartFilter);
        request.setAttribute("duration-date-end-filter", durationDateEndFilter);
        request.setAttribute("vehicle-type-filter", vehicleTypeFilter);
        request.setAttribute("vehicle-filter", vehicleFilter);
        request.setAttribute("fuel-type-filter", fuelTypeFilter);

        List<String> vehicleTypes = vehicleService.getVehicleTypesByCustomerID(loggedCustomer.getCustomerID());
        List<FuelPurchaseDetailDTO> fuelUsageDetails = fuelPurchaseService.getFuelPurchaseDetailsByCustomerID(
                loggedCustomer.getCustomerID(), durationDateStartFilter, durationDateEndFilter, vehicleTypeFilter, vehicleFilter, fuelTypeFilter);
        List<Fuel> fuels = fuelService.getAllFuels();
        List<Vehicle> vehicles = vehicleService.getVehiclesByCustomerID(loggedCustomer.getCustomerID());

        request.setAttribute("fuel-usage-details", fuelUsageDetails);
        request.setAttribute("vehicle-types", vehicleTypes);
        request.setAttribute("vehicles", vehicles);
        request.setAttribute("fuels", fuels);

        request.getRequestDispatcher("/views/customer/fuel/history.jsp").forward(request, response);
    }
}
