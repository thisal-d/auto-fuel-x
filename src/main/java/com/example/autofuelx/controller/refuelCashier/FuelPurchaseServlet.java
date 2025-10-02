package com.example.autofuelx.controller.refuelCashier;

import com.example.autofuelx.model.Employee;
import com.example.autofuelx.model.FuelPurchase;
import com.example.autofuelx.model.Vehicle;
import com.example.autofuelx.service.FuelPurchaseService;
import com.example.autofuelx.service.VehicleService;
import com.example.autofuelx.util.AuthUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;

@WebServlet("/refuel-cashier/fuel/purchase")
public class FuelPurchaseServlet extends HttpServlet {
    private FuelPurchaseService fuelPurchaseService;
    private VehicleService vehicleService;

    @Override
    public void init() throws ServletException {
        fuelPurchaseService = new FuelPurchaseService();
        vehicleService = new VehicleService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Employee employee = AuthUtil.checkEmployeeLogin(request, response, "Refuel Cashier");
        if (employee == null) return;

        String plateNumber = request.getParameter("plateNumber");
        int fuelID= Integer.parseInt(request.getParameter("fuelID"));
        double quantity = Double.parseDouble(request.getParameter("quantity"));

        // Get vehicle
        Vehicle vehicle = vehicleService.getVehicleByPlateNo(plateNumber);
        double purchaseCost = fuelPurchaseService.getPurchaseCost(fuelID, quantity);

        FuelPurchase fuelPurchase = new FuelPurchase();
        fuelPurchase.setVehicleID(vehicle.getVehicleID());
        fuelPurchase.setFuelID(fuelID);
        fuelPurchase.setQuantity(quantity);
        fuelPurchase.setCustomerID(vehicle.getCustomerID());
        fuelPurchase.setTotalCost(purchaseCost);

        // FuelPurchaseServlet after saving purchase
        try {
            fuelPurchaseService.makePurchase(fuelPurchase);
            // use query param to pass status
            response.sendRedirect(request.getContextPath() + "/refuel-cashier/fuel/refuel-form?purchase-status=success");
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/refuel-cashier/fuel/refuel-form?purchase-status=failed");
        }

    }
}
