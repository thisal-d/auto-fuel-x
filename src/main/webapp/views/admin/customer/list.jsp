<%--
  Created by IntelliJ IDEA.
  User: Chamiru
  Date: 9/19/2025
  Time: 8:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.autofuelx.model.Customer" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Customers</title>
</head>
<body>
<h1>All Customers</h1>

<table border="1">
    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Actions</th>
    </tr>
    <%
        List<Customer> customers = (List<Customer>) request.getAttribute("customers");
        if(customers != null) {
            for(Customer customer : customers) {
    %>
    <tr>
        <td><%= customer.getCustomerID() %></td>
        <td><%= customer.getFirstName() %></td>
        <td><%= customer.getLastName() %></td>
        <td><%= customer.getEmail() %></td>
        <td>
            <a href="<%= request.getContextPath() %>/customers/view?id=<%= customer.getCustomerID() %>">View</a>
            <a href="<%= request.getContextPath() %>/customers/edit-form?id=<%= customer.getCustomerID() %>">Edit</a>
            <a href="<%= request.getContextPath() %>/customers/delete?id=<%= customer.getCustomerID() %>"
               onclick="return confirm('Are you sure you want to delete this customer?')">Delete</a>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>

