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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/admin/service-booking/dashboard.css">
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

<div class="container">
    <div class="dashboard-header">
        <h1>Service Booking Dashboard</h1>
        <p>Summary of service bookings</p>
    </div>

    <div class="status-cards">
        <div class="status-card total">
            <div class="status-card-header">
                <div class="status-card-title">Total Bookings</div>
                <div class="status-card-icon">📊</div>
            </div>
            <div class="status-card-count"><%= totalBookings %>
            </div>
            <a href="<%= request.getContextPath() %>/admin/service-booking/view?status=all" class="status-card-action">View
                All →</a>
        </div>

        <div class="status-card pending">
            <div class="status-card-header">
                <div class="status-card-title">Pending Approval</div>
                <div class="status-card-icon">⏱️</div>
            </div>
            <div class="status-card-count"><%= pendingApprovalCount %>
            </div>
            <a href="<%= request.getContextPath() %>/admin/service-booking/view?status=awaiting-confirmation"
               class="status-card-action">Manage →</a>
        </div>

        <div class="status-card confirmed">
            <div class="status-card-header">
                <div class="status-card-title">Confirmed</div>
                <div class="status-card-icon">✓</div>
            </div>
            <div class="status-card-count"><%= confirmedCount %>
            </div>
            <a href="<%= request.getContextPath() %>/admin/service-booking/view?status=confirmed"
               class="status-card-action">Manage →</a>
        </div>

        <div class="status-card reschedule">
            <div class="status-card-header">
                <div class="status-card-title">Reschedule Required</div>
                <div class="status-card-icon">🔄</div>
            </div>
            <div class="status-card-count"><%= rescheduleRequiredCount %>
            </div>
            <a href="<%= request.getContextPath() %>/admin/service-booking/view?status=reschedule-required"
               class="status-card-action">Manage →</a>
        </div>

        <div class="status-card progress">
            <div class="status-card-header">
                <div class="status-card-title">In Progress</div>
                <div class="status-card-icon">🔧</div>
            </div>
            <div class="status-card-count"><%= inProgressCount %>
            </div>
            <a href="<%= request.getContextPath() %>/admin/service-booking/view?status=in-progress"
               class="status-card-action">Manage →</a>
        </div>

        <div class="status-card pickup">
            <div class="status-card-header">
                <div class="status-card-title">Waiting for Pickup</div>
                <div class="status-card-icon">🚗</div>
            </div>
            <div class="status-card-count"><%= waitingPickupCount %>
            </div>
            <a href="<%= request.getContextPath() %>/admin/service-booking/view?status=awaiting-pickup"
               class="status-card-action">Manage →</a>
        </div>

        <div class="status-card completed">
            <div class="status-card-header">
                <div class="status-card-title">Completed</div>
                <div class="status-card-icon">✅</div>
            </div>
            <div class="status-card-count"><%= completedCount %>
            </div>
            <a href="<%= request.getContextPath() %>/admin/service-booking/view?status=completed"
               class="status-card-action">View →</a>
        </div>

        <div class="status-card cancelled">
            <div class="status-card-header">
                <div class="status-card-title">Cancelled</div>
                <div class="status-card-icon">❌</div>
            </div>
            <div class="status-card-count"><%= cancelledCount%>
            </div>
            <a href="<%= request.getContextPath() %>/admin/service-booking/view?status=cancelled"
               class="status-card-action">View →</a>
        </div>

        <div class="status-card missed">
            <div class="status-card-header">
                <div class="status-card-title">Missed Appointments</div>
                <div class="status-card-icon">⚠️</div>
            </div>
            <div class="status-card-count"><%= missedAppointmentCount %>
            </div>
            <a href="<%= request.getContextPath() %>/admin/service-booking/view?status=missed-appointment"
               class="status-card-action">View →</a>
        </div>
    </div>

    <div class="quick-actions">
        <h2>Quick Actions</h2>
        <ul class="quick-actions-list">
            <li><a href="<%= request.getContextPath() %>/admin/service-booking/view?status=awaiting-confirmation">Review
                Pending Approvals</a></li>
            <li><a href="<%= request.getContextPath() %>/admin/service-booking/view?status=confirmed">Check Today's
                Appointments</a></li>
            <li><a href="<%= request.getContextPath() %>/admin/service-booking/view?status=in-progress">Monitor Active
                Services</a></li>
            <li><a href="<%= request.getContextPath() %>/admin/service-booking/view?status=all">View All Bookings</a>
            </li>
        </ul>
    </div>

    <a href="<%= request.getContextPath() %>/admin/dashboard" class="back-link">Back to Admin Dashboard</a>
</div>
</body>
</html>