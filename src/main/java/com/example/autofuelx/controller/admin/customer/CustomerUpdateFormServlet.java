package com.example.autofuelx.controller.admin.customer;

import com.example.autofuelx.model.Customer;
import com.example.autofuelx.service.CustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/customer/edit-form")
public class CustomerUpdateFormServlet extends HttpServlet {
    private CustomerService CustomerService;

    @Override
    public void init() {
        CustomerService = new CustomerService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = CustomerService.getCustomerById(id);
        request.setAttribute("customer-update", customer);
        request.getRequestDispatcher("/views/admin/customer/update.jsp").forward(request, response);
    }
}