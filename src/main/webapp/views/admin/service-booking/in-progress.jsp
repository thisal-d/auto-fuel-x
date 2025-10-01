<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/28/2025
  Time: 11:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.autofuelx.dto.ServiceBookingDTO" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>In Progress Bookings</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<h1>In Progress</h1>

<p>Services currently being serviced.</p>

<p><a href="<%= request.getContextPath() %>/admin/dashboard">Back to Dashboard</a></p>

<table border="1">
  <tr>
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
    <td><%= customerName %></td>
    <td><%= vehicleInfo %></td>
    <td><%= booking.getServiceType() %></td>
    <td><%= formattedDate %></td>
    <td><%= formattedTime %></td>
    <td>
      <!-- Approve Form -->
      <form action="<%= request.getContextPath() %>/admin/service-booking/update-status" method="post" style="display:inline;">
        <input type="number" name="total-cost" value="<%= booking.getServiceCost() %>">
        <input type="hidden" name="bookingId" value="<%= booking.getBookingID() %>">
        <input type="hidden" name="status" value="Awaiting Pickup">
        <input type="hidden" name="redirect-url" value="/admin/service-booking/view?status=in-progress">
        <button type="submit">Service Completed</button>
      </form>
    </td>
  </tr>
  <%
    }
  } else {
  %>
  <tr>
    <td colspan="7">No in-progress bookings found.</td>
  </tr>
  <%
    }
  %>
</table>
</body>
</html>