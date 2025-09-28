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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Completed Bookings</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<h1>Completed Bookings</h1>

<p>Completed bookings that have been fully processed and closed.</p>

<p><a href="<%= request.getContextPath() %>/admin/dashboard">Back to Dashboard</a></p>

<table border="1">
    <tr>
        <th>Booking ID</th>
        <th>Customer</th>
        <th>Vehicle</th>
        <th>Service</th>
        <th>Date</th>
        <th>Time</th>
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
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="7">No completed bookings found.</td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>