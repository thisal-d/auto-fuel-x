package com.example.autofuelx.controller.admin.fuelSupplier;


import com.example.autofuelx.model.Employee;
import com.example.autofuelx.model.FuelSupplier;
import com.example.autofuelx.service.FuelSupplierService;
import com.example.autofuelx.util.AuthUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/fuel-supplier/edit-form")
public class FuelSupplierUpdateFormServlet extends HttpServlet {
    FuelSupplierService fuelSupplierService;

    @Override
    public void init() throws ServletException {
        fuelSupplierService = new FuelSupplierService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Employee employee = AuthUtil.checkEmployeeLogin(request, response, "Admin");
        if (employee == null) return;

        // Get form parameters
        String supplierIDStr = request.getParameter("fuel-supplier-id");
        int supplierID = Integer.parseInt(supplierIDStr);

        FuelSupplier fuelSupplier = fuelSupplierService.getSupplierById(supplierID);
        request.setAttribute("fuelSupplier", fuelSupplier);

        request.getRequestDispatcher("/views/admin/fuel-supplier/update-form.jsp").forward(request, response);
    }
}
