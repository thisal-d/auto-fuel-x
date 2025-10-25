package com.example.autofuelx.controller.customer.vehicle;

import com.example.autofuelx.model.Customer;
import com.example.autofuelx.model.Vehicle;
import com.example.autofuelx.service.VehicleService;
import com.example.autofuelx.util.AuthUtil;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Customer customer = AuthUtil.checkCustomerLogin(req, resp);
        if (customer == null) return;

        int vehicleID = Integer.parseInt(req.getParameter("vehicleID"));
        Vehicle vehicle = vehicleService.getVehicleByID(vehicleID);

        req.setAttribute("vehicle-update", vehicle);
        req.getRequestDispatcher("/views/customer/vehicle/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        Customer customer = AuthUtil.checkCustomerLogin(request, response);
        if (customer == null) return;

        int vehicleID = Integer.parseInt(request.getParameter("vehicleID"));
        Vehicle vehicle = vehicleService.getVehicleByID(vehicleID);

        request.setAttribute("vehicle-update", vehicle);
        request.getRequestDispatcher("/views/customer/vehicle/update.jsp").forward(request, response);
    }
}
