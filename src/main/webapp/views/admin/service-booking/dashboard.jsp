<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/28/2025
  Time: 7:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Service Booking Dashboard</title>
</head>
<body>

<%
  Map<String, Integer> statusCounts = (Map<String, Integer>) request.getAttribute("statusCounts");
  int totalBookings = statusCounts.get("totalBookings");
  int pendingApprovalCount = statusCounts.get("awaitingConfirmationCount");
  int confirmedCount = statusCounts.get("confirmedCount");
  int rescheduleRequiredCount = statusCounts.get("rescheduleRequiredCount");
  int inProgressCount = statusCounts.get("inProgressCount");
  int waitingPickupCount = statusCounts.get("awaitingPickupCount");
  int completedCount = statusCounts.get("completedCount");
  int cancelledCount = statusCounts.get("cancelledCount");
  int missedAppointmentCount = statusCounts.get("missedAppointmentCount");
%>

<jsp:include page="header.jsp"/>

<h1>Service Booking Dashboard</h1>

<p>Summary of service bookings</p>

<table border="1">
  <tr>
    <th>Status</th>
    <th>Count</th>
    <th>Action</th>
  </tr>
  <tr>
    <td>Total Bookings</td>
    <td><%= totalBookings %></td>
    <td><a href="<%= request.getContextPath() %>/admin/service-booking/view?status=all">View All</a></td>
  </tr>
  <tr>
    <td>Pending Approval (Need to accept requested time)</td>
    <td><%= pendingApprovalCount %></td>
    <td><a href="<%= request.getContextPath() %>/admin/service-booking/view?status=awaiting-confirmation">Manage</a></td>
  </tr>
  <tr>
    <td>Confirmed (Scheduled Services)</td>
    <td><%= confirmedCount %></td>
    <td><a href="<%= request.getContextPath() %>/admin/service-booking/view?status=confirmed">Manage</a></td>
  </tr>
  <tr>
    <td>Reschedule Required</td>
    <td><%= rescheduleRequiredCount %></td>
    <td><a href="<%= request.getContextPath() %>/admin/service-booking/view?status=reschedule-required">Manage</a></td>
  </tr>
  <tr>
    <td>In Progress (Service in progress)</td>
    <td><%= inProgressCount %></td>
    <td><a href="<%= request.getContextPath() %>/admin/service-booking/view?status=in-progress">Manage</a></td>
  </tr>
  <tr>
    <td>Service Done (Waiting for vehicle pickup)</td>
    <td><%= waitingPickupCount %></td>
    <td><a href="<%= request.getContextPath() %>/admin/service-booking/view?status=awaiting-pickup">Manage</a></td>
  </tr>
  <tr>
    <td>Completed (Vehicle picked up by customer)</td>
    <td><%= completedCount %></td>
    <td><a href="<%= request.getContextPath() %>/admin/service-booking/view?status=completed">View</a></td>
  </tr>
  <tr>
    <td>Cancelled (Cancelled Services)</td>
    <td><%= cancelledCount%></td>
    <td><a href="<%= request.getContextPath() %>/admin/service-booking/view?status=cancelled">View</a></td>
  </tr>
  <tr>
    <td>Missed Appointments (Customer didn't show up)</td>
    <td><%= missedAppointmentCount %></td>
    <td><a href="<%= request.getContextPath() %>/admin/service-booking/view?status=missed-appointment">View</a></td>
  </tr>
</table>

<h2>Quick Actions</h2>
<ul>
  <li><a href="<%= request.getContextPath() %>/admin/service-booking/view?status=awaiting-confirmation">Review Pending Approvals</a></li>
  <li><a href="<%= request.getContextPath() %>/admin/service-booking/view?status=confirmed">Check Today's Appointments</a></li>
  <li><a href="<%= request.getContextPath() %>/admin/service-booking/view?status=in-progress">Monitor Active Services</a></li>
  <li><a href="<%= request.getContextPath() %>/admin/service-booking/view?status=all">View All Bookings</a></li>
</ul>

<p><a href="<%= request.getContextPath() %>/admin/dashboard">Back to Admin Dashboard</a></p>
</body>
</html>