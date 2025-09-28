package com.example.autofuelx.controller.customer.serviceBooking;

import com.example.autofuelx.model.Customer;
import com.example.autofuelx.model.Service;
import com.example.autofuelx.model.Vehicle;
import com.example.autofuelx.service.ServiceManager;
import com.example.autofuelx.service.VehicleService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/customer/service/booking-form")
public class ServiceBookingFormServlet extends HttpServlet {
    private VehicleService vehicleService;
    private ServiceManager serviceManager;

    @Override
    public void init() throws ServletException {
        vehicleService = new VehicleService();
        serviceManager = new ServiceManager();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Customer loggedCustomer = (Customer) session.getAttribute("customer");

        if (loggedCustomer == null) {
            response.sendRedirect(request.getContextPath() + "/views/customer/login.jsp");
            return;
        }

        List<Vehicle> vehicles = vehicleService.getVehiclesByCustomerID(loggedCustomer.getCustomerID());
        List<Service> services = serviceManager.getAllServices();

        request.setAttribute("vehicles", vehicles);
        request.setAttribute("services", services);

        request.getRequestDispatcher("/views/customer/service/booking-form.jsp").forward(request, response);
    }
}
