package com.example.autofuelx.controller.customer;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.autofuelx.model.Customer;
import com.example.autofuelx.service.CustomerService;


@WebServlet("/customer/register")
public class CustomerRegisterServlet extends HttpServlet {

    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        customerService = new CustomerService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get form data
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");       // NEW
        String password = request.getParameter("password");
        String addressNo = request.getParameter("addressNo");
        String addressLane = request.getParameter("addressLane");
        String addressArea = request.getParameter("addressArea");

        // Create Customer object
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);       // NEW
        customer.setPassword(password);
        customer.setAddressNo(addressNo);
        customer.setAddressLane(addressLane);
        customer.setAddressArea(addressArea);

        // Call service to save
        boolean isRegistered = customerService.registerCustomer(customer);

        if (isRegistered) {
            response.sendRedirect(request.getContextPath() + "/views/customer/registration-success.jsp");
        } else {
            request.setAttribute("register-error-message", "Registration failed !");
            request.getRequestDispatcher("/views/customer/register.jsp").forward(request, response);
        }
    }
}
