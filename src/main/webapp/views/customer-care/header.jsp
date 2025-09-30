<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/30/2025
  Time: 4:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/employee/header.css">
</head>
<div class="subheader-container">
    <div class="subheader-nav">
        <a href="<%= request.getContextPath() %>/customer-care/complaint/list" class="subheader-link active">
            <span class="nav-icon">⛽</span> Refuel Form
        </a>
        <a href="<%= request.getContextPath() %>/views/employee/profile.jsp" class="subheader-link">
            <span class="nav-icon">👤</span> Profile
        </a>
        <div class="active-indicator" style="width: 120px; left: 0;"></div>
    </div>
</div>