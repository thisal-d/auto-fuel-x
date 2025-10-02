package com.example.autofuelx.util;


import com.example.autofuelx.model.Customer;
import com.example.autofuelx.model.Employee;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class AuthUtil {

    // returns logged in customer or redirects if not logged in
    public static Customer checkCustomerLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();

        Customer customer = (session != null) ? (Customer) session.getAttribute("customer") : null;

        if (customer == null) {
            response.sendRedirect(request.getContextPath() + "/views/customer/login.jsp");
            return null;
        }
        return customer;

    }

    // returns logged in employee or redirects if not logged in
    public static Employee checkEmployeeLogin(HttpServletRequest request, HttpServletResponse response, String employeeType) throws IOException {
        HttpSession session = request.getSession();
        Employee employee = (session != null) ? (Employee) session.getAttribute("employee") : null;

        if (employee == null) {
            response.sendRedirect(request.getContextPath() + "/views/employee/login.jsp");
            return null;
        }

        if (!employeeType.equals(employee.getType())) {
            response.sendRedirect(request.getContextPath() + "/views/employee/login.jsp");
            return null;
        }
        return employee;
    }

    // returns logged in employee or redirects if not logged in
    public static Employee checkEmployeeLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Employee employee = (session != null) ? (Employee) session.getAttribute("employee") : null;

        if (employee == null) {
            response.sendRedirect(request.getContextPath() + "/views/employee/login.jsp");
            return null;
        }
        return employee;
    }
}
