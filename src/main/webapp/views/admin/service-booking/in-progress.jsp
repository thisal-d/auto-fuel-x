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
  <title>In Progress Bookings</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/admin/service-booking/list.css">
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="booking-container">
  <div class="booking-header">
    <h1 class="booking-title">In Progress</h1>
  </div>

  <div class="description-section">
    <p>Services currently being serviced.</p>
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
          <th>Actions</th>
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
          <td><%= customerName %></td>
          <td><%= vehicleInfo %></td>
          <td><%= booking.getServiceType() %></td>
          <td><%= formattedDate %></td>
          <td><%= formattedTime %></td>
          <td>
            <div class="action-buttons">
              <!-- Service Completed Form -->
              <form action="<%= request.getContextPath() %>/admin/service-booking/update-status" method="post" class="action-form">
                <div class="form-group">
                  <label class="filter-label">Total Cost:</label>
                  <input type="number" name="total-cost" value="<%= booking.getServiceCost() %>" class="form-control" step="0.01">
                </div>
                <input type="hidden" name="bookingId" value="<%= booking.getBookingID() %>">
                <input type="hidden" name="status" value="Awaiting Pickup">
                <input type="hidden" name="redirect-url" value="/admin/service-booking/view?status=in-progress">
                <button type="submit" class="btn btn-success">Service Completed</button>
              </form>
            </div>
          </td>
        </tr>
        <%   }
        } else { %>
        <tr>
          <td colspan="6" class="no-results">No in-progress bookings found.</td>
        </tr>
        <% } %>
        </tbody>
      </table>
    </div>
  </div>
</div>
</body>
</html>