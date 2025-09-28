<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/19/2025
  Time: 1:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.autofuelx.model.Admin" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
</head>
<body>
<%-- Check if admin is logged in --%>
<%
    Admin admin = (Admin) session.getAttribute("admin");
    if (admin == null) {
        response.sendRedirect("adminLogin.jsp");
        return;
    }
%>

<h1>Welcome, <%= admin.getFirstName() %> <%= admin.getLastName() %>!</h1>
<p>Role: <%= admin.getRole() %></p>

<h2>Admin Management</h2>
<ul>
    <li><a href="adminList">View All Admins</a></li>
    <li><a href="addAdminForm.jsp">Add New Admin</a></li>
</ul>

<p><a href="adminLogout">Logout</a></p>
</body>
</html>
