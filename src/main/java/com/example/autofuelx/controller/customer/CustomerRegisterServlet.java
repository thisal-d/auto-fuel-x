package com.example.autofuelx.controller.customer;

import com.example.autofuelx.model.Customer;
import com.example.autofuelx.service.CustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/customer/register")
public class CustomerRegisterServlet extends HttpServlet {

    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        customerService = new CustomerService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/views/customer/register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get form data
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String addressNo = request.getParameter("addressNo");
        String addressLane = request.getParameter("addressLane");
        String addressArea = request.getParameter("addressArea");

        Customer customer = customerService.getCustomerByEmail(email);

        if (customer != null) {
            request.setAttribute("register-error-message", "Email Already in use..!");
            request.getRequestDispatcher("/views/customer/register.jsp").forward(request, response);
            return;
        }

        // create Customer object
        Customer newCustomer = new Customer();
        newCustomer.setFirstName(firstName);
        newCustomer.setLastName(lastName);
        newCustomer.setEmail(email);
        newCustomer.setPassword(password);
        newCustomer.setAddressNo(addressNo);
        newCustomer.setAddressLane(addressLane);
        newCustomer.setAddressArea(addressArea);

        // register customer
        boolean isRegistered = customerService.registerCustomer(newCustomer);

        if (isRegistered) {
            response.sendRedirect(request.getContextPath() + "/views/customer/registration-success.jsp");
        } else {
            request.setAttribute("register-error-message", "Registration failed !");
            request.getRequestDispatcher("/views/customer/register.jsp").forward(request, response);
        }
    }
}
