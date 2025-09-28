package com.example.autofuelx.controller.customer.vehicle;

import com.example.autofuelx.model.Customer;
import com.example.autofuelx.model.Vehicle;
import com.example.autofuelx.service.VehicleService;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/customer/vehicle/add")
public class VehicleAddServlet extends HttpServlet {
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

        try {
            String plateNumber = request.getParameter("plateNumber");
            String type = request.getParameter("type");
            String model = request.getParameter("model");
            String color = request.getParameter("color");
            Date registrationDate = new SimpleDateFormat("yyyy-MM-dd")
                    .parse(request.getParameter("registrationDate"));

            Vehicle vehicle = new Vehicle();
            vehicle.setPlateNumber(plateNumber);
            vehicle.setType(type);
            vehicle.setModel(model);
            vehicle.setColor(color);
            vehicle.setCustomerID(customer.getCustomerID());
            vehicle.setRegistrationDate(registrationDate);

            vehicleService.addVehicle(vehicle);

        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/customer/vehicle/list");
    }
}
