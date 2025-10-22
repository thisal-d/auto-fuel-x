package com.example.autofuelx.controller.admin.employee;

import com.example.autofuelx.model.Employee;
import com.example.autofuelx.service.EmployeePhoneNumberService;
import com.example.autofuelx.service.EmployeeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/employee/edit-form")
public class EmployeeUpdateFormServlet extends HttpServlet {
    private EmployeeService employeeService;
    private EmployeePhoneNumberService employeePhoneNumberService;

    @Override
    public void init() {
        employeeService = new EmployeeService();
        employeePhoneNumberService = new EmployeePhoneNumberService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int employeeID = Integer.parseInt(request.getParameter("employee-ID"));
        Employee employee = employeeService.getEmployeeById(employeeID);

        List<String> phoneNumbers = employeePhoneNumberService.getPhoneNumbersByEmployee(employeeID);
        request.setAttribute("employee", employee);
        request.setAttribute("employee-phone-numbers", phoneNumbers);


        request.getRequestDispatcher("/views/admin/employee/update.jsp").forward(request, response);
    }
}