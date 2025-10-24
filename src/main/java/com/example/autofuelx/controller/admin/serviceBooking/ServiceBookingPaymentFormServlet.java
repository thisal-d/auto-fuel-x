package com.example.autofuelx.controller.admin.serviceBooking;


import com.example.autofuelx.dto.ServiceBookingDTO;
import com.example.autofuelx.model.Employee;
import com.example.autofuelx.model.ServiceBooking;
import com.example.autofuelx.service.ServiceBookingService;
import com.example.autofuelx.util.AuthUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/service-booking/payment-form")
public class ServiceBookingPaymentFormServlet extends HttpServlet {
    private ServiceBookingService serviceBookingService;

    @Override
    public void init() throws ServletException {
        serviceBookingService = new ServiceBookingService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = AuthUtil.checkEmployeeLogin(req, resp, "Admin");
        if (employee == null) return;

        resp.sendRedirect(req.getContextPath() + "/admin/service-booking/view");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Employee employee = AuthUtil.checkEmployeeLogin(request, response, "Admin");
        if (employee == null) return;

        // get parameters
        int bookingId = Integer.parseInt(request.getParameter("bookingId"));
        ServiceBookingDTO booking = serviceBookingService.getBookingDTOByID(bookingId);

        request.setAttribute("booking", booking);

        request.getRequestDispatcher("/views/admin/payment/form.jsp").forward(request, response);
    }
}