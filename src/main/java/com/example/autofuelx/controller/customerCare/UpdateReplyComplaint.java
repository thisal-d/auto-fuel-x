package com.example.autofuelx.controller.customerCare;

import com.example.autofuelx.model.Complaint;
import com.example.autofuelx.model.Employee;
import com.example.autofuelx.model.ReplyComplaint;
import com.example.autofuelx.service.ComplaintService;
import com.example.autofuelx.service.ReplyComplaintService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/customer-care/complaint/update")
public class UpdateReplyComplaint extends HttpServlet {
    private ReplyComplaintService replyComplaintService;
    private ComplaintService complaintService;

    @Override
    public void init() throws ServletException {
        replyComplaintService = new ReplyComplaintService();
        complaintService = new ComplaintService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("employee");

        // Catch data
        int complaintID = Integer.parseInt(request.getParameter("complaintID"));
        String replyTitle = request.getParameter("replyTitle");
        String replyDescription = request.getParameter("replyDescription");

        ReplyComplaint replyComplaint = replyComplaintService.getReplyComplaintByComplaintID(complaintID);

        replyComplaint.setTitle(replyTitle);
        replyComplaint.setDescription(replyDescription);
        replyComplaint.setStaffID(employee.getEmployeeID());
        replyComplaint.setStatus("Open");

        replyComplaintService.updateReplyComplaint(replyComplaint);

        // set user complaint as viewed
        Complaint complaint = complaintService.getComplaintByComplaintID(complaintID);
        complaint.setStatus("Closed");
        complaintService.updateComplaint(complaint);

        response.sendRedirect(request.getContextPath() + "/customer-care/complaint/view?complaintID=" + complaintID);
    }
}