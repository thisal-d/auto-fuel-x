package com.example.autofuelx.controller.customer.phoneNumber;

import com.example.autofuelx.model.Customer;
import com.example.autofuelx.service.CustomerPhoneNumberService;
import com.example.autofuelx.util.AuthUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/customer/phone-number/delete")
public class CustomerPhoneNumberDeleteServlet extends HttpServlet {
    private CustomerPhoneNumberService customerPhoneNumberService;

    @Override
    public void init() throws ServletException {
        customerPhoneNumberService = new CustomerPhoneNumberService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer = AuthUtil.checkCustomerLogin(req, resp);
        if (customer == null) return;

        resp.sendRedirect(req.getContextPath() + "/customer/views/profile.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Customer customer = AuthUtil.checkCustomerLogin(request, response);
        if (customer == null) return;

        int customerID = Integer.parseInt(request.getParameter("customerID"));
        String phoneNumber = request.getParameter("phoneNumber");

        boolean success = customerPhoneNumberService.deletePhoneNumber(customerID, phoneNumber);

        if (success) {
            request.getSession().setAttribute("message", "Phone number deleted successfully.");
        } else {
            request.getSession().setAttribute("error", "Failed to delete phone number.");
        }

        HttpSession session = request.getSession();
        List<String> phoneNumbers = customerPhoneNumberService.getPhoneNumbersByCustomer(customer.getCustomerID());
        session.setAttribute("phone-numbers", phoneNumbers);
        response.sendRedirect(request.getContextPath() + "/views/customer/update.jsp");
    }
}
