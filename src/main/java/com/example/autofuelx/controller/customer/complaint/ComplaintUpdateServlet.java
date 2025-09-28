package com.example.autofuelx.controller.customer.complaint;

import com.example.autofuelx.model.Complaint;
import com.example.autofuelx.service.ComplaintService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/customer/complaint/update")
public class ComplaintUpdateServlet extends HttpServlet {
    private ComplaintService complaintService;

    @Override
    public void init() throws ServletException {
        complaintService = new ComplaintService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Catch data
        int complaintID = Integer.parseInt(request.getParameter("complaintID"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String status = request.getParameter("status");


        Complaint complaint = new Complaint();
        complaint.setComplaintID(complaintID);
        complaint.setTitle(title);
        complaint.setDescription(description);
        complaint.setDescription(status);

        complaintService.submitComplaint(complaint);

        response.sendRedirect(request.getContextPath() + "/customer/complaint/list");
    }
}
