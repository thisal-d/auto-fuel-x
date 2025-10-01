package com.example.autofuelx.controller.admin.employee;

import com.example.autofuelx.model.Employee;
import com.example.autofuelx.service.EmployeeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/admin/employee/status/update")
public class EmployeeStatusUpdateServlet extends HttpServlet {
    private EmployeeService employeeService;

    @Override
    public void init() {
        employeeService = new EmployeeService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int employeeID = Integer.parseInt(request.getParameter("employee-ID"));
        String status = request.getParameter("status");
        String redirectUrl = request.getParameter("redirect-url");

        employeeService.updateEmployeeStatus(employeeID,status);

        response.sendRedirect(request.getContextPath() + redirectUrl);
    }
}