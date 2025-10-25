<%--
  Created by IntelliJ IDEA.
  User: Nadeeth
  Date: 9/28/2025
  Time: 10:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/customer/service-booking/header.css">
</head>
<div class="subheader-container">
    <div class="subheader-nav">
        <a href="<%= request.getContextPath() %>/customer/service-booking/list?status=active"
           class="subheader-link active">Active</a>
        <a href="<%= request.getContextPath() %>/customer/service-booking/list?status=missed" class="subheader-link">Missed</a>
    </div>
</div>