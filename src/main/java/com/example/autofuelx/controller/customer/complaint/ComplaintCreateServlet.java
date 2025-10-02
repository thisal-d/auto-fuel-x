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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get logged user
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        // check user logged in ot not
        if (customer==null) {
            response.sendRedirect(request.getContextPath() + "/views/customer/login.jsp");
        }
        else {
            response.sendRedirect(request.getContextPath() + "/customer/complaint/list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get logged user
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        if (customer==null) {
            response.sendRedirect("/views/customer/login.jsp");
        }

        // Catch data
        String title = request.getParameter("title");
        String description = request.getParameter("description");

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
