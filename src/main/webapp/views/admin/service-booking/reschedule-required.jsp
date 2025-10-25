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
<%
    List<ServiceBookingDTO> bookings = (List<ServiceBookingDTO>) request.getAttribute("bookings");
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
%>
<html>
<head>
    <title>Reschedule Required Bookings</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/admin/service-booking/list.css">
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="booking-container">
    <div class="booking-header">
        <h1 class="booking-title">Reschedule Required Bookings</h1>
    </div>

    <div class="description-section">
        <p>Customer scheduled time needs to be changed. Please review the bookings below:</p>
        <div class="back-link">
            <a href="<%= request.getContextPath() %>/admin/dashboard" class="btn btn-secondary">Back to Dashboard</a>
        </div>
    </div>

    <div class="table-section">
        <h2 class="table-title">Booking List</h2>
        <div class="table-container">
            <table class="booking-table">
                <thead>
                <tr>
                    <th>Customer</th>
                    <th>Vehicle</th>
                    <th>Service</th>
                    <th>Date</th>
                    <th>Time</th>
                </tr>
                </thead>
                <tbody>
                <% if (bookings != null && !bookings.isEmpty()) {
                    for (ServiceBookingDTO booking : bookings) {
                        String customerName = booking.getCustomerFirstName() + " " + booking.getCustomerLastName();
                        String vehicleInfo = booking.getVehiclePlate() + " - " + booking.getVehicleModel();
                        String formattedDate = booking.getBookingDate().format(dateFormatter);
                        String formattedTime = booking.getBookingTime().format(timeFormatter);
                %>
                <tr>
                    <td><%= customerName %>
                    </td>
                    <td><%= vehicleInfo %>
                    </td>
                    <td><%= booking.getServiceType() %>
                    </td>
                    <td><%= formattedDate %>
                    </td>
                    <td><%= formattedTime %>
                    </td>
                </tr>
                <% }
                } else { %>
                <tr>
                    <td colspan="5" class="no-results">No reschedule required bookings found.</td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>