package com.example.autofuelx.controller.employee;

import java.io.IOException;
import java.util.List;

import com.example.autofuelx.model.Employee;
import com.example.autofuelx.service.EmployeePhoneNumberService;
import com.example.autofuelx.service.EmployeeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/employee/login")
public class EmployeeLoginServlet extends HttpServlet {
    private EmployeeService employeeService;
    private EmployeePhoneNumberService employeePhoneNumberService;

    public void init() {
        employeePhoneNumberService = new EmployeePhoneNumberService();
        employeeService = new EmployeeService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String type = request.getParameter("type");

        System.out.println(email);
        System.out.println(password);
        System.out.println(type);

        HttpSession session = request.getSession();

        Employee employee = employeeService.validateEmployee(email, password, type);

        String redirectUrl = "";
        if ("Customer Care Officer".equals(type)) redirectUrl = "/customer-care/complaint/list";
        else if ("Admin".equals(type)) redirectUrl = "/admin/service-booking/dashboard";
        else if ("Refuel Cashier".equals(type)) redirectUrl = "/refuel-cashier/fuel/refuel-form";
        else if ("Service Center Staff".equals(type)) redirectUrl = "/service-center/service-booking/assigned";

        if (employee != null) {
            System.out.println("Logged : " + employee.getRole());
            session.setAttribute("type", type);
            List<String> phoneNumbers = employeePhoneNumberService.getPhoneNumbersByEmployee(employee.getEmployeeID());
            session.setAttribute("phone-numbers", phoneNumbers);

            session.setAttribute("employee", employee);
            response.sendRedirect( request.getContextPath() + redirectUrl);
        } else {
            // Login failed
            request.setAttribute("error-message", "Invalid email or password");
            request.getRequestDispatcher( "/views/employee/login.jsp").forward(request, response);
        }
    }
}