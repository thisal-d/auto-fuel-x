package com.example.autofuelx.controller.employee.phoneNumber;

import com.example.autofuelx.model.Employee;
import com.example.autofuelx.service.EmployeePhoneNumberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/employee/phone-number/add")
public class EmployeePhoneNumberAddServlet extends HttpServlet {
    private EmployeePhoneNumberService employeePhoneNumberService;

    @Override
    public void init() throws ServletException {
        employeePhoneNumberService = new EmployeePhoneNumberService();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("employee");
        int employeeID = employee.getEmployeeID();

        String phoneNumber = request.getParameter("phone-number");

        boolean success = employeePhoneNumberService.addPhoneNumber(employeeID, phoneNumber);

        if (success) {
            request.getSession().setAttribute("message", "Phone number deleted successfully.");
        } else {
            request.getSession().setAttribute("error", "Failed to delete phone number.");
        }

        List<String> phoneNumbers = employeePhoneNumberService.getPhoneNumbersByEmployee(employee.getEmployeeID());
        session.setAttribute("phone-numbers", phoneNumbers);
        response.sendRedirect(request.getContextPath() + "/views/employee/update.jsp");
    }
}
