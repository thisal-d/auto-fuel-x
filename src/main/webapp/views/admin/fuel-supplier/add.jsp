<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 10/14/2025
  Time: 5:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Fuel Supplier</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/admin/fuel-supplier/add.css">
</head>
<body>

<jsp:include page="/views/admin/header.jsp"/>

<div class="form-container">
    <!-- Navigation -->
    <div class="form-header">
        <h1>Add New Fuel Supplier</h1>
    </div>

    <div class="form-card">
        <%-- The form action points to your servlet's URL pattern --%>
        <form action="<%= request.getContextPath() %>/admin/fuel-supplier/add" method="post">
            <div class="form-group">
                <label for="name">Supplier Name:</label>
                <%-- The 'name' attribute must match the parameter name in your servlet --%>
                <input type="text" id="name" name="name" required placeholder="Enter supplier name">
            </div>

            <div class="form-group">
                <label for="phone-no">Phone Number:</label>
                <%-- The 'name' attribute must match the parameter name in your servlet --%>
                <input type="text" id="phone-no" name="phone-no" required placeholder="Enter phone number">
            </div>

            <div class="form-actions">
                <button type="submit" class="form-button">Add Supplier</button>
                <a href="<%= request.getContextPath() %>/admin/fuel-supplier/list" class="cancel-link">Cancel</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>