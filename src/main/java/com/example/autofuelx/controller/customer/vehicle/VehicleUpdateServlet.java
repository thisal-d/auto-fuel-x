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
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/customer/vehicle/update")
public class VehicleUpdateServlet extends HttpServlet {
    private VehicleService vehicleService;

    @Override
    public void init() {
        vehicleService = new VehicleService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer = AuthUtil.checkCustomerLogin(req, resp);
        if (customer == null) return;
        
        resp.sendRedirect(req.getContextPath() + "/customer/vehicle/list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Customer customer = AuthUtil.checkCustomerLogin(request, response);
        if (customer == null) return;

        try {
            int vehicleID = Integer.parseInt(request.getParameter("vehicleID"));
            String plateNumber = request.getParameter("plateNumber");
            String type = request.getParameter("type");
            String model = request.getParameter("model");
            String color = request.getParameter("color");
            Date registrationDate = new SimpleDateFormat("yyyy-MM-dd")
                    .parse(request.getParameter("registrationDate"));

            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleID(vehicleID);
            vehicle.setPlateNumber(plateNumber);
            vehicle.setType(type);
            vehicle.setModel(model);
            vehicle.setColor(color);
            vehicle.setCustomerID(customer.getCustomerID());
            vehicle.setRegistrationDate(registrationDate);

            vehicleService.updateVehicle(vehicle);

        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/customer/vehicle/list");
    }
}
