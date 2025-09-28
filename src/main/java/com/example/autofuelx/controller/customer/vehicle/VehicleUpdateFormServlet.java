package com.example.autofuelx.controller.customer.vehicle;

import com.example.autofuelx.model.Vehicle;
import com.example.autofuelx.service.VehicleService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/customer/vehicle/update-form")
public class VehicleUpdateFormServlet extends HttpServlet {
    private VehicleService vehicleService;

    @Override
    public void init() {
        vehicleService = new VehicleService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        int vehicleID = Integer.parseInt(request.getParameter("vehicleID"));
        Vehicle vehicle = vehicleService.getVehicleByID(vehicleID);

        request.setAttribute("vehicle-update", vehicle);
        request.getRequestDispatcher("/views/customer/vehicle/update.jsp").forward(request, response);
    }
}
