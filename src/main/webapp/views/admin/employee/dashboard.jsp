<%--
  Created by IntelliJ IDEA.
  User: Saachika
  Date: 10/1/2025
  Time: 9:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/admin/employee/dashboard.css">
</head>
<body>
<nav class="nav-cards">
    <div class="nav-card">
        <div class="nav-card-icon">+</div>
        <a href="<%= request.getContextPath() %>/views/admin/employee/add.jsp">Add New Employee</a>
    </div>
    <div class="nav-card">
        <div class="nav-card-icon">👥</div>
        <a href="<%= request.getContextPath() %>/admin/employee/all">All employees</a>
    </div>
    <div class="nav-card">
        <div class="nav-card-icon">🔧</div>
        <a href="<%= request.getContextPath() %>/admin/employee/list-by-type?type=service-center-staff">Service Center
            Staff</a>
    </div>
    <div class="nav-card">
        <div class="nav-card-icon">👔</div>
        <a href="<%= request.getContextPath() %>/admin/employee/list-by-type?type=admin">Admin</a>
    </div>
    <div class="nav-card">
        <div class="nav-card-icon">📞</div>
        <a href="<%= request.getContextPath() %>/admin/employee/list-by-type?type=customer-care-officer">Customer Care
            Officer</a>
    </div>
    <div class="nav-card">
        <div class="nav-card-icon">⛽</div>
        <a href="<%= request.getContextPath() %>/admin/employee/list-by-type?type=refuel-cashier">Refuel Cashier</a>
    </div>
</nav>
</body>
</html>
