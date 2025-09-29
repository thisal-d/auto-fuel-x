package com.example.autofuelx.controller.customer.complaint;

import com.example.autofuelx.model.Complaint;
import com.example.autofuelx.service.ComplaintService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/customer/complaint/update-form")
public class ComplaintUpdateFormServlet extends HttpServlet {
    private ComplaintService complaintService;

    @Override
    public void init() throws ServletException {
        complaintService = new ComplaintService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int complaintID = Integer.parseInt(request.getParameter("complaintID"));
        Complaint complaint = complaintService.getComplaintByComplaintID(complaintID);
        request.setAttribute("complaint", complaint);

        request.getRequestDispatcher("/views/customer/complaint/update.jsp").forward(request, response);
    }
}
