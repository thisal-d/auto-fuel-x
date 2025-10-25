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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/employee/header.css">
</head>
<div class="subheader-container">
    <div class="subheader-nav">
        <a href="<%= request.getContextPath() %>/service-center/service-booking/assigned" class="subheader-link active">
            <span class="nav-icon"> 🛠️ </span> Assigned Jobs
        </a>
        <a href="<%= request.getContextPath() %>/views/employee/profile.jsp" class="subheader-link">
            <span class="nav-icon"> 👤 </span> Profile
        </a>

        <!-- Logout Section -->
        <div class="nav-section nav-section-logout">
            <a href="<%= request.getContextPath() %>/user/logout"
               onClick="return confirm('Are you sure You Want to Logout ?')" class="logout-link">Logout</a>
        </div>
        <div class="active-indicator" style="width: 120px; left: 0;"></div>
    </div>
</div>