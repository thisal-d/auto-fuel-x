<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/28/2025
  Time: 10:51 PM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="/views/admin/header.jsp"/>

<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/admin/sub-header.css">
    <title>Dashboard</title>
</head>

<nav class="nav-horizontal">
    <a href="<%= request.getContextPath() %>/admin/service-booking/dashboard">Service Booking Dashboard</a> |
    <%--<a href="<%= request.getContextPath() %>/admin/service-booking/view?status=all">All Bookings</a> |--%>
    <a href="<%= request.getContextPath() %>/admin/service-booking/view?status=awaiting-confirmation">Pending
        Approval</a> |
    <a href="<%= request.getContextPath() %>/admin/service-booking/view?status=confirmed">Confirmed</a> |
    <a href="<%= request.getContextPath() %>/admin/service-booking/view?status=reschedule-required">Reschedule
        Required</a> |
    <a href="<%= request.getContextPath() %>/admin/service-booking/view?status=in-progress">In Progress</a> |
    <a href="<%= request.getContextPath() %>/admin/service-booking/view?status=awaiting-pickup">Service Done</a> |
    <a href="<%= request.getContextPath() %>/admin/service-booking/view?status=completed">Completed</a> |
    <a href="<%= request.getContextPath() %>/admin/service-booking/view?status=cancelled">Cancelled</a> |
    <a href="<%= request.getContextPath() %>/admin/service-booking/view?status=missed-appointment">Missed
        Appointments</a>
</nav>
<hr>