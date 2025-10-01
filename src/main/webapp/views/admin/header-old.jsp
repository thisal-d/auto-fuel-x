<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/30/2025
  Time: 4:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/admin/employee/header.css">
    <title>Dashboard</title>
</head>
<nav class="nav-horizontal">
    <a href="<%= request.getContextPath() %>/views/employee/profile.jsp">Profile 👨🏻‍💼</a>
    <a href="<%= request.getContextPath() %>/admin/service-booking/dashboard">Service Booking 📌</a>
    <a href="<%= request.getContextPath() %>/admin/service/list">Services 🔧</a>
    <a href="<%= request.getContextPath() %>/views/admin/employee/dashboard.jsp">Employee 👨‍🔧</a>
    <a href="<%= request.getContextPath() %>//admin/customer/lis">Customers 🧑‍💻</a>
</nav>