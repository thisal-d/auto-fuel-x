<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/28/2025
  Time: 10:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Booking Confirmation - Vehicle Service & Refuel Center</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/customer/service-booking/success.css">
</head>
<body>
<jsp:include page="/views/customer/header.jsp"/>

<div class="confirmation-container">
    <div class="confirmation-card">
        <div class="success-section">
            <div class="success-icon">✓</div>
        </div>

        <div class="confirmation-header">
            <h1>Service Booked Successfully!</h1>
        </div>

        <div class="confirmation-message">
            Your service has been scheduled. Thank you for your booking.
        </div>

        <div class="confirmation-actions">
            <a href="<%= request.getContextPath() %>/customer/service-booking/list?status=active" class="btn-confirmation">Manage Active Bookings</a>
        </div>
    </div>
</div>

</body>
</html>