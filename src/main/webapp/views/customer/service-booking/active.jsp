
<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/29/2025
  Time: 2:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.autofuelx.dto.ServiceBookingDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<html>
<head>
    <title>Title</title>
</head>

<jsp:include page="/views/customer/header.jsp"/>
<jsp:include page="header.jsp"/>

<h1>Active Booking</h1>

<table border="1">
    <tr>
        <th>Booking ID</th>
        <th>Customer</th>
        <th>Vehicle</th>
        <th>Service</th>
        <th>Date</th>
        <th>Time</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>

    <%
        List<ServiceBookingDTO> bookings = (List<ServiceBookingDTO>) request.getAttribute("bookings");
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
        <td><%= booking.getBookingID() %></td>
        <td><%= customerName %></td>
        <td><%= vehicleInfo %></td>
        <td><%= booking.getServiceType() %></td>
        <td><%= formattedDate %></td>
        <td><%= formattedTime %></td>
        <td><%= booking.getStatus() %></td>
        <td>
            <% if (booking.getStatus().equals("Reschedule Required")) { %>
            <!-- Reschedule Form -->
            <form action="<%=request.getContextPath()%>/user/service-booking/reschedule" method="post">
                <input type="hidden" name="booking-ID" value="<%= booking.getBookingID() %>">
                <input type="hidden" name="redirect-url" value="/customer/service-booking/list?status=active">

                <label>New Date: <input type="date" name="new-date" required></label><br>
                <label>New Time: <input type="time" name="new-time" required></label><br>
                <input type="submit" value="Reschedule">
            </form>

            <!-- Cancel Form -->
            <form action="<%=request.getContextPath()%>/user/service-booking/update-status" method="post">
                <input type="hidden" name="booking-ID" value="<%= booking.getBookingID() %>">
                <input type="hidden" name="status" value="Cancelled">
                <input type="hidden" name="redirect-url" value="/customer/service-booking/list?status=active">
                <input type="submit" value="Cancel">
            </form>
            <% } %>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="8">No pending approval bookings found.</td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>