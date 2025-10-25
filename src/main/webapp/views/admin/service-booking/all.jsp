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
<%
    List<ServiceBookingDTO> bookings = (List<ServiceBookingDTO>) request.getAttribute("bookings");
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
%>
<html>
<head>
    <title>All Bookings</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/admin/service-booking/list.css">
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="booking-container">
    <div class="booking-header">
        <h1 class="booking-title">All Bookings</h1>
    </div>

    <div class="description-section">
        <p>Complete list of all service bookings.</p>
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
                    <th>Booking ID</th>
                    <th>Customer</th>
                    <th>Vehicle</th>
                    <th>Service</th>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Status</th>
                </tr>
                </thead>
                <tbody>
                <% if (bookings != null && !bookings.isEmpty()) {
                    for (ServiceBookingDTO booking : bookings) {
                        String customerName = booking.getCustomerFirstName() + " " + booking.getCustomerLastName();
                        String vehicleInfo = booking.getVehiclePlate() + " - " + booking.getVehicleModel();
                        String formattedDate = booking.getBookingDate().format(dateFormatter);
                        String formattedTime = booking.getBookingTime().format(timeFormatter);
                        String status = booking.getStatus();
                %>
                <tr>
                    <td><%= booking.getBookingID() %>
                    </td>
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
                    <td>
                        <% if ("Awaiting Confirmation".equals(status)) { %>
                        <span class="status status-pending">Pending</span>
                        <% } else if ("Confirmed".equals(status)) { %>
                        <span class="status status-confirmed">Confirmed</span>
                        <% } else if ("In Progress".equals(status)) { %>
                        <span class="status status-progress">In Progress</span>
                        <% } else if ("Awaiting Pickup".equals(status)) { %>
                        <span class="status status-awaiting">Awaiting Pickup</span>
                        <% } else if ("Completed".equals(status)) { %>
                        <span class="status status-completed">Completed</span>
                        <% } else if ("Missed Appointment".equals(status)) { %>
                        <span class="status status-missed">Missed</span>
                        <% } else if ("Reschedule Required".equals(status)) { %>
                        <span class="status status-reschedule">Reschedule</span>
                        <% } %>
                    </td>
                </tr>
                <% }
                } else { %>
                <tr>
                    <td colspan="7" class="no-results">No bookings found.</td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>