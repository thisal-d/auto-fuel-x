package com.example.autofuelx.controller.employee;

import com.example.autofuelx.model.Employee;
import com.example.autofuelx.service.EmployeeService;
import com.example.autofuelx.util.AuthUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/employee/update")
public class EmployeeUpdateServlet extends HttpServlet {
    private EmployeeService employeeService;

    @Override
    public void init() {
        employeeService = new EmployeeService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = AuthUtil.checkEmployeeLogin(req, resp);
        if (employee == null) return;
        resp.sendRedirect(req.getContextPath() + "/views/employee/edit.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Employee employee = AuthUtil.checkEmployeeLogin(request, response);
        if (employee == null) return;

        try {
            // get data from request
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

            // check employee type and get type specific attributes
            String role = employee.getRole();
            if ("Service Center Staff".equals(role)) {
                employee.setSkillSet(request.getParameter("skillSet"));
            } else if ("Refuel Cashier".equals(role)) {
                employee.setShift(request.getParameter("shift"));
            } else if ("Admin".equals(role)) {
                employee.setRole(request.getParameter("adminRole"));
            }

            // update Employee in Database
            employeeService.updateEmployee(employee);

            // update session
            HttpSession session = request.getSession();
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
