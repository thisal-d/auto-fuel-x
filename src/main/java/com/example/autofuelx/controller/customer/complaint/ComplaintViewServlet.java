package com.example.autofuelx.controller.customer.complaint;

import com.example.autofuelx.model.Complaint;
import com.example.autofuelx.model.ReplyComplaint;
import com.example.autofuelx.service.ComplaintService;
import com.example.autofuelx.service.ReplyComplaintService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/customer/complaint/view")
public class ComplaintViewServlet extends HttpServlet {
    private ComplaintService complaintService;
    private ReplyComplaintService replyComplaintService;

    @Override
    public void init() throws ServletException {
        complaintService = new ComplaintService();
        replyComplaintService =  new ReplyComplaintService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Catch data
        int complaintID = Integer.parseInt(request.getParameter("complaintID"));

        Complaint complaint = complaintService.getComplaintByComplaintID(complaintID);
        ReplyComplaint replyComplaint = replyComplaintService.getReplyComplaintByComplaintID(complaintID);

        request.setAttribute("complaint", complaint);
        request.setAttribute("reply-complaint", replyComplaint);

        request.getRequestDispatcher( "/views/customer/complaint/view.jsp").forward(request, response);
    }
}
