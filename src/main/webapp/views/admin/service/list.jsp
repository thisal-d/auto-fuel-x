<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/29/2025
  Time: 8:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.autofuelx.model.Service" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Service List</title>
</head>
<body>
<h1>Service List</h1>

<%
    List<Service> services = (List<Service>) request.getAttribute("services");
    String minCost = request.getParameter("minCost");
    String maxCost = request.getParameter("maxCost");
    String keyword = request.getParameter("keyword");
%>

<form method="get" action="<%= request.getContextPath() %>/admin/service/list">
    <label for="minCost">Min Cost:</label>
    <input type="number" id="minCost" name="minCost" value="<%= minCost != null ? minCost : "" %>" min="0" step="0.01">

    <label for="maxCost">Max Cost:</label>
    <input type="number" id="maxCost" name="maxCost" value="<%= maxCost != null ? maxCost : "" %>" min="0" step="0.01">

    <label for="keyword">Keyword:</label>
    <input type="text" id="keyword" name="keyword" value="<%= keyword != null ? keyword : "" %>"
           placeholder="Search in type and description">

    <button type="submit">Apply Filters</button>
    <a href="<%= request.getContextPath() %>/admin/service/list">Clear Filters</a>
</form>

<% if (services != null && !services.isEmpty()) { %>
<table border="1">
    <tr>
        <th>Service ID</th>
        <th>Type</th>
        <th>Description</th>
        <th>Cost</th>
    </tr>
    <% for (Service service : services) { %>
    <tr>
        <td><%= service.getServiceID() %>
        </td>
        <td><%= service.getType() %>
        </td>
        <td><%= service.getDescription() != null ? service.getDescription() : "" %>
        </td>
        <td>$<%= String.format("%.2f", service.getCost()) %>
        </td>
        <td>
            <form method="get" action="<%= request.getContextPath() %>/admin/service/update-form">
                <input type="hidden" name="id" value="<%= service.getServiceID() %>">
                <button type="submit">View</button>
            </form>
        </td>

        <td>
            <form method="get" action="<%= request.getContextPath() %>/admin/service/view">
                <input type="hidden" name="id" value="<%= service.getServiceID() %>">
                <button type="submit">Go to Update</button>
            </form>
        </td>

        <td>
            <form method="post" action="<%= request.getContextPath() %>/admin/service/delete">
                <input type="hidden" name="id" value="<%= service.getServiceID() %>">
                <button type="submit">Delete</button>
            </form>
        </td>

    </tr>
    <% } %>
</table>
<% } else { %>
<p>No services found matching your criteria.</p>
<% } %>
</body>
</html>