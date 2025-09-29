package com.example.autofuelx.controller.admin.customer;

import com.example.autofuelx.model.Customer;
import com.example.autofuelx.model.Employee;
import com.example.autofuelx.service.CustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/customer/list")
public class CustomerListServlet extends HttpServlet {
    private CustomerService customerService;

    @Override
    public void init() {
        customerService = new CustomerService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Customer> customers = customerService.getCustomers();
        request.setAttribute("customers", customers);

        request.getRequestDispatcher("/views/admin/customer/list-all.jsp").forward(request, response);
    }
}