package com.example.autofuelx.controller.customer.feedback;

import com.example.autofuelx.model.Customer;
import com.example.autofuelx.model.Feedback;
import com.example.autofuelx.service.FeedbackService;
import com.example.autofuelx.util.AuthUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/customer/feedback/create")
public class FeedbackCreateServlet extends HttpServlet {
    private FeedbackService feedbackService;

    @Override
    public void init() throws ServletException {
        feedbackService = new FeedbackService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer = AuthUtil.checkCustomerLogin(req, resp);
        if (customer == null) return;

        resp.sendRedirect("customer/feedback/list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Customer customer = AuthUtil.checkCustomerLogin(request, response);
        if (customer == null) return;

        // catch data
        int rate = Integer.parseInt(request.getParameter("title"));
        String message = request.getParameter("message");

        Feedback feedback = new Feedback();
        feedback.setRate(rate);
        feedback.setMessage(message);
        feedback.setCustomerID(customer.getCustomerID());

        feedbackService.submitFeedback(feedback);

        response.sendRedirect("customer/feedback/list");
    }
}
