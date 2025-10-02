package com.example.autofuelx.controller.admin.fuel;

import com.example.autofuelx.model.Fuel;
import com.example.autofuelx.service.FuelService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/fuel/level")
public class FuelFuelLevelServlet extends HttpServlet {
    private FuelService fuelService;

    @Override
    public void init() throws ServletException {
        fuelService = new FuelService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        List<Fuel> fuelTypes = fuelService.getAllFuels();
        request.setAttribute("fuel-types", fuelTypes);

        request.getRequestDispatcher("/views/admin/fuel/view.jsp").forward(request, response);
    }
}
