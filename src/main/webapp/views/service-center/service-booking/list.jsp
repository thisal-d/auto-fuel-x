<%--
  Created by IntelliJ IDEA.
  User: Nadeeth
  Date: 10/2/2025
  Time: 4:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.autofuelx.dto.ServiceBookingDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Assigned Service Bookings</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/service-center/assigned-bookings.css">
</head>
<body>
<jsp:include page="/views/service-center/header.jsp"/>

<div class="container">
    <div class="page-header">
        <h1>Assigned Service Bookings</h1>
    </div>

    <%
        List<ServiceBookingDTO> bookings = (List<ServiceBookingDTO>) request.getAttribute("assignedServiceBookingDTOS");

        if (bookings == null || bookings.isEmpty()) {
    %>
    <div class="empty-state">
        <div class="empty-state-icon">📋</div>
        <p>No assigned service bookings found.</p>
    </div>
    <%
    } else {
    %>
    <div class="bookings-container">
        <div class="bookings-table">
            <table>
                <tr>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Status</th>
                    <th>Customer</th>
                    <th>Vehicle</th>
                    <th>Service</th>
                    <th>Cost</th>
                    <th>Actions</th>
                </tr>

                <%
                    for (ServiceBookingDTO booking : bookings) {
                        String statusClass = booking.getStatus().toLowerCase().replace(" ", "-");
                %>
                <tr>
                    <td><span class="booking-id">#<%= booking.getBookingID() %></span></td>
                    <td><%= booking.getBookingDate() %></td>
                    <td><%= booking.getBookingTime() %></td>
                    <td><span class="status-badge <%= statusClass %>"><%= booking.getStatus() %></span></td>
                    <td>
                        <div class="customer-info">
                            <div class="customer-avatar"><%= booking.getCustomerFirstName().charAt(0) %><%= booking.getCustomerLastName().charAt(0) %></div>
                            <span><%= booking.getCustomerFirstName() %> <%= booking.getCustomerLastName() %></span>
                        </div>
                    </td>
                    <td>
                        <div class="vehicle-info">
                            <span class="vehicle-plate"><%= booking.getVehiclePlate() %></span>
                            <span class="vehicle-model"><%= booking.getVehicleModel() %></span>
                        </div>
                    </td>
                    <td><%= booking.getServiceType() %></td>
                    <td><span class="cost">$<%= booking.getTotalCost() %></span></td>
                    <td>
                        <div class="action-buttons">
                            <a href="#" class="action-btn view-btn">View</a>
                            <a href="#" class="action-btn edit-btn">Edit</a>
                        </div>
                    </td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>
    </div>
    <%
        }
    %>
</div>
</body>
</html>