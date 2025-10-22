package com.example.autofuelx.controller.admin.fuel;


import com.example.autofuelx.model.Employee;
import com.example.autofuelx.model.Fuel;
import com.example.autofuelx.service.FuelService;
import com.example.autofuelx.util.AuthUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/admin/fuel/add")
public class FuelTypeAddServlet extends HttpServlet {
    FuelService fuelService = new FuelService();

    @Override
    public void init() throws ServletException {
        fuelService = new FuelService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = AuthUtil.checkEmployeeLogin(req, resp, "Admin");
        if (employee == null) return;
        resp.sendRedirect(req.getContextPath() + "/admin/fuel/level");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Employee employee = AuthUtil.checkEmployeeLogin(request, response, "Admin");
        if (employee == null) return;

        // Get form parameters
        String type = request.getParameter("type");
        String quantityStr = request.getParameter("quantity");
        String costStr = request.getParameter("costPerLiter");

        Fuel fuel = new Fuel();
        fuel.setType(type);
        fuel.setQuantity(Double.parseDouble(quantityStr));
        fuel.setCostPerLiter(Double.parseDouble(costStr));

        fuelService.addFuel(fuel);

        // Redirect back to form with success message
        response.sendRedirect(request.getContextPath() + "/admin/fuel/level");
    }
}
