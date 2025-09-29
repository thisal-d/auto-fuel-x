
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

<h1>Missed Booking</h1>


<table border="1">
    <tr>
        <th>Booking ID</th>
        <th>Customer</th>
        <th>Vehicle</th>
        <th>Service</th>
        <th>Date</th>
        <th>Time</th>
        <th>Status</th>
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
            <form action="<%=request.getContextPath()%>/user/service-booking/update-status" method="post">
                <input type="hidden" value="<%=booking.getBookingID()%>" name="booking-ID">
                <input type="hidden" value="Canceled" name="status">
                <input type="hidden" value="/customer/service-booking/list?status=missed" name="redirect-url">
                <input type="submit" value="Set as canceled">
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