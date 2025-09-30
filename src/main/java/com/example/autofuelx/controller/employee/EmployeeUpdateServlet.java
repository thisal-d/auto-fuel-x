package com.example.autofuelx.controller.employee;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
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

@WebServlet("/employee/update")
public class EmployeeUpdateServlet extends HttpServlet {
    private EmployeeService employeeService;
    private EmployeePhoneNumberService phoneNumberService;

    @Override
    public void init() {
        employeeService = new EmployeeService();
        phoneNumberService = new EmployeePhoneNumberService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("employee");

        if (employee == null) {
            response.sendRedirect(request.getContextPath() + "/views/employee/login.jsp");
            return;
        }

        try {
            // --- Common Fields ---
            employee.setFirstName(request.getParameter("firstName"));
            employee.setLastName(request.getParameter("lastName"));
            employee.setDateOfBirth(Date.valueOf(request.getParameter("dateOfBirth")));
            employee.setEmail(request.getParameter("email"));
            employee.setStatus(request.getParameter("status"));
            employee.setHireDate(Date.valueOf(request.getParameter("hireDate")));
            employee.setSalary(Double.parseDouble(request.getParameter("salary")));
            employee.setAddressNo(request.getParameter("addressNo"));
            employee.setAddressLane(request.getParameter("addressLane"));
            employee.setAddressArea(request.getParameter("addressArea"));
            employee.setPassword(request.getParameter("password"));

            // --- Role-Specific ---
            String role = employee.getRole();
            if ("Service Center Staff".equals(role)) {
                employee.setSkillSet(request.getParameter("skillSet"));
            } else if ("Refuel Cashier".equals(role)) {
                employee.setShift(request.getParameter("shift"));
            } else if ("Admin".equals(role)) {
                employee.setRole(request.getParameter("adminRole"));
            }
            // Customer Care Officer → no extra fields

            // Update Employee in DB
            employeeService.updateEmployee(employee);

            // Update phone numbers in DB

            // Update session
            session.setAttribute("employee", employee);

            // Redirect back to profile page
            response.sendRedirect(request.getContextPath() + "/views/employee/profile.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error updating profile: " + e.getMessage());
            request.getRequestDispatcher("/views/employee/edit.jsp").forward(request, response);
        }
    }
}
