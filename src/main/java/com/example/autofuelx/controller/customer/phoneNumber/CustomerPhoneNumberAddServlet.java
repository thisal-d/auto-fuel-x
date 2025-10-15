package com.example.autofuelx.controller.customer.phoneNumber;

import com.example.autofuelx.model.Customer;
import com.example.autofuelx.service.CustomerPhoneNumberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/customer/phone-number/add")
public class CustomerPhoneNumberAddServlet extends HttpServlet {
    private CustomerPhoneNumberService customerPhoneNumberService;

    @Override
    public void init() throws ServletException {
        customerPhoneNumberService = new CustomerPhoneNumberService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int customerID = Integer.parseInt(request.getParameter("customerID"));
        String phoneNumber = request.getParameter("phoneNumber");

        boolean success = customerPhoneNumberService.addPhoneNumber(customerID, phoneNumber);

        if (success) {
            request.getSession().setAttribute("message", "Phone number added successfully.");
        } else {
            request.getSession().setAttribute("error", "Failed to add phone number.");
        }
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        List<String> phoneNumbers = customerPhoneNumberService.getPhoneNumbersByCustomer(customer.getCustomerID());
        session.setAttribute("phone-numbers", phoneNumbers);

        response.sendRedirect(request.getContextPath() + "/views/customer/update.jsp");
    }
}
