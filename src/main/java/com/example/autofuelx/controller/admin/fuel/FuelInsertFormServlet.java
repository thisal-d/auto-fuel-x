package com.example.autofuelx.controller.admin.fuel;

import com.example.autofuelx.model.Employee;
import com.example.autofuelx.model.Fuel;
import com.example.autofuelx.model.FuelSupplier;
import com.example.autofuelx.service.FuelService;
import com.example.autofuelx.service.FuelSupplierService;
import com.example.autofuelx.util.AuthUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/fuel/insert-form")
public class FuelInsertFormServlet extends HttpServlet {
    private FuelService fuelService;
    private FuelSupplierService fuelSupplierService;

    @Override
    public void init() throws ServletException {
        fuelService = new FuelService();
        fuelSupplierService = new FuelSupplierService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Employee employee = AuthUtil.checkEmployeeLogin(request, response, "Admin");
        if (employee == null) return;

        List<Fuel> fuelTypes = fuelService.getAllFuels();
        List<FuelSupplier> fuelSuppliers = fuelSupplierService.getAllSuppliers();
        request.setAttribute("fuel-types", fuelTypes);
        request.setAttribute("fuel-suppliers", fuelSuppliers);

        request.getRequestDispatcher("/views/admin/fuel/insert.jsp").forward(request, response);
    }
}
