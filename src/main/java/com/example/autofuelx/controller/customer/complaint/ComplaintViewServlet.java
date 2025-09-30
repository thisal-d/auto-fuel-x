package com.example.autofuelx.controller.customer.complaint;

import com.example.autofuelx.dto.ComplaintReplyDTO;
import com.example.autofuelx.model.Complaint;
import com.example.autofuelx.model.Employee;
import com.example.autofuelx.model.ReplyComplaint;
import com.example.autofuelx.service.ComplaintService;
import com.example.autofuelx.service.EmployeeService;
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
    private EmployeeService employeeService;

    @Override
    public void init() throws ServletException {
        complaintService = new ComplaintService();
        replyComplaintService =  new ReplyComplaintService();
        employeeService =  new EmployeeService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Catch data
        int complaintID = Integer.parseInt(request.getParameter("complaintID"));

        ComplaintReplyDTO complaintDTO = complaintService.getComplaintReplyDTOByComplaintID(complaintID);

        request.setAttribute("complaintReplyDTO", complaintDTO);

        request.getRequestDispatcher( "/views/customer/complaint/view.jsp").forward(request, response);
    }
}
