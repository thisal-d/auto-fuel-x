package com.example.autofuelx.controller.admin.fuel;

import com.example.autofuelx.model.Fuel;
import com.example.autofuelx.model.FuelSupplier;
import com.example.autofuelx.service.FuelService;
import com.example.autofuelx.service.FuelSupplierService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;
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

        List<Fuel> fuelTypes = fuelService.getAllFuels();
        List<FuelSupplier> fuelSuppliers = fuelSupplierService.getAllSuppliers();
        request.setAttribute("fuel-types", fuelTypes);
        request.setAttribute("fuel-suppliers", fuelSuppliers);

        request.getRequestDispatcher("/views/admin/fuel/insert.jsp").forward(request, response);
    }
}
