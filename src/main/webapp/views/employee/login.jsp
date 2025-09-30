<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/30/2025
  Time: 3:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Employee Login</title>
</head>
<body>
<h2>Employee Login</h2>

<%
  String errorMessage = (String) request.getAttribute("error-message");
  if (errorMessage != null) {
%>
<p style="color:red;"><%= errorMessage %></p>
<%
  }
%>

<form action="<%= request.getContextPath() %>/employee/login" method="post">
  <label for="email">Email:</label><br>
  <input type="email" id="email" name="email" required><br><br>

  <label for="password">Password:</label><br>
  <input type="password" id="password" name="password" required><br><br>

  <label for="type">Type:</label><br>
  <select id="type" name="type" required>
    <option value="">--Select Type--</option>
    <option value="Admin">Admin</option>
    <option value="Service Center Staff">Service Center Staff</option>
    <option value="Customer Care Officer">Customer Care Officer</option>
    <option value="Refuel Cashier">Refuel Cashier</option>
  </select><br><br>

  <input type="submit" value="Login">
</form>
</body>
</html>

