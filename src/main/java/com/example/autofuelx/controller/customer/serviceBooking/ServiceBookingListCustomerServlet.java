package com.example.autofuelx.controller.customer.serviceBooking;

import com.example.autofuelx.dto.ServiceBookingDTO;
import com.example.autofuelx.model.Customer;
import com.example.autofuelx.service.ServiceBookingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/customer/service-booking/list")
public class ServiceBookingListCustomerServlet extends HttpServlet {
    private ServiceBookingService serviceBookingService;

    @Override
    public void init() throws ServletException {
        serviceBookingService = new ServiceBookingService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String status = request.getParameter("status");
        HttpSession session = request.getSession();
        Customer loggedCustomer = (Customer) session.getAttribute("customer");

        if (loggedCustomer == null) {
            response.sendRedirect(request.getContextPath() + "/views/customer/login.jsp");
            return;
        }

        List<ServiceBookingDTO> serviceBookings = null;
        String redirectUrl = null;
        if ("active".equals(status)) {
            serviceBookings = serviceBookingService.getActiveBookingsByCustomerID(loggedCustomer.getCustomerID());
            redirectUrl = "/views/customer/service-booking/active.jsp";

        } else if ("missed".equals(status)) {
            serviceBookings = serviceBookingService.getBookingsByCustomerIDAndStatus(loggedCustomer.getCustomerID(), "Missed Appointment");
            redirectUrl = "/views/customer/service-booking/missed.jsp";
        }

        request.setAttribute("bookings", serviceBookings);

        request.getRequestDispatcher(redirectUrl).forward(request, response);
    }
}
