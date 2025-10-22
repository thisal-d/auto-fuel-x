package com.example.autofuelx.controller.admin.fuel;

import com.example.autofuelx.dao.FuelSupplyDAO;
import com.example.autofuelx.model.*;
import com.example.autofuelx.service.FuelPurchaseService;
import com.example.autofuelx.service.FuelService;
import com.example.autofuelx.service.FuelSupplyService;
import com.example.autofuelx.service.VehicleService;
import com.example.autofuelx.util.AuthUtil;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = AuthUtil.checkEmployeeLogin(req, resp, "Admin");
        if (employee == null) return;
        resp.sendRedirect(req.getContextPath() + "/admin/fuel/level");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Employee employee = AuthUtil.checkEmployeeLogin(request, response, "Admin");
        if (employee == null) return;

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
