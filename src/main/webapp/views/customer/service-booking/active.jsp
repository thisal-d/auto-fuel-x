<%--
  Created by IntelliJ IDEA.
  User: Nadeeth
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
    <title>Active Bookings - Vehicle Service & Refuel Center</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/customer/service-booking/active.css">
</head>

<body>
<jsp:include page="/views/customer/header.jsp"/>
<jsp:include page="header.jsp"/>

<div class="container">
    <div class="page-header">
        <h1>Active Bookings</h1>
    </div>

    <div class="booking-cards-container">
        <%
            List<ServiceBookingDTO> bookings = (List<ServiceBookingDTO>) request.getAttribute("bookings");
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

            if (bookings != null && !bookings.isEmpty()) {
                for (ServiceBookingDTO booking : bookings) {
                    String serviceInfo = booking.getServiceType() + " (" + booking.getServiceCost() + ")";
                    String vehicleInfo = booking.getVehiclePlate() + " - " + booking.getVehicleModel();
                    String formattedDate = booking.getBookingDate().format(dateFormatter);
                    String formattedTime = booking.getBookingTime().format(timeFormatter);
        %>
        <div class="booking-card">
            <div class="booking-card-header">
                <span class="status-badge status-<%= booking.getStatus().toLowerCase().replace(" ", "-") %>">
                    <%= booking.getStatus() %>
                </span>
            </div>

            <div class="booking-card-content">
                <div class="booking-detail">
                    <div class="booking-detail-label">Vehicle</div>
                    <div class="booking-detail-value"><%= vehicleInfo %></div>
                </div>

                <div class="booking-detail">
                    <div class="booking-detail-label">Service</div>
                    <div class="booking-detail-value"><%= booking.getServiceType() %> ($<%= booking.getServiceCost() %>)</div>
                </div>

                <div class="booking-detail">
                    <div class="booking-detail-label">Date & Time</div>
                    <div class="booking-detail-value"><%= formattedDate %> at <%= formattedTime %></div>
                </div>

                <div class="booking-detail">
                    <div class="booking-detail-label">Status</div>
                    <div class="booking-detail-value"><%= booking.getStatus() %></div>
                </div>
            </div>

            <div class="booking-card-actions">
                <% if (booking.getStatus().equals("Reschedule Required")) { %>
                <form class="action-form" action="<%=request.getContextPath()%>/user/service-booking/reschedule" method="post">
                    <input type="hidden" name="booking-ID" value="<%= booking.getBookingID() %>">
                    <input type="hidden" name="redirect-url" value="/customer/service-booking/list?status=active">

                    <div class="action-form-row">
                        <input type="date" name="new-date" placeholder="New Date" required>
                        <input type="time" name="new-time" placeholder="New Time" required>
                    </div>
                    <input type="submit" value="Reschedule">
                </form>
                <% } %>

                <%
                    String s = booking.getStatus();
                    if (s.equals("Awaiting Confirmation") || s.equals("Confirmed") || s.equals("Reschedule Required")) {
                %>
                <form class="action-form" action="<%=request.getContextPath()%>/user/service-booking/update-status" method="post">
                    <input type="hidden" name="booking-ID" value="<%= booking.getBookingID() %>">
                    <input type="hidden" name="status" value="Cancelled">
                    <input type="hidden" name="redirect-url" value="/customer/service-booking/list?status=active">
                    <input type="submit" class="cancel-btn" value="Cancel">
                </form>
                <% } %>
            </div>
        </div>
        <%
            }
        } else {
        %>
        <div class="empty-state">No active bookings found.</div>
        <%
            }
        %>
    </div>
</div>
</body>
</html>