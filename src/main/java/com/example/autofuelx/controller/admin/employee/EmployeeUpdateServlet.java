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

@WebServlet("/admin/employee/update")
public class EmployeeUpdateServlet extends HttpServlet {
    private EmployeeService employeeService;

    @Override
    public void init() {
        employeeService = new EmployeeService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int employeeID = Integer.parseInt(request.getParameter("employeeID"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        Date dateOfBirth = Date.valueOf(request.getParameter("dateOfBirth"));
        double salary = Double.parseDouble(request.getParameter("salary"));
        String status = request.getParameter("status");
        Date hireDate = Date.valueOf(request.getParameter("hireDate"));
        String addressNo = request.getParameter("addressNo");
        String addressLane = request.getParameter("addressLane");
        String addressArea = request.getParameter("addressArea");
        String email = request.getParameter("email");
        String skillSet = request.getParameter("skillSet");
        String role = request.getParameter("role");
        String shift = request.getParameter("shift");
        String type = request.getParameter("type");

        Employee employee = new Employee();
        employee.setEmployeeID(employeeID);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setDateOfBirth(dateOfBirth);
        employee.setSalary(salary);
        employee.setStatus(status);
        employee.setHireDate(hireDate);
        employee.setAddressNo(addressNo);
        employee.setAddressLane(addressLane);
        employee.setAddressArea(addressArea);
        employee.setEmail(email);
        employee.setSkillSet(skillSet);
        employee.setRole(role);
        employee.setShift(shift);
        employee.setType(type);

        employeeService.updateEmployee(employee);
        response.sendRedirect(request.getContextPath() + "/employee/list");
    }
}