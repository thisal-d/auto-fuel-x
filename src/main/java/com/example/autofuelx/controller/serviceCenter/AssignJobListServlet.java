package com.example.autofuelx.controller.serviceCenter;

import com.example.autofuelx.dto.ServiceBookingDTO;
import com.example.autofuelx.model.Employee;
import com.example.autofuelx.service.ServiceBookingService;
import com.example.autofuelx.util.AuthUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/service-center/service-booking/assigned")
public class AssignJobListServlet extends HttpServlet {
    ServiceBookingService serviceBookingService;

    @Override
    public void init() throws ServletException {
        serviceBookingService = new ServiceBookingService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Employee employee = AuthUtil.checkEmployeeLogin(request, response, "Service Center Staff");
        if (employee == null) return;

        List<ServiceBookingDTO> assignedServiceBookingDTOS = serviceBookingService.getActiveBookingByEmployee(employee.getEmployeeID());

        request.setAttribute("assignedServiceBookingDTOS", assignedServiceBookingDTOS);

        request.getRequestDispatcher("/views/service-center/service-booking/list.jsp").forward(request, response);

    }

}
