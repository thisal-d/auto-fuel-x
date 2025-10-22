package com.example.autofuelx.controller.refuelCashier;

import com.example.autofuelx.model.Employee;
import com.example.autofuelx.model.Fuel;
import com.example.autofuelx.service.FuelService;
import com.example.autofuelx.util.AuthUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;
import java.util.List;

@WebServlet("/refuel-cashier/fuel/refuel-form")
public class RefuelFormServlet extends HttpServlet {
    private FuelService fuelService;

    @Override
    public void init() throws ServletException {
        fuelService = new FuelService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Employee employee = AuthUtil.checkEmployeeLogin(request, response, "Refuel Cashier");
        if (employee == null) return;

        List<Fuel> fuelTypes = fuelService.getAllFuels();
        request.setAttribute("fuel-types", fuelTypes);

        request.getRequestDispatcher("/views/refuel-cashier/fuel/refuel-form.jsp?refuel-form?purchase-status=" + request.getParameter("purchase-status")).forward(request, response);
    }

}
