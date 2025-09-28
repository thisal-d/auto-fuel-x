package com.example.autofuelx.controller.customer.feedback;

import java.io.*;

import com.example.autofuelx.service.FeedbackService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/customer/feedback/delete")
public class FeedbackDeleteServlet extends HttpServlet {
    private FeedbackService feedbackService;

    @Override
    public void init() throws ServletException {
        feedbackService = new FeedbackService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Catch data
        int feedbackID = Integer.parseInt(request.getParameter("id"));

        // Delete feedback
        feedbackService.deleteComplaint(feedbackID);

        response.sendRedirect("customer/feedback/list");
    }
}
