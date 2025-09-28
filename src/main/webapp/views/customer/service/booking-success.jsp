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
    <title>Booking Confirmation</title>
</head>
<body>
<h1>Service Booked Successfully!</h1>
<p>Your service has been scheduled. Thank you for your booking.</p>
<a href="<%= request.getContextPath() %>/customer/service/history">View Booking History</a>
</body>
</html>