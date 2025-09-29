package com.example.autofuelx.controller.admin.service;

import com.example.autofuelx.model.Service;
import com.example.autofuelx.service.ServiceManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/service/add")
public class ServiceAddServlet extends HttpServlet {
    private ServiceManager serviceManager;

    @Override
    public void init() {
        serviceManager = new ServiceManager();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String type = request.getParameter("type");
        String description = request.getParameter("description");
        double cost = Double.parseDouble(request.getParameter("cost"));


        Service service = new Service();
        service.setType(type);
        service.setDescription(description);
        service.setCost(cost);

        serviceManager.addService(service);

        response.sendRedirect(request.getContextPath() + "/admin/service/list");
    }
}