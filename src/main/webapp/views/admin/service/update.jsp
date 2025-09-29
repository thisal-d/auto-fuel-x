<%@ page import="com.example.autofuelx.model.Service" %><%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/26/2025
  Time: 8:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Service</title>
</head>
<body>

<%
    Service service = (Service) request.getAttribute("service");
%>


<h1>Update Service</h1>

<form method="post" action="<%= request.getContextPath() %>/admin/service/update">
    <input type="hidden" name="id" value="<%=service.getServiceID()%>">

    <div>
        <label for="type">Service Type:</label>
        <input type="text" id="type" name="type" value="<%=service.getType()%>" required>
    </div>

    <div>
        <label for="description">Description:</label>
        <textarea id="description" name="description" rows="4" cols="50"  value="<%=service.getDescription()%>" ></textarea>
    </div>

    <div>
        <label for="cost">Cost ($):</label>
        <input type="number" id="cost" name="cost" step="0.01" min="0" value="<%=service.getCost()%>" required>
    </div>

    <div>
        <button type="submit">Update Service</button>
        <a href="<%= request.getContextPath() %>/admin/service/list">Cancel</a>
    </div>
</form>
</body>
</html>