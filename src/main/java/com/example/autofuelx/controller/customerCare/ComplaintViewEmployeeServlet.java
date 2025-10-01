package com.example.autofuelx.controller.customerCare;

import com.example.autofuelx.dto.ComplaintReplyDTO;
import com.example.autofuelx.service.ComplaintService;
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

        // Catch data
        int complaintID = Integer.parseInt(request.getParameter("complaintID"));


        ComplaintReplyDTO complaintDTO = complaintService.getComplaintReplyDTOByComplaintID(complaintID);

        request.setAttribute("complaintReplyDTO", complaintDTO);

        request.getRequestDispatcher( "/views/customer-care/complaint/view.jsp").forward(request, response);
    }
}
