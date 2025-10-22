package com.example.autofuelx.controller.admin.employeeNew;

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

@WebServlet("/admin/employeeNew/add")
public class EmployeeAddServlet extends HttpServlet {
    private EmployeeService employeeService;

    @Override
    public void init() {
        employeeService = new EmployeeService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employeeNew = AuthUtil.checkEmployeeLogin(req, resp, "Admin");
        if (employeeNew == null) return;
        resp.sendRedirect(req.getContextPath() + "/admin/employeeNew/list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Employee employee = AuthUtil.checkEmployeeLogin(request, response, "Admin");
        if (employee == null) return;

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
        String password = request.getParameter("password");

        Employee employeeNew = new Employee();
        employeeNew.setFirstName(firstName);
        employeeNew.setLastName(lastName);
        employeeNew.setDateOfBirth(dateOfBirth);
        employeeNew.setSalary(salary);
        employeeNew.setStatus(status);
        employeeNew.setHireDate(hireDate);
        employeeNew.setAddressNo(addressNo);
        employeeNew.setAddressLane(addressLane);
        employeeNew.setAddressArea(addressArea);
        employeeNew.setType(type);
        employeeNew.setEmail(email);
        employeeNew.setPassword(password);

        if (type.equals("Refuel Cashier")){
            employeeNew.setShift(shift);
        }
        else if (type.equals("Admin")){
            employeeNew.setRole(role);
        }
        else if (type.equals("Service Center Staff")){
            employeeNew.setSkillSet(skillSet);
        }

        employeeService.addEmployee(employeeNew);
        response.sendRedirect(request.getContextPath() + "/admin/employeeNew/list");
    }
}