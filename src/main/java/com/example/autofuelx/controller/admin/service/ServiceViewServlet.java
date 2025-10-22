package com.example.autofuelx.controller.admin.service;
import com.example.autofuelx.model.Employee;
import com.example.autofuelx.model.Service;
import com.example.autofuelx.service.ServiceManager;
import com.example.autofuelx.util.AuthUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/service/view")
public class ServiceViewServlet extends HttpServlet {
    private ServiceManager serviceManager;

    @Override
    public void init() {
        serviceManager = new ServiceManager();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Employee employee = AuthUtil.checkEmployeeLogin(request, response, "Admin");
        if (employee == null) return;
        
        int id = Integer.parseInt(request.getParameter("id"));
        Service service = serviceManager.getServiceByID(id);
        request.setAttribute("service", service);
        request.getRequestDispatcher("/views/admin/service/view.jsp").forward(request, response);
    }
}