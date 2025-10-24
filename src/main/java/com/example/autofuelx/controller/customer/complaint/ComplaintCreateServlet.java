package com.example.autofuelx.controller.customer.complaint;

import java.io.*;

import com.example.autofuelx.model.Complaint;
import com.example.autofuelx.model.Customer;
import com.example.autofuelx.service.ComplaintService;
import com.example.autofuelx.util.AuthUtil;
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
        // get logged user
        Customer customer = AuthUtil.checkCustomerLogin(request, response);
        if (customer == null) return;

        response.sendRedirect(request.getContextPath() + "/customer/complaint/list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Customer customer = AuthUtil.checkCustomerLogin(request, response);
        if (customer == null) return;

        // catch data
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        if (title.trim().length() == 0 || description.trim().length() == 0){
            request.setAttribute("error-message", "Please Fill the complaint form before submitting.");
            request.getRequestDispatcher("/views/customer/complaint/form.jsp").forward(request, response);
            return;
        }

        // create complaint Object
        Complaint complaint = new Complaint();

        complaint.setTitle(title);
        complaint.setDescription(description);
        complaint.setStatus("Sent");
        complaint.setCustomerID(customer.getCustomerID());

        complaintService.submitComplaint(complaint);

        response.sendRedirect(request.getContextPath() + "/customer/complaint/list");
    }
}
