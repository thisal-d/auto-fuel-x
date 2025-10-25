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
import java.util.List;

@WebServlet("/customer-care/complaint/list")
public class ComplaintListServlet extends HttpServlet {
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

        // read filter parameters from request
        String keyword = request.getParameter("keyword");
        String lastUpdateDate = request.getParameter("last-update-date");
        String customerEmail = request.getParameter("customer-email");
        String status = request.getParameter("status");

        List<ComplaintReplyDTO> complaints = complaintService.getComplaintReplyDTOsFiltered(keyword, lastUpdateDate, customerEmail, status);

        request.setAttribute("complaints", complaints);
        request.setAttribute("status", status);
        request.setAttribute("last-update-date", lastUpdateDate);
        request.setAttribute("customer-email", customerEmail);
        request.setAttribute("keyword", keyword);

        request.getRequestDispatcher("/views/customer-care/complaint/list.jsp").forward(request, response);
    }
}
