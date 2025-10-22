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

@WebServlet("/admin/employee/delete")
public class EmployeeDeleteServlet extends HttpServlet {
    private EmployeeService employeeService;

    @Override
    public void init() {
        employeeService = new EmployeeService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = AuthUtil.checkEmployeeLogin(req, resp, "Admin");
        if (employee == null) return;

        resp.sendRedirect(req.getContextPath() + "/admin/employee/list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Employee employee = AuthUtil.checkEmployeeLogin(request, response, "Admin");
        if (employee == null) return;

        int id = Integer.parseInt(request.getParameter("employee-ID"));
        String redirectUrl = request.getParameter("redirect-url");
        employeeService.deleteEmployee(id);

        response.sendRedirect(request.getContextPath() + redirectUrl);
    }
}