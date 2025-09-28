package com.example.autofuelx.controller.customer.vehicle;

import com.example.autofuelx.model.Customer;
import com.example.autofuelx.model.Vehicle;
import com.example.autofuelx.service.VehicleService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/customer/vehicle/list")
public class VehicleListServlet extends HttpServlet {
    private VehicleService vehicleService;

    @Override
    public void init() {
        vehicleService = new VehicleService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        if (customer == null) {
            response.sendRedirect(request.getContextPath() + "/views/customer/login.jsp");
            return;
        }

        List<Vehicle> vehicles = vehicleService.getVehiclesByCustomerID(customer.getCustomerID());
        request.setAttribute("vehicles", vehicles);
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.getType());
        }
        request.getRequestDispatcher("/views/customer/vehicle/list.jsp").forward(request, response);
    }
}
