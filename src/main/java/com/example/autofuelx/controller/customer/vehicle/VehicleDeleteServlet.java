package com.example.autofuelx.controller.customer.vehicle;

import com.example.autofuelx.model.Customer;
import com.example.autofuelx.service.VehicleService;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/customer/vehicle/delete")
public class VehicleDeleteServlet extends HttpServlet {
    private VehicleService vehicleService;

    @Override
    public void init() {
        vehicleService = new VehicleService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        if (customer == null) {
            response.sendRedirect(request.getContextPath() + "/views/customer/login.jsp");
            return;
        }

        int vehicleID = Integer.parseInt(request.getParameter("vehicleID"));
        vehicleService.deleteVehicle(vehicleID, customer.getCustomerID());

        response.sendRedirect(request.getContextPath() + "/customer/vehicle/list");
    }
}
