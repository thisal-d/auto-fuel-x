package com.example.autofuelx.controller.admin.fuel;

import com.example.autofuelx.model.Employee;
import com.example.autofuelx.model.Fuel;
import com.example.autofuelx.service.FuelService;
import com.example.autofuelx.util.AuthUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/fuel/update")
public class FuelUpdateServlet extends HttpServlet {
    private FuelService fuelService;

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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Employee employee = AuthUtil.checkEmployeeLogin(request, response, "Admin");
        if (employee == null) return;

        int fuelID = Integer.parseInt(request.getParameter("fuelID"));
        double costPerLiter = Double.parseDouble(request.getParameter("costPerLiter"));

        // get fuel
        Fuel fuel = fuelService.getFuelById(fuelID);
        if (fuel != null) {
            fuel.setCostPerLiter(costPerLiter);
            fuelService.updateFuel(fuel);
        }

        response.sendRedirect(request.getContextPath() + "/admin/fuel/level");
    }
}
