package com.example.autofuelx.controller.admin.serviceBooking;


import com.example.autofuelx.model.Employee;
import com.example.autofuelx.model.ServiceBooking;
import com.example.autofuelx.service.ServiceBookingService;
import com.example.autofuelx.strategy.CardPaymentStrategy;
import com.example.autofuelx.strategy.CashPaymentStrategy;
import com.example.autofuelx.strategy.PaymentContext;
import com.example.autofuelx.util.AuthUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/service-booking/complete")
public class ServiceBookingCompleteServlet extends HttpServlet {
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
        String paymentMethod = request.getParameter("paymentMethod");
        ServiceBooking booking = serviceBookingService.getBookingByID(bookingId);


        // Create payment context and select strategy based on payment method
        PaymentContext paymentContext = new PaymentContext();

        if (paymentMethod.equalsIgnoreCase("CASH")) {
            paymentContext.setPaymentStrategy(new CashPaymentStrategy());
        } else if (paymentMethod.equalsIgnoreCase("CARD")) {
            String cardNumber = request.getParameter("cardNumber");
            paymentContext.setPaymentStrategy(new CardPaymentStrategy(cardNumber));
        }
        // Process payment using the selected strategy
        String paymentResult = paymentContext.processPayment(booking.getTotalCost());


        booking.setStatus("Completed");
        serviceBookingService.updateBooking(booking);

        // Redirect back to  bookings page
        response.sendRedirect(request.getContextPath() + "/admin/service-booking/view?status=completed");
    }
}