<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.autofuelx.model.Service" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Service Details</title>
</head>
<body>
<h1>Service Details</h1>

<%
    Service service = (Service) request.getAttribute("service");
    if (service != null) {
%>
<table border="1">
    <tr>
        <th>Type</th>
        <td><%= service.getType() %>
        </td>
    </tr>
    <tr>
        <th>Description</th>
        <td><%= service.getDescription() != null ? service.getDescription() : "" %>
        </td>
    </tr>
    <tr>
        <th>Cost</th>
        <td>Rs. <%= service.getCost() %>
        </td>
    </tr>
</table>

<p>
    <a href="<%= request.getContextPath() %>/admin/service/list">Back to List</a>
</p>

<%
} else {
%>
<p>Service not found.</p>
<p><a href="<%= request.getContextPath() %>/admin/service/list">Back to List</a></p>
<%
    }
%>

</body>
</html>
