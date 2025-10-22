package com.example.autofuelx.controller.customer;


import com.example.autofuelx.model.Customer;
import com.example.autofuelx.service.CustomerPhoneNumberService;
import com.example.autofuelx.service.CustomerService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/customer/login")
public class CustomerLoginServlet extends HttpServlet {
    private CustomerService customerService;
    private CustomerPhoneNumberService customerPhoneNumberService;

    @Override
    public void init() throws ServletException {
        customerService = new CustomerService();
        customerPhoneNumberService = new CustomerPhoneNumberService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/views/customer/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Customer customer = customerService.loginCustomer(email, password);

        if (customer != null) {
            HttpSession session = request.getSession();
            session.setAttribute("customer", customer);

            List<String> phoneNumbers = customerPhoneNumberService.getPhoneNumbersByCustomer(customer.getCustomerID());
            session.setAttribute("phone-numbers", phoneNumbers);

            response.sendRedirect(request.getContextPath() + "/views/customer/dashboard.jsp"); // got to dashboard
        } else {
            // Login failed
            request.setAttribute("login-error-message", "Invalid email or password");
            request.getRequestDispatcher("/views/customer/login.jsp").forward(request, response);

        }
    }
}
