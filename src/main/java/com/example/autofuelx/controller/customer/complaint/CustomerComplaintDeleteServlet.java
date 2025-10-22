package com.example.autofuelx.controller.customer.complaint;

import com.example.autofuelx.model.Complaint;
import com.example.autofuelx.model.Customer;
import com.example.autofuelx.service.ComplaintService;
import com.example.autofuelx.util.AuthUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/customer/complaint/delete")
public class CustomerComplaintDeleteServlet extends HttpServlet {
    private ComplaintService complaintService;

    @Override
    public void init() throws ServletException {
        complaintService = new ComplaintService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer = AuthUtil.checkCustomerLogin(req, resp);
        if (customer == null) return;
        resp.sendRedirect(req.getContextPath() + "/customer/complaint/list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Customer customer = AuthUtil.checkCustomerLogin(request, response);
        if (customer == null) return;

        // catch data
        int complaintID = Integer.parseInt(request.getParameter("complaintID"));

        complaintService.deleteComplaint(complaintID);

        response.sendRedirect(request.getContextPath() + "/customer/complaint/list");
    }
}
