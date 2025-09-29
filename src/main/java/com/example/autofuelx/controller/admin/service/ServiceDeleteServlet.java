package com.example.autofuelx.controller.admin.service;

import com.example.autofuelx.service.EmployeeService;
import com.example.autofuelx.service.ServiceManager;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        serviceManager.deleteService(id);
        response.sendRedirect(request.getContextPath() + "/admin/service/list");
    }
}