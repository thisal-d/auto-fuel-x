package com.example.autofuelx.controller.admin.employee.phoneNumber;

import com.example.autofuelx.model.Employee;
import com.example.autofuelx.service.EmployeePhoneNumberService;
import com.example.autofuelx.util.AuthUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/employee/phone-number/delete")
public class AdminEmployeePhoneNumberDeleteServlet extends HttpServlet {
    private EmployeePhoneNumberService employeePhoneNumberService;

    @Override
    public void init() throws ServletException {
        employeePhoneNumberService = new EmployeePhoneNumberService();
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

        int employeeID = Integer.parseInt(request.getParameter("employee-ID"));
        String phoneNumber = request.getParameter("phone-number");

        employeePhoneNumberService.deletePhoneNumber(employeeID, phoneNumber);

        response.sendRedirect(request.getContextPath() + "/admin/employee/edit-form?employee-ID=" + employeeID);
    }
}
