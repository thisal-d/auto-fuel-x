package com.example.autofuelx.controller.customer.complaint;

import java.io.*;

import com.example.autofuelx.model.Complaint;
import com.example.autofuelx.model.Customer;
import com.example.autofuelx.service.ComplaintService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/customer/complaint/create")
public class ComplaintCreateServlet extends HttpServlet {
    private ComplaintService complaintService;

    @Override
    public void init() throws ServletException {
        complaintService = new ComplaintService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Catch data
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        // Get logged user
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        // Create complaint Object
        Complaint complaint = new Complaint();

        complaint.setTitle(title);
        complaint.setDescription(description);
        complaint.setStatus("Open");
        complaint.setCustomerID(customer.getCustomerID());

        complaintService.submitComplaint(complaint);

        response.sendRedirect(request.getContextPath() + "/customer/complaint/list");
    }
}
