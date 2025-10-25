<%--
  Created by IntelliJ IDEA.
  User: Chamiru
  Date: 9/19/2025
  Time: 9:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.autofuelx.model.Customer" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Details</title>
</head>
<body>
<h1>Customer Details</h1>

<p>
    <a href="<%= request.getContextPath() %>/customers/list">Back to List</a>
</p>

<table border="1">
    <%
        Customer customer = (Customer) request.getAttribute("customer-view");
        if (customer != null) {
    %>
    <tr>
        <td>Customer ID</td>
        <td><%= customer.getCustomerID() %>
        </td>
    </tr>
    <tr>
        <td>First Name</td>
        <td><%= customer.getFirstName() %>
        </td>
    </tr>
    <tr>
        <td>Last Name</td>
        <td><%= customer.getLastName() %>
        </td>
    </tr>
    <tr>
        <td>Email</td>
        <td><%= customer.getEmail() %>
        </td>
    </tr>
    <tr>
        <td>Password</td>
        <td><%= customer.getPassword() %>
        </td>
    </tr>
    <tr>
        <td>Address No</td>
        <td><%= customer.getAddressNo() %>
        </td>
    </tr>
    <tr>
        <td>Address Lane</td>
        <td><%= customer.getAddressLane() %>
        </td>
    </tr>
    <tr>
        <td>Address Area</td>
        <td><%= customer.getAddressArea() %>
        </td>
    </tr>
    <% } %>
</table>

<p>
    <a href="<%= request.getContextPath() %>/customers/edit-form?id=<%= customer.getCustomerID() %>">Edit</a>
    <a href="<%= request.getContextPath() %>/customers/delete?id=<%= customer.getCustomerID() %>"
       onclick="return confirm('Are you sure you want to delete this customer?')">Delete</a>
</p>
</body>
</html>
