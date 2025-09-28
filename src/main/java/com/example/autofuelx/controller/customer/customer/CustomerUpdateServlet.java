package com.example.autofuelx.controller.customer.customer;

import com.example.autofuelx.model.Customer;
import com.example.autofuelx.service.CustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/customer/customer/update")
public class CustomerUpdateServlet extends HttpServlet {
    private CustomerService CustomerService;

    @Override
    public void init() {
        CustomerService = new CustomerService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // gET LOGGED IN Customer
        Customer customer = (Customer) session.getAttribute("customer");

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String addressNo = request.getParameter("addressNo");
        String addressLane = request.getParameter("addressLane");
        String addressArea = request.getParameter("addressArea");

        // Create Customer object
        customer.setCustomerID(customer.getCustomerID());
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setAddressNo(addressNo);
        customer.setAddressLane(addressLane);
        customer.setAddressArea(addressArea);

        // Update customer details in DB
        CustomerService.updateCustomer(customer);

        session.setAttribute("customer", customer);
        response.sendRedirect(request.getContextPath() + "/views/customer/profile.jsp");
    }
}