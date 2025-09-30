package com.example.autofuelx.controller.customer.complaint;

import com.example.autofuelx.dto.ComplaintReplyDTO;
import com.example.autofuelx.model.Complaint;
import com.example.autofuelx.model.Customer;
import com.example.autofuelx.service.ComplaintService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/customer/complaint/list")
public class ComplaintListServlet extends HttpServlet {
    private ComplaintService complaintService;

    @Override
    public void init() throws ServletException {
        complaintService = new ComplaintService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get logged-in user
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        List<ComplaintReplyDTO> complaints = complaintService.getComplaintsWithReplyByCustomerId(customer.getCustomerID());

        request.setAttribute("complaints", complaints);
        request.getRequestDispatcher("/views/customer/complaint/list.jsp").forward(request, response);
    }
}
