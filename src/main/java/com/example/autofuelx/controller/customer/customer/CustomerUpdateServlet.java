package com.example.autofuelx.controller.customer.customer;

import com.example.autofuelx.model.Customer;
import com.example.autofuelx.service.CustomerService;
import com.example.autofuelx.util.AuthUtil;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer = AuthUtil.checkCustomerLogin(req, resp);
        if (customer == null) return;
        resp.sendRedirect(req.getContextPath() + "/views/customer/update.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Customer customer = AuthUtil.checkCustomerLogin(request, response);
        if (customer == null) return;

        // catch data
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String addressNo = request.getParameter("addressNo");
        String addressLane = request.getParameter("addressLane");
        String addressArea = request.getParameter("addressArea");

        // create Customer object
        customer.setCustomerID(customer.getCustomerID());
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setAddressNo(addressNo);
        customer.setAddressLane(addressLane);
        customer.setAddressArea(addressArea);

        // update customer details in DB
        CustomerService.updateCustomer(customer);

        HttpSession session = request.getSession();
        session.setAttribute("customer", customer);
        response.sendRedirect(request.getContextPath() + "/views/customer/profile.jsp");
    }
}