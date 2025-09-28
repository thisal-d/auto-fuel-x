package com.example.autofuelx.controller.admin.customer;

import com.example.autofuelx.model.Customer;
import com.example.autofuelx.service.CustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/customer/update")
public class CustomerUpdateServlet extends HttpServlet {
    private CustomerService customerService;

    @Override
    public void init() {
        customerService = new CustomerService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int customerID = Integer.parseInt(request.getParameter("customerID"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String addressNo = request.getParameter("addressNo");
        String addressLane = request.getParameter("addressLane");
        String addressArea = request.getParameter("addressArea");

        // Create Customer object
        Customer customer = new Customer();
        customer.setCustomerID(customerID);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setAddressNo(addressNo);
        customer.setAddressLane(addressLane);
        customer.setAddressArea(addressArea);

        // Update via service
        customerService.updateCustomer(customer);

        // Redirect to customer list page
        response.sendRedirect(request.getContextPath() + "/customer/list");
    }
}
