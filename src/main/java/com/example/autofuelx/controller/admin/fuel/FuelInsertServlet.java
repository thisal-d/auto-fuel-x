package com.example.autofuelx.controller.admin.fuel;

import com.example.autofuelx.model.Fuel;
import com.example.autofuelx.model.FuelPurchase;
import com.example.autofuelx.model.Vehicle;
import com.example.autofuelx.service.FuelPurchaseService;
import com.example.autofuelx.service.FuelService;
import com.example.autofuelx.service.VehicleService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;

@WebServlet("/admin/fuel/insert/form")
public class FuelInsertServlet extends HttpServlet {
    private FuelService fuelService;

    @Override
    public void init() throws ServletException {
        fuelService = new FuelService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int fuelID = Integer.parseInt(request.getParameter("fuelID"));
        double quantity = Double.parseDouble(request.getParameter("quantity"));

        // Get vehicle
        Fuel fuel = fuelService.getFuelById(fuelID);

        fuel.setQuantity(fuel.getQuantity() + quantity);
        fuelService.updateFuel(fuel);

        response.sendRedirect(request.getContextPath() + "/refuel-cashier/fuel/refuel-form");


    }
}
