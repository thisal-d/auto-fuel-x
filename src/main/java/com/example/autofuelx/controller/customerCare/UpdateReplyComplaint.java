package com.example.autofuelx.controller.customerCare;

import com.example.autofuelx.model.Employee;
import com.example.autofuelx.model.ReplyComplaint;
import com.example.autofuelx.service.ReplyComplaintService;
import com.example.autofuelx.util.AuthUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/customer-care/complaint/update")
public class UpdateReplyComplaint extends HttpServlet {
    private ReplyComplaintService replyComplaintService;

    @Override
    public void init() throws ServletException {
        replyComplaintService = new ReplyComplaintService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = AuthUtil.checkEmployeeLogin(req, resp, "Customer Care Officer");
        if (employee == null) {
        } else resp.sendRedirect(req.getContextPath() + "/customer-care/complaint/list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Employee employee = AuthUtil.checkEmployeeLogin(request, response, "Customer Care Officer");
        if (employee == null) return;

        // Catch data
        int complaintID = Integer.parseInt(request.getParameter("complaintID"));
        String replyTitle = request.getParameter("replyTitle");
        String replyDescription = request.getParameter("replyDescription");

        ReplyComplaint replyComplaint = replyComplaintService.getReplyComplaintByComplaintID(complaintID);

        replyComplaint.setTitle(replyTitle);
        replyComplaint.setDescription(replyDescription);
        replyComplaint.setStaffID(employee.getEmployeeID());

        replyComplaintService.updateReplyComplaint(replyComplaint);

        replyComplaintService.updateReplyComplaintStatus(complaintID, "Sent");
        replyComplaint = replyComplaintService.getReplyComplaintByComplaintID(complaintID);

        // check status
        System.out.println("Status : " + replyComplaint.getStatus());

        /*
        // update complaint
        complaintService.updateComplaintStatus(complaintID, "Seen");
        */

        response.sendRedirect(request.getContextPath() + "/customer-care/complaint/view?complaintID=" + complaintID);
    }
}