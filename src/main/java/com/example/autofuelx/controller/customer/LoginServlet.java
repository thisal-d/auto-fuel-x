package com.example.autofuelx.controller.customer;


import com.example.autofuelx.model.Customer;
import com.example.autofuelx.service.CustomerService;

import com.example.autofuelx.service.VehicleService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/customer/login")
public class LoginServlet extends HttpServlet {
    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        customerService = new CustomerService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Customer customer = customerService.loginCustomer(email, password);

        if (customer != null) {
            // Login success
            // Create session
            HttpSession session = request.getSession();
            session.setAttribute("customer", customer);

            response.sendRedirect(request.getContextPath() + "/views/customer/profile.jsp"); // Redirect to welcome page
        } else {
            // Login failed
            request.setAttribute("login-error-message", "Invalid email or password");
            request.getRequestDispatcher("/views/customer/login.jsp").forward(request, response);

        }
    }
}
