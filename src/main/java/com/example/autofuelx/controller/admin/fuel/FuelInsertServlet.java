package com.example.autofuelx.controller.admin.fuel;

import com.example.autofuelx.dao.FuelSupplyDAO;
import com.example.autofuelx.model.Fuel;
import com.example.autofuelx.model.FuelPurchase;
import com.example.autofuelx.model.FuelSupply;
import com.example.autofuelx.model.Vehicle;
import com.example.autofuelx.service.FuelPurchaseService;
import com.example.autofuelx.service.FuelService;
import com.example.autofuelx.service.FuelSupplyService;
import com.example.autofuelx.service.VehicleService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;

@WebServlet("/admin/fuel/insert/form")
public class FuelInsertServlet extends HttpServlet {
    private FuelSupplyService fuelSupplyService;

    @Override
    public void init() throws ServletException {
        fuelSupplyService = new FuelSupplyService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int fuelID = Integer.parseInt(request.getParameter("fuelID"));
        double quantity = Double.parseDouble(request.getParameter("quantity"));
        int fuelSupplierID = Integer.parseInt(request.getParameter("fuelSupplierID"));

        // Get vehicle
        FuelSupply fuelSupply = new FuelSupply();
        fuelSupply.setFuelID(fuelID);
        fuelSupply.setQuantity(quantity);
        fuelSupply.setSupplierID(fuelSupplierID);

        fuelSupplyService.addFuelSupply(fuelSupply);

        response.sendRedirect(request.getContextPath() + "/refuel-cashier/fuel/refuel-form");
    }
}
