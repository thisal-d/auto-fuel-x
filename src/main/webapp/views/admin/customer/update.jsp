<%--
  Created by IntelliJ IDEA.
  User: Chamiru
  Date: 9/19/2025
  Time: 8:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.autofuelx.model.Customer" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Customer</title>
</head>
<body>
<h1>Edit Customer</h1>

<p>
    <a href="<%= request.getContextPath() %>/customers/list">Back to List</a>
</p>

<form action="<%= request.getContextPath() %>/customers/update" method="post">
    <%
        Customer customer = (Customer) request.getAttribute("customer-update");
        if (customer != null) {
    %>
    <input type="hidden" name="customerID" value="<%= customer.getCustomerID() %>">

    <table border="1">
        <tr>
            <td>First Name</td>
            <td><input type="text" name="firstName" value="<%= customer.getFirstName() %>" required></td>
        </tr>
        <tr>
            <td>Last Name</td>
            <td><input type="text" name="lastName" value="<%= customer.getLastName() %>" required></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="email" name="email" value="<%= customer.getEmail() %>" required></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" value="<%= customer.getPassword() %>" required></td>
        </tr>
        <tr>
            <td>Address No</td>
            <td><input type="text" name="addressNo" value="<%= customer.getAddressNo() %>"></td>
        </tr>
        <tr>
            <td>Address Lane</td>
            <td><input type="text" name="addressLane" value="<%= customer.getAddressLane() %>"></td>
        </tr>
        <tr>
            <td>Address Area</td>
            <td><input type="text" name="addressArea" value="<%= customer.getAddressArea() %>"></td>
        </tr>
    </table>

    <input type="submit" value="Update Customer">
    <% } %>
</form>
</body>
</html>
