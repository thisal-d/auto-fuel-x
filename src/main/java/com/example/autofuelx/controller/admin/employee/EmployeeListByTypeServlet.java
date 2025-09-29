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
import java.util.List;

@WebServlet("/admin/employee/list-by-type")
public class EmployeeListByTypeServlet extends HttpServlet {
    private EmployeeService employeeService;

    public void init() {
        employeeService = new EmployeeService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get filter parameters from request
        String type = request.getParameter("type");
        String status = request.getParameter("status");
        String hireDateFromStr = request.getParameter("hireDateFrom");
        String hireDateToStr = request.getParameter("hireDateTo");
        String minSalaryStr = request.getParameter("minSalary");
        String maxSalaryStr = request.getParameter("maxSalary");
        String name = request.getParameter("name");
        String ageGroup = request.getParameter("ageGroup");

        // Convert parameters
        Date hireDateFrom = null;
        Date hireDateTo = null;
        Double minSalary = null;
        Double maxSalary = null;

        String redirectUrl = "";
        String employeeType = "";

        if (type.equals("customer-care-officer")){
            redirectUrl = "/views/admin/employee/list-customer-care-officer.jsp";
            employeeType = "Customer Care Officer";
        } else if(type.equals("refuel-cashier")){
            redirectUrl = "/views/admin/employee/list-refuel-cashier.jsp";
            employeeType = "Refuel Cashier";
        } else if (type.equals("admin")){
            redirectUrl = "/views/admin/employee/list-admin.jsp";
            employeeType = "Admin";
        } else if (type.equals("service-center-staff")){
            redirectUrl = "/views/admin/employee/list-service-center-staff.jsp";
            employeeType = "Service Center Staff";
        }

        try {
            if (hireDateFromStr != null && !hireDateFromStr.isEmpty()) {
                hireDateFrom = Date.valueOf(hireDateFromStr);
            }
            if (hireDateToStr != null && !hireDateToStr.isEmpty()) {
                hireDateTo = Date.valueOf(hireDateToStr);
            }
            if (minSalaryStr != null && !minSalaryStr.isEmpty()) {
                minSalary = Double.parseDouble(minSalaryStr);
            }
            if (maxSalaryStr != null && !maxSalaryStr.isEmpty()) {
                maxSalary = Double.parseDouble(maxSalaryStr);
            }
        } catch (Exception e) {
            // Handle parsing errors
            e.printStackTrace();
        }

        // Get filtered employees
        List<Employee> employees = employeeService.getFilteredEmployees(
                employeeType, status, hireDateFrom, hireDateTo,
                minSalary, maxSalary, name, ageGroup
        );

        // Set attributes for JSP
        request.setAttribute("employees", employees);
        request.setAttribute("type", type);
        request.setAttribute("status", status);
        request.setAttribute("hireDateFrom", hireDateFromStr);
        request.setAttribute("hireDateTo", hireDateToStr);
        request.setAttribute("minSalary", minSalaryStr);
        request.setAttribute("maxSalary", maxSalaryStr);
        request.setAttribute("name", name);
        request.setAttribute("ageGroup", ageGroup);

        // Forward to JSP
        request.getRequestDispatcher(redirectUrl).forward(request, response);
    }
}