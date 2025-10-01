<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/28/2025
  Time: 8:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.autofuelx.dto.ServiceBookingDTO" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="com.example.autofuelx.model.Employee" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pending Approval Bookings</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<h1>Pending Approval</h1>

<p>Bookings waiting for admin/staff approval of requested time.</p>

<p><a href="<%= request.getContextPath() %>/admin/dashboard">Back to Dashboard</a></p>

<table border="1">
    <tr>
        <th>Customer</th>
        <th>Vehicle</th>
        <th>Service</th>
        <th>Date & Time</th>
        <th>Action</th>
    </tr>

    <%
        List<ServiceBookingDTO> bookings = (List<ServiceBookingDTO>) request.getAttribute("bookings");
        List<Employee> employees = (List<Employee>) request.getAttribute("employees");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        if (bookings != null && !bookings.isEmpty()) {
            for (ServiceBookingDTO booking : bookings) {
                String customerName = booking.getCustomerFirstName() + " " + booking.getCustomerLastName();
                String vehicleInfo = booking.getVehiclePlate() + " - " + booking.getVehicleModel();
                String formattedDate = booking.getBookingDate().format(dateFormatter);
                String formattedTime = booking.getBookingTime().format(timeFormatter);
    %>
    <tr>
        <td><%= customerName %></td>
        <td><%= vehicleInfo %></td>
        <td><%= booking.getServiceType() %></td>
        <td><%= formattedDate %> - <%= formattedTime %></td>
        <td>
            <!-- Approve Form -->
            <form action="<%= request.getContextPath() %>/admin/service-booking/update-status" method="post" style="display:inline;">
                <input type="hidden" name="bookingId" value="<%= booking.getBookingID() %>">
                <input type="hidden" name="status" value="Confirmed">
                <label>
                    <select name="employeeId">
                        <%for (Employee e: employees) {
                            String employeeDetails = e.getFirstName() + " " + e.getLastName() + " - " + e.getSkillSet();
                        %>
                            <option value="<%=e.getEmployeeID()%>"><%=employeeDetails%></option>
                        <%}%>
                    </select>
                </label>
                <input type="hidden" name="redirect-url" value="/admin/service-booking/view?status=awaiting-pickup">
                <button type="submit">Approve</button>
            </form>

            <!-- Approve Form -->
            <form action="<%= request.getContextPath() %>/admin/service-booking/update-status" method="post" style="display:inline;">
                <input type="hidden" name="bookingId" value="<%= booking.getBookingID() %>">
                <input type="hidden" name="status" value="Reschedule Required">
                <input type="hidden" name="redirect-url" value="/admin/service-booking/view?status=awaiting-pickup">
                <button type="submit">Request Reschedule</button>
            </form>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="7">No pending approval bookings found.</td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>