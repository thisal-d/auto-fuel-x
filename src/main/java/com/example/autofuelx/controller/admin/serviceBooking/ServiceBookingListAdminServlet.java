
package com.example.autofuelx.controller.admin.serviceBooking;

import com.example.autofuelx.dto.ServiceBookingDTO;
import com.example.autofuelx.model.Employee;
import com.example.autofuelx.service.EmployeeService;
import com.example.autofuelx.service.ServiceBookingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/service-booking/view")
public class ServiceBookingListAdminServlet extends HttpServlet {
    private ServiceBookingService serviceBookingService;
    private EmployeeService employeeService;

    @Override
    public void init() throws ServletException {
        serviceBookingService = new ServiceBookingService();
        employeeService = new EmployeeService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String viewStatus = request.getParameter("status");

        String redirectUrl = "";
        String status = "";

        if (viewStatus.equals("awaiting-confirmation")) {
            redirectUrl = "/views/admin/service-booking/awaiting-confirmation.jsp";
            status = "Awaiting Confirmation";
        } else if (viewStatus.equals("confirmed")) {
            redirectUrl = "/views/admin/service-booking/confirmed.jsp";
            status = "Confirmed";
        } else if (viewStatus.equals("in-progress")) {
            redirectUrl = "/views/admin/service-booking/in-progress.jsp";
            status = "In Progress";
        } else if (viewStatus.equals("missed-appointment")) {
            redirectUrl = "/views/admin/service-booking/missed-appointment.jsp";
            status = "Missed Appointment";
        } else if (viewStatus.equals("awaiting-pickup")) {
            redirectUrl = "/views/admin/service-booking/awaiting-pickup.jsp";
            status = "Awaiting Pickup";
        } else if (viewStatus.equals("completed")) {
            redirectUrl = "/views/admin/service-booking/completed.jsp";
            status = "Completed"; }
        else if (viewStatus.equals("reschedule-required")) {
            redirectUrl = "/views/admin/service-booking/reschedule-required.jsp";
            status = "Reschedule Required";
        }else if (viewStatus.equals("cancelled")) {
                redirectUrl = "/views/admin/service-booking/cancelled.jsp";
                status = "Cancelled";
        } else {
            redirectUrl = "/views/admin/service-booking/all.jsp";
            status = "All";
        }

        List<ServiceBookingDTO> bookings;
        if(!status.equals("All")) bookings = serviceBookingService.getAllBookings(status);
        else bookings = serviceBookingService.getAllBookings();

        List<Employee> employees = employeeService.getEmployeesByTypeStatus("Service Center Staff", "Active");

        System.out.println(employees.size());
        request.setAttribute("bookings", bookings);
        request.setAttribute("employees", employees);

        // Forward to JSP
        request.getRequestDispatcher(redirectUrl).forward(request, response);
    }
}