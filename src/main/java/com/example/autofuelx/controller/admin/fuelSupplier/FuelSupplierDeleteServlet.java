package com.example.autofuelx.controller.admin.fuelSupplier;


import com.example.autofuelx.model.FuelSupplier;
import com.example.autofuelx.service.FuelSupplierService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/fuel-supplier/delete")
public class FuelSupplierDeleteServlet extends HttpServlet {
    FuelSupplierService fuelSupplierService;

    @Override
    public void init() throws ServletException {
        fuelSupplierService = new FuelSupplierService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get form parameters
        String supplierIDStr = request.getParameter("fuel-supplier-id");
        int supplierID = Integer.parseInt(supplierIDStr);

        fuelSupplierService.deleteSupplier(supplierID);

        // Redirect back to form with success message
        response.sendRedirect(request.getContextPath() + "/admin/fuel-supplier/list");
    }
}
