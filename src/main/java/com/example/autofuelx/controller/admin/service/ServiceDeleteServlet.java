package com.example.autofuelx.controller.admin.service;

import com.example.autofuelx.model.Employee;
import com.example.autofuelx.service.EmployeeService;
import com.example.autofuelx.service.ServiceManager;
import com.example.autofuelx.util.AuthUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/service/delete")
public class ServiceDeleteServlet extends HttpServlet {
    private ServiceManager serviceManager;

    @Override
    public void init() {
        serviceManager = new ServiceManager();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = AuthUtil.checkEmployeeLogin(req, resp, "Admin");
        if (employee == null) return;

        resp.sendRedirect(req.getContextPath() + "/admin/service/list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Employee employee = AuthUtil.checkEmployeeLogin(request, response, "Admin");
        if (employee == null) return;

        int id = Integer.parseInt(request.getParameter("id"));
        serviceManager.deleteService(id);
        response.sendRedirect(request.getContextPath() + "/admin/service/list");
    }
}