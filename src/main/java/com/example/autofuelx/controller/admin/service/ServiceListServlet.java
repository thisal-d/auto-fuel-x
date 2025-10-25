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
import java.util.List;

@WebServlet("/admin/service/list")
public class ServiceListServlet extends HttpServlet {
    private ServiceManager serviceManager;

    @Override
    public void init() throws ServletException {
        serviceManager = new ServiceManager();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Employee employee = AuthUtil.checkEmployeeLogin(request, response, "Admin");
        if (employee == null) return;

        // Get filter parameters
        String minCostParam = request.getParameter("minCost");
        String maxCostParam = request.getParameter("maxCost");
        String keyword = request.getParameter("keyword");

        // Parse cost parameters
        Double minCost = null;
        Double maxCost = null;

        if (minCostParam != null && !minCostParam.isEmpty()) {
            minCost = Double.parseDouble(minCostParam);
        }
        if (maxCostParam != null && !maxCostParam.isEmpty()) {
            maxCost = Double.parseDouble(maxCostParam);
        }


        // Get filtered services
        List<Service> services = serviceManager.getFilteredServices(minCost, maxCost, keyword);

        // Set attributes
        request.setAttribute("services", services);

        // Forward to JSP
        request.getRequestDispatcher("/views/admin/service/list-all.jsp").forward(request, response);
    }
}