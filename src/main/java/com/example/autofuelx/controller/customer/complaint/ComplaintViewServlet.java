package com.example.autofuelx.controller.customer.complaint;

import com.example.autofuelx.dto.ComplaintReplyDTO;
import com.example.autofuelx.model.Complaint;
import com.example.autofuelx.model.Customer;
import com.example.autofuelx.model.Employee;
import com.example.autofuelx.model.ReplyComplaint;
import com.example.autofuelx.service.ComplaintService;
import com.example.autofuelx.service.EmployeeService;
import com.example.autofuelx.service.ReplyComplaintService;
import com.example.autofuelx.util.AuthUtil;
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
        replyComplaintService = new ReplyComplaintService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Customer customer = AuthUtil.checkCustomerLogin(request, response);
        if (customer == null) return;

        // catch data
        int complaintID = Integer.parseInt(request.getParameter("complaintID"));

        ComplaintReplyDTO complaintREplyDTO = complaintService.getComplaintReplyDTOByComplaintID(complaintID);

        ReplyComplaint replyComplaint = replyComplaintService.getReplyComplaintByReplyComplaintID(complaintID);
        if (replyComplaint != null) {
            // User view customer care reply
            replyComplaintService.updateReplyComplaintStatus(replyComplaint.getReplyComplaintID(), "Seen");
        }

        request.setAttribute("complaintReplyDTO", complaintREplyDTO);

        request.getRequestDispatcher( "/views/customer/complaint/view.jsp").forward(request, response);
    }
}
