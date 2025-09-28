package com.example.autofuelx.controller.customer.feedback;

import java.io.*;

import com.example.autofuelx.model.Customer;
import com.example.autofuelx.model.Feedback;
import com.example.autofuelx.service.FeedbackService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/customer/feedback/create")
public class FeedbackCreateServlet extends HttpServlet {
    private FeedbackService feedbackService;

    @Override
    public void init() throws ServletException {
        feedbackService = new FeedbackService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Catch data
        int rate = Integer.parseInt(request.getParameter("title"));
        String message = request.getParameter("message");

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        Feedback feedback = new Feedback();
        feedback.setRate(rate);
        feedback.setMessage(message);
        feedback.setCustomerID(customer.getCustomerID());

        feedbackService.submitFeedback(feedback);

        response.sendRedirect("customer/feedback/list");
    }
}
