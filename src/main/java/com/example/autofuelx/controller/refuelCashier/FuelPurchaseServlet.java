package com.example.autofuelx.controller.refuelCashier;

import com.example.autofuelx.model.Employee;
import com.example.autofuelx.model.Fuel;
import com.example.autofuelx.model.FuelPurchase;
import com.example.autofuelx.model.Vehicle;
import com.example.autofuelx.service.FuelPurchaseService;
import com.example.autofuelx.service.FuelService;
import com.example.autofuelx.service.VehicleService;
import com.example.autofuelx.util.AuthUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/refuel-cashier/fuel/purchase")
public class FuelPurchaseServlet extends HttpServlet {
    private FuelPurchaseService fuelPurchaseService;
    private VehicleService vehicleService;
    private FuelService fuelService;

    @Override
    public void init() throws ServletException {
        fuelPurchaseService = new FuelPurchaseService();
        vehicleService = new VehicleService();
        fuelService = new FuelService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Employee employee = AuthUtil.checkEmployeeLogin(request, response, "Refuel Cashier");
        if (employee == null) {
        } else response.sendRedirect(request.getContextPath() + "/views/refuel-cashier/fuel/refuel-form.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Employee employee = AuthUtil.checkEmployeeLogin(request, response, "Refuel Cashier");
        if (employee == null) return;

        String plateNumber = request.getParameter("plateNumber");
        int fuelID = Integer.parseInt(request.getParameter("fuelID"));
        double quantity = Double.parseDouble(request.getParameter("quantity"));


        Fuel fuel = fuelService.getFuelById(fuelID);
        if (fuel.getQuantity() < quantity) {
            response.sendRedirect(request.getContextPath() + "/refuel-cashier/fuel/refuel-form?purchase-status=failed");
            return;
        }

        // get vehicle using plate no
        Vehicle vehicle = vehicleService.getVehicleByPlateNo(plateNumber);
        double purchaseCost = fuelPurchaseService.getPurchaseCost(fuelID, quantity);

        FuelPurchase fuelPurchase = new FuelPurchase();
        if (vehicle != null) {
            fuelPurchase.setVehicleID(vehicle.getVehicleID());
            fuelPurchase.setCustomerID(vehicle.getCustomerID());
        }
        fuelPurchase.setFuelID(fuelID);
        fuelPurchase.setQuantity(quantity);
        fuelPurchase.setTotalCost(purchaseCost);

        try {
            fuelPurchaseService.makePurchase(fuelPurchase);
            response.sendRedirect(request.getContextPath() + "/refuel-cashier/fuel/refuel-form?purchase-status=success");
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/refuel-cashier/fuel/refuel-form?purchase-status=failed");
        }

    }
}
