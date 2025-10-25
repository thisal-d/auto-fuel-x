package com.example.autofuelx.controller.admin.employee;

import com.example.autofuelx.model.Employee;
import com.example.autofuelx.service.EmployeeService;
import com.example.autofuelx.util.AuthUtil;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employeeUpdate = AuthUtil.checkEmployeeLogin(req, resp, "Admin");
        if (employeeUpdate == null) return;

        resp.sendRedirect(req.getContextPath() + "/admin/employee/list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Employee employee = AuthUtil.checkEmployeeLogin(request, response, "Admin");
        if (employee == null) return;

        int employeeID = Integer.parseInt(request.getParameter("employeeID"));
        Employee employeeUpdate = employeeService.getEmployeeById(employeeID);

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

        employeeUpdate.setEmployeeID(employeeID);
        employeeUpdate.setFirstName(firstName);
        employeeUpdate.setLastName(lastName);
        employeeUpdate.setDateOfBirth(dateOfBirth);
        employeeUpdate.setSalary(salary);
        employeeUpdate.setStatus(status);
        employeeUpdate.setHireDate(hireDate);
        employeeUpdate.setAddressNo(addressNo);
        employeeUpdate.setAddressLane(addressLane);
        employeeUpdate.setAddressArea(addressArea);
        employeeUpdate.setEmail(email);
        employeeUpdate.setType(type);

        if (type.equals("Refuel Cashier")) {
            employeeUpdate.setShift(shift);
        } else if (type.equals("Admin")) {
            employeeUpdate.setRole(role);
        } else if (type.equals("Service Center Staff")) {
            employeeUpdate.setSkillSet(skillSet);
        }

        employeeService.updateEmployee(employeeUpdate);
        response.sendRedirect(request.getContextPath() + "/admin/employee/edit-form?employee-ID=" + employeeID);
    }
}