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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer = AuthUtil.checkCustomerLogin(req, resp);
        if (customer == null) return;

        resp.sendRedirect(req.getContextPath() + "/views/customer/vehicle/add.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Customer customer = AuthUtil.checkCustomerLogin(request, response);
        if (customer == null) return;

        String plateNumber = request.getParameter("plateNumber");
        // check vehicle already exist or not

        Vehicle findVehicle = vehicleService.getVehicleByPlateNo(plateNumber);
        if (findVehicle != null) {
            request.setAttribute("errorMessage", "Vehicle already exists..!");
            request.getRequestDispatcher("/views/customer/vehicle/add.jsp").forward(request, response); // Redirect to vehicle add page
            return;
        }

        try {
            String type = request.getParameter("type");
            String model = request.getParameter("model");
            String color = request.getParameter("color");
            Date registrationDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("registrationDate"));

            Vehicle vehicle = new Vehicle();
            vehicle.setPlateNumber(plateNumber);
            vehicle.setType(type);
            vehicle.setModel(model);
            vehicle.setColor(color);
            vehicle.setCustomerID(customer.getCustomerID());
            vehicle.setRegistrationDate(registrationDate);
            vehicleService.addVehicle(vehicle);

        } catch (Exception e) {
            request.setAttribute("errorMessage", "Something Went Wrong..!");
            response.sendRedirect(request.getContextPath() + "/views/customer/vehicle/add.jsp"); // Redirect to vehicle add page
        }
        response.sendRedirect(request.getContextPath() + "/customer/vehicle/list");
    }
}
