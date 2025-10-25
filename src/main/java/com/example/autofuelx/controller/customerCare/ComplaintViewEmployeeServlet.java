package com.example.autofuelx.controller.customerCare;

import com.example.autofuelx.dto.ComplaintReplyDTO;
import com.example.autofuelx.model.Employee;
import com.example.autofuelx.service.ComplaintService;
import com.example.autofuelx.util.AuthUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/customer-care/complaint/view")
public class ComplaintViewEmployeeServlet extends HttpServlet {
    private ComplaintService complaintService;

    @Override
    public void init() throws ServletException {
        complaintService = new ComplaintService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Employee employee = AuthUtil.checkEmployeeLogin(request, response, "Customer Care Officer");
        if (employee == null) return;

        // Catch data
        int complaintID = Integer.parseInt(request.getParameter("complaintID"));

        ComplaintReplyDTO complaintDTO = complaintService.getComplaintReplyDTOByComplaintID(complaintID);

        request.setAttribute("complaintReplyDTO", complaintDTO);

        // set user complaint as viewed by customer care
        complaintService.updateComplaintStatus(complaintID, "Seen");

        request.getRequestDispatcher("/views/customer-care/complaint/view.jsp").forward(request, response);
    }
}
