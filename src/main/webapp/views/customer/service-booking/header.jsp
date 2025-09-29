<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/28/2025
  Time: 10:51 PM
  To change this template use File | Settings | File Templates.
--%>
<nav>
    <a href="<%= request.getContextPath() %>/customer/service-booking/form">New Book</a> |
    <a href="<%= request.getContextPath() %>/customer/service-booking/list?status=active">Active Bookings</a> |
    <a href="<%= request.getContextPath() %>/customer/service-booking/list?status=missed">Missed Bookings</a> |
</nav>
<hr>